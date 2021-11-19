package com.example.universityfindingapp.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.universityfindingapp.network.UniversityApi
import com.example.universityfindingapp.network.UniversityProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class UniversityApiStatus { LOADING, ERROR, DONE }
class ListViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val response: LiveData<String>
        get() = _response

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
        getUniversityListProperties()
    }

    private fun getUniversityListProperties() {

        coroutineScope.launch {
            var getPropertyDeferrd = UniversityApi.retrofitService.getProperties()

            try {
                var listResult = getPropertyDeferrd.await()
                _response.value = "Success: ${listResult.size} University Properties retrieved"
            }catch (t:Throwable){
                _response.value = "Failure" + t.message
            }
        }



    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}