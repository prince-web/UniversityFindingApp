package com.example.universityfindingapp.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.universityfindingapp.network.UniversityApi
import com.example.universityfindingapp.network.UniversityApiFilter
import com.example.universityfindingapp.network.UniversityProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class UniversityApiStatus { LOADING, ERROR, DONE }
class ListViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<UniversityApiStatus>()

    // The external immutable LiveData for the request status String
    val status: LiveData<UniversityApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<UniversityProperty>>()

    val property:LiveData<List<UniversityProperty>>
    get() = _properties

    private val _navigationToSelectedProperty = MutableLiveData<UniversityProperty>()

    val navigationToSelectedProperty:LiveData<UniversityProperty>
    get() = _navigationToSelectedProperty

    private var viewModelJob  = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

//    private val _status = MutableLiveData<UniversityApiStatus>()
//
//    val status :LiveData<UniversityApiStatus>
//    get() = _status
//
//    private val _properties = MutableLiveData<List<UniversityProperty>>()
//
//    val properties :LiveData<List<UniversityProperty>>
//    get() = _properties
//
//    private val _navigationToSelectedProperty = MutableLiveData<UniversityProperty>()
//
//    val navigationToSelectedProperty:LiveData<UniversityProperty>
//    get() = _navigationToSelectedProperty

    init {
        getUniversityListProperties(UniversityApiFilter.SHOW_INDIA)
    }

    private fun getUniversityListProperties(filter: UniversityApiFilter) {

        coroutineScope.launch {
            var getPropertyDeferrd = UniversityApi.retrofitService.getProperties(filter.value)

            try {
                _status.value = UniversityApiStatus.LOADING
                //This will run on a thread managed by retrofit
                var listResult = getPropertyDeferrd.await()
                _status.value = UniversityApiStatus.DONE
                _properties.value = listResult
                /*if (listResult.size > 0 ){

                }*/
            }catch (e:Exception){
                _status.value = UniversityApiStatus.ERROR
                //_properties.value = ArrayList()
            }
        }



    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(universityProperty: UniversityProperty){
        _navigationToSelectedProperty.value = universityProperty
    }

    fun displayPropertyDetailsComplete(){
        _navigationToSelectedProperty.value = null
    }

    fun updateFilter(filter:UniversityApiFilter){
        getUniversityListProperties(filter)
    }
}