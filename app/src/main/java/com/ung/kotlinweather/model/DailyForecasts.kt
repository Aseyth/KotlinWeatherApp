package com.ung.kotlinweather.model

/**
 * Created by Ung on 01/11/2017.
 */

class DailyForecasts {
    var city: City? = null
    var cod: String? = null
    var message: String? = null
    var cnt: Long = 0
    var list: Array<DailyForecast>? = null
}