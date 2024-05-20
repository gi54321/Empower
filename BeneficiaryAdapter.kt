package com.example.empower

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * An adapter for a RecyclerView to display a list of beneficiaries.
 *
 * This adapter manages a mutable list of [Beneficiary] objects and handles
 * user clicks on each item through a lambda function. It is responsible for
 * creating and binding view holders that display beneficiary details.
 *
 * @property beneficiaries A mutable list of [Beneficiary] objects that the adapter manages.
 * @property onItemClick A lambda function to handle clicks on each beneficiary item.
 */
class BeneficiaryAdapter(
    private var beneficiaries: MutableList<Beneficiary>,
    private val onItemClick: (Beneficiary) -> Unit
) : RecyclerView.Adapter<BeneficiaryViewHolder>() {

    /**
     * Creates and returns a ViewHolder for the beneficiary, initializing it with a TextView.
     *
     * @param parent The ViewGroup into which the new view will be added.
     * @param viewType The view type of the new View, not used here.
     * @return A new instance of [BeneficiaryViewHolder] initialized with a TextView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeneficiaryViewHolder {
        val textView = TextView(parent.context).apply {
            textSize = 16f
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            ).apply {
                setPadding(20, 20, 20, 20)
            }
        }
        return BeneficiaryViewHolder(textView, onItemClick)
    }

    /**
     * Binds a beneficiary to a ViewHolder at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the content of the item.
     * @param position The position of the item within the adapter's dataset.
     */
    override fun onBindViewHolder(holder: BeneficiaryViewHolder, position: Int) {
        holder.bind(beneficiaries[position])
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The size of the [beneficiaries] list.
     */
    override fun getItemCount() = beneficiaries.size

    /**
     * Updates the adapter's data set with a new list of beneficiaries and notifies any
     * registered observers that the data set has changed.
     *
     * @param newBeneficiaries The new list of beneficiaries to replace the current data set.
     */
    fun updateData(newBeneficiaries: List<Beneficiary>) {
        beneficiaries.clear()
        beneficiaries.addAll(newBeneficiaries)
        notifyDataSetChanged()  // Notify any registered observers that the data set has changed.
    }
}
