package com.didi.vestiaireweather.data.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by didi on 11/12/2016.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastResultApi {
    @JsonProperty("list")
    private List<WeatherDayApi> listWeatherDays;

    public List<WeatherDayApi> getListWeatherDays() {
        return listWeatherDays;
    }
}
