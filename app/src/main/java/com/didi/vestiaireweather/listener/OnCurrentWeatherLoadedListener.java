package com.didi.vestiaireweather.listener;

import com.didi.vestiaireweather.data.model.vestiaire.WeatherDayVestiaire;

/**
 * Created by didi on 11/12/2016.
 * Listener to be aware when home loaded forecast data and especially current day
 */

public interface OnCurrentWeatherLoadedListener {
    void onCurrentWeatherLoaded(WeatherDayVestiaire currentWeatherDay);
}
