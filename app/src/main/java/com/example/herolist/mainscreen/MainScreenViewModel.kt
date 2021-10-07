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
        getHeroesProperties(HeroApiFilter.SHOW_ALL)
        _publishers.value= listOf("All","DC Comics","Marvel", "Dark Horse", "Other")
    }

    private fun getHeroesProperties(filter: HeroApiFilter) {
        viewModelScope.launch {
            try {
                _properties.value = when(filter){
                    HeroApiFilter.SHOW_MARVEL -> applyStudioFilter(HeroApi.retrofitService.getProperties(),filter)
                    HeroApiFilter.SHOW_DC -> applyStudioFilter(HeroApi.retrofitService.getProperties(),filter)
                    HeroApiFilter.SHOW_DARK_HORSE -> applyStudioFilter(HeroApi.retrofitService.getProperties(),filter)
                    HeroApiFilter.SHOW_OTHER -> applyOtherFilter(HeroApi.retrofitService.getProperties())
                    else -> HeroApi.retrofitService.getProperties()
                }
                _response.value = "Success: Mars properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.i("responseerr", "${_response.value}")

            }
        }
    }

    fun updateFilter(filter: HeroApiFilter) {
        getHeroesProperties(filter)
    }


    private fun applyStudioFilter(lst:MutableList<HeroProp>, filter: HeroApiFilter):List<HeroProp>{
        var i=0
        while (i!=lst.size-1){
            if (lst[i].biography.publisher!=filter.value){
                lst.removeAt(i)
                i--
            }
            i++
        }
        return lst.toList()
    }

    private fun applyOtherFilter(lst:MutableList<HeroProp>):List<HeroProp>{
        val marvel = HeroApiFilter.SHOW_MARVEL.value
        val dc = HeroApiFilter.SHOW_DC.value
        val dh = HeroApiFilter.SHOW_DARK_HORSE.value
        var i=0
        while (i!=lst.size-1){
            val item = lst[i].biography.publisher
            if (    item == dh ||
                    item == dc ||
                    item == marvel){
                lst.removeAt(i)
                i--
            }
            i++
        }
        return lst.toList()
    }

    fun displayPropertyDetails(heroProp: HeroProp) {
        _navigateToSelectedProperty.value = heroProp
    }
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

}