package com.example.herolist.network

import android.os.Parcelable
import com.bumptech.glide.load.resource.bitmap.VideoDecoder.parcel
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


//data class HeroProperty(
//    val id: String,
//    val name:String,
//    val full_name: String,
//    val publisher: String,
//    val image:String
//)

@Parcelize
data class HeroProperty(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String,
    val type: String,
    val price: Double) : Parcelable{
    val isRental
        get() = type == "rent" }
