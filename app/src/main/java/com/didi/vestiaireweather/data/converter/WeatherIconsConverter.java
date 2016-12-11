package com.didi.vestiaireweather.data.converter;

import java.util.HashMap;

/**
 * Created by didi on 11/12/2016.
 * Matcher between open weather icons ids and our font icon from
 * https://erikflowers.github.io/weather-icons/
 */

public class WeatherIconsConverter {

    private static HashMap<String, String> mapIcons;

    public static HashMap<String, String> getMatchingIconsHashMap(){
        if(mapIcons == null) initMatchingIcons();
        return mapIcons;
    }

    /**
     * Matching between code open weather icons and our font icon
     */
    private static void initMatchingIcons(){
        mapIcons = new HashMap<>();
        //clear sky
        mapIcons.put("01d", "wic-day-sunny");
        mapIcons.put("01n", "wic-night-clear");

        //few clouds
        mapIcons.put("02d", "wic-day-cloudy");
        mapIcons.put("02n", "wic-night-alt-cloudy");

        //scattered clouds
        mapIcons.put("03d", "wic-cloud");
        mapIcons.put("03n", "wic-cloud");

        //broken clouds
        mapIcons.put("04d", "wic-cloudy");
        mapIcons.put("04n", "wic-cloudy");

        //shower rain
        mapIcons.put("09d", "wic-showers");
        mapIcons.put("09n", "wic-showers");

        //rain
        mapIcons.put("10d", "wic-day-showers");
        mapIcons.put("10n", "wic-night-alt-showers");

        //thunderstorm
        mapIcons.put("11d", "wic-thunderstorm");
        mapIcons.put("11n", "wic-thunderstorm");

        //snow
        mapIcons.put("13d", "wic-snow");
        mapIcons.put("13n", "wic-snow");

        //mist
        mapIcons.put("50d", "wic-fog");
        mapIcons.put("50n", "wic-fog");
    }
}
