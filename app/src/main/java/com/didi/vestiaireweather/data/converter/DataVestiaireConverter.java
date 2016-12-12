package com.didi.vestiaireweather.data.converter;

import com.didi.vestiaireweather.data.model.api.WeatherDayApi;
import com.didi.vestiaireweather.data.model.api.WeatherDayDescApi;
import com.didi.vestiaireweather.data.model.vestiaire.WeatherDayVestiaire;
import com.didi.vestiaireweather.utils.StringUtils;

/**
 * Created by didi on 11/12/2016.
 * Will convert any kind of data (here API for instance) to our format
 * we are used to work with and able to improve as we want
 */

public class DataVestiaireConverter {

    private DataVestiaireConverter() {}

    public static WeatherDayVestiaire convertToVestiaire(WeatherDayApi weatherDayApi) {
        WeatherDayVestiaire weatherDayVestiaire = null;
        if(weatherDayApi != null) {
            String stringValue;
            weatherDayVestiaire = new WeatherDayVestiaire();
            weatherDayVestiaire.setId(weatherDayApi.getId());
            weatherDayVestiaire.setPressure(weatherDayApi.getPressure());
            weatherDayVestiaire.setHumidity(weatherDayApi.getHumidity());
            weatherDayVestiaire.setWindSpeed(weatherDayApi.getWindSpeed());
            weatherDayVestiaire.setWindDirection(weatherDayApi.getWindDirection());
            weatherDayVestiaire.setCloudiness(weatherDayApi.getCloudiness());

            //Temperature part
            if(weatherDayApi.getWeatherTempApi() != null) {
                weatherDayVestiaire.setTempDay((int) Math.round(weatherDayApi.getWeatherTempApi().getDayTemp()));
            }

            //Weather desc part
            if(weatherDayApi.getListWeatherDayDescApi() != null && !weatherDayApi.getListWeatherDayDescApi().isEmpty()) {
                //Will only work of first element here so get it
                WeatherDayDescApi weatherDayDescApi = weatherDayApi.getListWeatherDayDescApi().get(0);
                if(weatherDayDescApi != null) {
                    if(StringUtils.isNotNullOrEmpty(stringValue = weatherDayDescApi.getDesc()))
                        weatherDayVestiaire.setDesc(stringValue);
                    weatherDayVestiaire.setWeatherIconId(weatherDayDescApi.getIcon());
                }
            }
        }
        return weatherDayVestiaire;
    }
}
