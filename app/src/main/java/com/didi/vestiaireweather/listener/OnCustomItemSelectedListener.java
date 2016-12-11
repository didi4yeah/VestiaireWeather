package com.didi.vestiaireweather.listener;

import com.didi.vestiaireweather.data.model.vestiaire.WeatherDayVestiaire;
import com.mikepenz.iconics.view.IconicsImageView;

/**
 * Created by didi on 11/12/2016.
 * Listener to be aware when user click on forecast weather days list
 */

public interface OnCustomItemSelectedListener {
    void onItemSelected(IconicsImageView ivWeatherIcon, WeatherDayVestiaire currentWeatherDay);
}
