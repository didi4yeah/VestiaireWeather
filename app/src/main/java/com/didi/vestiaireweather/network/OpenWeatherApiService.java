package com.didi.vestiaireweather.network;

import com.didi.vestiaireweather.data.model.api.ForecastResultApi;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by didi on 11/12/2016.
 */

public interface OpenWeatherApiService {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    /**
     * Get forecast weather for...
     * @param cityId ... this city
     * @param units ... in this units (i.e. °C)
     * @param total ... for max items
     * @param lang ... in this language
     * @param apiKey ... with auth access
     * @return ForecastResultApi
     */
    @GET("forecast/daily/")
    Observable<ForecastResultApi> getForecastWeather(
            @Query("id") int cityId, @Query("units") String units,
            @Query("cnt") int total, @Query("lang") String lang,
            @Query("APPID") String apiKey
    );
}
