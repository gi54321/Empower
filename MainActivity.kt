package com.example.empower

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Main activity class that initializes and manages UI components for displaying and
 * interacting with a list of beneficiaries.
 *
 * This class sets up a RecyclerView managed by a BeneficiaryAdapter and a
 * BeneficiaryViewModel. It also handles dialog presentations
 * for beneficiary details upon item clicks.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: BeneficiaryViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BeneficiaryAdapter  // Adapter for managing beneficiary items.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup the main layout as a vertical LinearLayout to host the RecyclerView.
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        // Initialize the RecyclerView for displaying beneficiaries.
        recyclerView = RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        // Setup the adapter with an empty list and a click listener to show details dialog.
        adapter = BeneficiaryAdapter(mutableListOf()) { beneficiary ->
            showDetailsDialog(beneficiary)
        }
        recyclerView.adapter = adapter

        // Add the RecyclerView to the main layout.
        layout.addView(recyclerView)
        setContentView(layout) // Set the LinearLayout as the content view for the activity.

        // Initialize the ViewModel and observe changes to the beneficiary data.
        viewModel = ViewModelProvider(this).get(BeneficiaryViewModel::class.java)
        viewModel.beneficiaries.observe(this, Observer { beneficiaries ->
            adapter.updateData(beneficiaries) // Update adapter data when the observed data changes.
        })
    }

    /**
     * Displays a detailed dialog for the selected beneficiary.
     *
     * @param beneficiary The [Beneficiary] for which details are to be shown.
     */
    private fun showDetailsDialog(beneficiary: Beneficiary) {
        AlertDialog.Builder(this)
            .setTitle("${beneficiary.firstName} ${beneficiary.lastName}")
            .setMessage("SSN: ${beneficiary.ssn}\nDOB: ${beneficiary.dob}\n" +
                    "Phone: ${beneficiary.phone}\nAddress: ${beneficiary.address}")
            .setPositiveButton("OK", null)
            .show()
    }
}
