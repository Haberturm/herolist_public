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
    private val _property = MutableLiveData<HeroProperty>()
    val property: LiveData<HeroProperty>
        get() = _property
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status



    init {
        getHeroesProperties()
    }

    private fun getHeroesProperties() {
        viewModelScope.launch {
            try {
                val listResult = HeroApi.retrofitService.getProperties()
                _status.value = "Success: ${listResult.size} Mars properties retrieved"
                if (listResult.size > 0) {
                    _property.value = listResult[0]
                }
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }


    }
}