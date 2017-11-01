package com.ung.kotlinweather

import com.ung.kotlinweather.model.DailyForecasts

/**
 * Created by Ung on 30/10/2017.
 */

object HttpManager {
    private val appId = "f08f5bce8d0fe0c0903f145a88d773f8"

    suspend fun getCityForecast(city: String, cnt: Long? = null, units: String? = null): DailyForecasts
            = RetrofitServices.service.getDailyForecastsByCity(appId, city, cnt, units).await()
}