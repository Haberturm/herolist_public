package com.example.herolist.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.herolist.network.HeroApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainScreenViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response
    init {
        getHeroesProperties()
    }

    private fun getHeroesProperties() {
        //_response.value = "Set the Hero API Response here!"
        HeroApi.retrofitService.getProperties().enqueue( object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("FAILURE","CALLED")
                _response.value = "Failure: " + t.message
                Log.i("FAILURE","${response.value}")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {

                _response.value = response.body()
                Log.i("FAILURE1","${_response.value}")

            }
        })
        Log.i("FAILURE","${_response.value}")

    }
}