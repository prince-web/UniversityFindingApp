package com.example.universityfindingapp.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.universityfindingapp.network.UniversityProperty

class DetailViewModel(universityProperty: UniversityProperty,
app:Application):AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<UniversityProperty>()
    val selectedProperty:LiveData<UniversityProperty>
    get() = _selectedProperty

    init {
        _selectedProperty.value = universityProperty
    }
}