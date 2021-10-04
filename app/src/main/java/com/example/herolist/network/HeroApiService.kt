package com.example.herolist.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL =
    "https://superheroapi.com/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface HeroApiService {
    @GET("id")
    fun getProperties():
            Call<String>
}

object HeroApi {
    val retrofitService : HeroApiService by lazy {
        retrofit.create(HeroApiService::class.java) }
}
