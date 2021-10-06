package com.example.herolist.bio

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.herolist.network.HeroProp

class BioViewModelFactory(
    private val heroProp: HeroProp,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BioViewModel::class.java)) {
            return BioViewModel(heroProp, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}