package com.example.herolist.bio

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.herolist.network.HeroProperty

class BioViewModelFactory(
    private val heroProperty: HeroProperty,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BioViewModel::class.java)) {
            return BioViewModel(heroProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}