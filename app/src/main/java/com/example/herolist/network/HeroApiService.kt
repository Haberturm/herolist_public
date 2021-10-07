package com.example.herolist.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

enum class HeroApiFilter(val value: String) {
    SHOW_DC("DC Comics"),
    SHOW_MARVEL("Marvel Comics"),
    SHOW_DARK_HORSE("Dark Horse Comics"),
    SHOW_OTHER("other"),
    SHOW_ALL("all") }

private const val BASE_URL =
    "https://akabab.github.io/superhero-api/api/" //https://superheroapi.com/api/

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface HeroApiService {
    @GET("all.json")
    suspend fun getProperties(): MutableList<HeroProp>
}

object HeroApi {
    val retrofitService : HeroApiService by lazy {
        retrofit.create(HeroApiService::class.java) }
}
