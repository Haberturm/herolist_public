package com.example.herolist.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.herolist.network.HeroApi
import com.example.herolist.network.HeroApiFilter
import com.example.herolist.network.HeroProp
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainScreenViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response
    private val _properties = MutableLiveData<List<HeroProp>>()
    val properties: LiveData<List<HeroProp>>
        get() = _properties
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status
    private val _publishers = MutableLiveData<List<String>>()
    val publishers:LiveData<List<String>>
        get() = _publishers

    private val _navigateToSelectedProperty = MutableLiveData<HeroProp?>()
    val navigateToSelectedProperty: LiveData<HeroProp?>
        get() = _navigateToSelectedProperty


    init {
        getHeroesProperties()
        _publishers.value= listOf("All","DC Comics","Marvel")
    }

    private fun getHeroesProperties() {
        Log.i("SIZEE", "@@@@@@@@")
        viewModelScope.launch {
            try {

                _properties.value = HeroApi.retrofitService.getProperties()
                _response.value = "Success: Mars properties retrieved"


            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"

            }
        }
       // Log.i("SIZEE", "${properties.value!!.size}")
    }

//    fun updateFilter(filter: HeroApiFilter) {
//        getHeroesProperties()
//    }


    fun displayPropertyDetails(heroProp: HeroProp) {
        _navigateToSelectedProperty.value = heroProp
    }
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

}