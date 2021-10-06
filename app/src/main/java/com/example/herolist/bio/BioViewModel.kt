package com.example.herolist.bio

import android.app.Application
import androidx.lifecycle.*
import com.example.herolist.R
import com.example.herolist.network.HeroProp

class BioViewModel(heroProp: HeroProp, app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<HeroProp>()
    val selectedProperty: LiveData<HeroProp>
        get() = _selectedProperty

    init {
        _selectedProperty.value = heroProp
    }

//    val displayPropertyPrice = Transformations.map(selectedProperty) {
//        app.applicationContext.getString(
//            when (it.isRental) {
//                true -> R.string.display_price_monthly_rental
//                false -> R.string.display_price
//            }, it.price)
//    }
//
//val displayPropertyType = Transformations.map(selectedProperty) {
//    app.applicationContext.getString(R.string.display_powerstat,
//        app.applicationContext.getString(
//            when (it) {
//                it.powerstats.intelligence -> R.string.type_rent
//                false -> R.string.type_sale
//            }))
//}
}