package com.example.herolist.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL =
    "https://mars.udacity.com/" //https://superheroapi.com/api/

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface HeroApiService {
    @GET("realestate")
    suspend fun getProperties(): List<HeroProperty>
}

object HeroApi {
    val retrofitService : HeroApiService by lazy {
        retrofit.create(HeroApiService::class.java) }
}
