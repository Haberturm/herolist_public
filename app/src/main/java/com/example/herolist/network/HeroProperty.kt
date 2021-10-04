package com.example.herolist.network

import com.squareup.moshi.Json

//data class HeroProperty(
//    val id: String,
//    val name:String,
//    val full_name: String,
//    val publisher: String,
//    val image:String
//)

data class HeroProperty(
    val id: String, @Json(name = "img_src") val imgSrcUrl: String,
    val type: String,
    val price: Double
)