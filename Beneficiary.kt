package com.example.empower

/**
 * Represents a beneficiary in an employee empowerment system.
 *
 * This data class encapsulates all the necessary details of a beneficiary,
 * providing a structured way to handle beneficiary information throughout the system.
 *
 * @property firstName The first name of the beneficiary.
 * @property lastName The last name of the beneficiary.
 * @property middleName The middle name of the beneficiary, which may or may not be present.
 * @property beneType The type of beneficiary, describing their role or relation.
 * @property designationCode A code that represents the beneficiary's designation (e.g., 'P' for Primary).
 * @property ssn Social Security Number of the beneficiary.
 * @property dob Date of birth of the beneficiary, formatted as a string.
 * @property phone Contact phone number of the beneficiary.
 * @property address Address of the beneficiary.
 */
data class Beneficiary(
    val firstName: String,
    val lastName: String,
    val middleName: String?, // ? field may not be present
    val beneType: String,
    val designationCode: String,
    val ssn: String,
    val dob: String,
    val phone: String,
    val address: String
) {
    val designation: String
        get() = when (designationCode) {
            "P" -> "Primary"
            "C" -> "Contingent"
            else -> "Unknown"
        }
}
