package com.ung.kotlinweather

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ung.kotlinweather.model.DailyForecasts
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.coordinator_drawer.*
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.navigation_drawer.*

class MainActivity : AppCompatActivity() {
    private lateinit var model: DataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        drawer?.run {
            val toggle = ActionBarDrawerToggle(
                    this@MainActivity,
                    drawer,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }
        navigation.setNavigationItemSelectedListener {
            model.action(SelectCity(it.title.toString()))
        }
        initModel()
    }

    private fun initModel() {
        model = ViewModelProviders.of(this).get(DataModel::class.java)
        model.dailyForecasts.observe { displayCityForecast(it ?: DailyForecasts()) }
    }

    private fun displayCityForecast(cityForecast: DailyForecasts) {
        val iconUrl: String = "http://openweathermap.org/img/w/" + cityForecast.list?.get(0)?.weather?.get(0)?.icon + ".png"
        cityName.text = getString(R.string.city_name, cityForecast.city?.name, cityForecast.city?.country)
        pressure.text = getString(R.string.pressure, cityForecast.list?.get(0)?.pressure.toString())
        humidity.text = getString(R.string.humidity, cityForecast.list?.get(0)?.humidity.toString()) + " %"
        weatherDesc.text = cityForecast.list?.get(0)?.weather?.get(0)?.description ?: "empty"
        maxTemp.text = getString(R.string.max_temp, cityForecast.list?.get(0)?.temp?.max.toString())
        minTemp.text = getString(R.string.min_temp, cityForecast.list?.get(0)?.temp?.min.toString())
        dayTemp.text = getString(R.string.day_temp, cityForecast.list?.get(0)?.temp?.day.toString())
        nightTemp.text = getString(R.string.night_temp, cityForecast.list?.get(0)?.temp?.night.toString())
        Glide.with(this).load(iconUrl).into(weatherIcon)
    }

    private fun <T> LiveData<T>.observe(observe: (T?) -> Unit)
            = observe(this@MainActivity, Observer { observe(it) })
}
