package com.example.empower

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder for displaying beneficiary information in a RecyclerView.
 *
 * This ViewHolder binds a [Beneficiary] object to a TextView within a
 * RecyclerView item and handles click events for each item. The beneficiary's first name,
 * last name, beneficiary type, and designation are displayed in the TextView.
 *
 * @param view The view associated with this ViewHolder.
 * @param onItemClick A lambda function that is called when the item is clicked,
 *                      passing the [Beneficiary] as a parameter.
 */
class BeneficiaryViewHolder(view: View, private val onItemClick: (Beneficiary) -> Unit) : RecyclerView.ViewHolder(view) {
    private val textView = view as TextView

    /**
     * Binds a [Beneficiary] to this ViewHolder.
     *
     * Sets the text of the TextView to display the beneficiary's full name,
     * beneficiary type, and designation.
     * Sets up a click listener on the itemView that triggers the [onItemClick]
     * function with the current [Beneficiary].
     *
     * @param beneficiary The [Beneficiary] data to be bound to this ViewHolder.
     */
    fun bind(beneficiary: Beneficiary) {
        textView.text = "${beneficiary.firstName} ${beneficiary.lastName} - " +
                "${beneficiary.beneType} - ${beneficiary.designation}"
        itemView.setOnClickListener { onItemClick(beneficiary) }
    }
}
