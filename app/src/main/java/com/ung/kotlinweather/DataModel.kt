package com.ung.kotlinweather

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.ung.kotlinweather.model.DailyForecasts
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.actor

/**
 * Created by Ung on 01/11/2017.
 */

class DataModel : ViewModel() {
    private val mutableCity = MutableLiveData<String>().apply { value = "" }
    private val mutableDailyForecasts = MutableLiveData<DailyForecasts>().apply { value = null }

    val dailyForecasts: LiveData<DailyForecasts> = mutableDailyForecasts

    private val actor = actor<Action>(UI, Channel.CONFLATED) {
        for (action in this) when (action) {
            is SelectCity -> {
                mutableCity.value = action.city
                try {
                    mutableDailyForecasts.value = getCityForecast(action.city)
                } catch (e: Exception) {
                    Log.e("Error", e.message)
                }
            }
        }
    }

    init {
        action(SelectCity("Paris"))
    }

    fun action(action: Action) = actor.offer(action)

    private suspend fun getCityForecast(city: String) = HttpManager.getCityForecast(city, null, "metric")

}

sealed class Action

data class SelectCity(val city: String) : Action()