package com.ung.kotlinweather.model

/**
 * Created by Ung on 01/11/2017.
 */

class DailyForecast {
    var dt: Long = 0
    var temp: Temp? = null
    var pressure: Float = 0f
    var humidity: Int = 0
    var weather: Array<Weather>? = null
    var speed: Float = 0f
    var deg: Float = 0f
    var clouds: Int = 0
    var rain: Float = 0f
    var snow: Float = 0f
}