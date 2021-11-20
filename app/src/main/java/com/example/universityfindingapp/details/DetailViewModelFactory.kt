package com.example.universityfindingapp.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.universityfindingapp.network.UniversityProperty
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
    private val universityProperty: UniversityProperty,
    private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(universityProperty,application)as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}