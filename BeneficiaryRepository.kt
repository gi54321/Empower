package com.example.empower
import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

/**
 * Repository for fetching and parsing beneficiary data stored in a JSON format.
 *
 * This class is responsible for accessing the beneficiary data from assets and converting
 * it into a list of [Beneficiary] objects.
 *
 * @property context The context from which to access application assets.
 */
class BeneficiaryRepository(context: Context) {
    private val assets = context.assets

    /**
     * Fetches raw JSON data from the assets folder.
     *
     * @return A string containing the raw JSON data.
     */
    private fun fetchJsonData(): String {
        return assets.open("Beneficiaries.json").bufferedReader().use { it.readText() }
    }

    /**
     * Retrieves a list of [Beneficiary] objects parsed from the JSON data.
     *
     * @return A list of [Beneficiary] objects.
     */
    fun getBeneficiaries(): List<Beneficiary> {
        val jsonString = fetchJsonData()
        val jsonArray = JSONArray(jsonString)
        return List(jsonArray.length()) { index ->
            parseBeneficiary(jsonArray.getJSONObject(index))
        }
    }

    /**
     * Parses a JSON object into a [Beneficiary] instance.
     *
     * @param jsonObject The JSON object to parse.
     * @return A [Beneficiary] object populated with data from the JSON object.
     */
    private fun parseBeneficiary(jsonObject: JSONObject): Beneficiary {
        val addressObj = jsonObject.getJSONObject("beneficiaryAddress")
        return Beneficiary(
            firstName = jsonObject.optString("firstName", "N/A"),
            lastName = jsonObject.optString("lastName", "N/A"),
            middleName = jsonObject.optString("middleName", null),
            beneType = jsonObject.optString("beneType", "N/A"),
            designationCode = jsonObject.optString("designationCode", "N/A"),
            ssn = jsonObject.optString("socialSecurityNumber", "N/A"),
            dob = formatDateOfBirth(jsonObject.optString("dateOfBirth", "Invalid Date")),
            phone = jsonObject.optString("phoneNumber", "N/A"),
            address = formatAddress(addressObj)
        )
    }

    /**
     * Formats a date of birth string into a more readable format.
     *
     * @param dob A string representing the date of birth in YYYYMMDD format.
     * @return A formatted date of birth string or "Invalid Date" if input is incorrect.
     */
    private fun formatDateOfBirth(dob: String): String {
        return if (dob.length == 8) {
            "${dob.substring(0, 2)}/${dob.substring(2, 4)}/${dob.substring(4)}"
        } else {
            "Invalid Date"
        }
    }

    /**
     * Formats a JSON object representing an address into a single string.
     *
     * @param jsonAddress The JSON object containing the address components.
     * @return A single string representing the full address.
     */
    private fun formatAddress(jsonAddress: JSONObject): String {
        val parts = listOf(
            jsonAddress.optString("firstLineMailing").nullToEmpty(),
            jsonAddress.optString("scndLineMailing").nullToEmpty(),
            jsonAddress.optString("city").nullToEmpty(),
            jsonAddress.optString("zipCode").nullToEmpty(),
            jsonAddress.optString("stateCode").nullToEmpty(),
            jsonAddress.optString("country").nullToEmpty()
        ).filterNotEmpty()
        return parts.joinToString(", ")
    }

    /**
     * Converts a nullable string that may contain the literal "null" into an empty string.
     *
     * @return A non-null string, either empty or containing the original content.
     */
    private fun String?.nullToEmpty(): String {
        return if (this == null || this.lowercase() == "null") "" else this
    }

    /**
     * Filters out empty strings from a list of strings.
     *
     * @return A list containing only non-empty strings.
     */
    private fun List<String>.filterNotEmpty(): List<String> {
        return this.filter { it.isNotEmpty() }
    }
}
