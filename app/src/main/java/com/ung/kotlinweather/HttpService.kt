package com.ung.kotlinweather

import com.ung.kotlinweather.model.DailyForecasts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ung on 01/11/2017.
 */

interface HttpService {
    @GET("/data/2.5/forecast/daily")
    fun getDailyForecastsByCity(
            @Query("appid") appid: String,
            @Query("q") city: String,
            @Query("cnt") cnt: Long? = null,
            @Query("units") units: String? = null): Call<DailyForecasts>
}