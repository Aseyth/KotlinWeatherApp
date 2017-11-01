package com.ung.kotlinweather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ung on 30/10/2017.
 */

object RetrofitServices {

    private const val URL = "http://api.openweathermap.org"

    private val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

    val service = retrofit.create(HttpService::class.java)!!
}