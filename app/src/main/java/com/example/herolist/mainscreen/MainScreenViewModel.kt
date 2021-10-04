package com.example.herolist.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.herolist.network.HeroApi
import com.example.herolist.network.HeroProperty
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainScreenViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response
    private val _properties = MutableLiveData<List<HeroProperty>>()
    val properties: LiveData<List<HeroProperty>>
        get() = _properties
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status



    init {
        getHeroesProperties()
    }

    private fun getHeroesProperties() {
        viewModelScope.launch {
            try {
                _properties.value = HeroApi.retrofitService.getProperties()
                _response.value = "Success: Mars properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }


    }
}