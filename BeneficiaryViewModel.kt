package com.example.empower

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * ViewModel for managing UI-related data in the lifecycle of the beneficiaries screen.
 *
 * This ViewModel extends [AndroidViewModel] and is designed to store and manage UI-related data
 * in a lifecycle conscious way. This allows data to survive configuration changes such
 * as screen rotations.
 *
 * @param application The application that owns this ViewModel.
 */
class BeneficiaryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BeneficiaryRepository(application)
    // LiveData to hold data which the UI can observe and react, keeping load off UI thread.
    val beneficiaries = MutableLiveData<List<Beneficiary>>()

    init {
        loadBeneficiaries()
    }
    private fun loadBeneficiaries() {
        beneficiaries.value = repository.getBeneficiaries()
    }
}