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
public class WeatherDayApi {
    @JsonProperty("dt")
    private int id; //Will take like timestamp as id
    @JsonProperty("pressure")
    private double pressure;
    @JsonProperty("humidity")
    private double humidity;
    @JsonProperty("speed")
    private double windSpeed;
    @JsonProperty("deg")
    private double windDirection;
    @JsonProperty("clouds")
    private double cloudiness;
    @JsonProperty("temp")
    private WeatherTempApi weatherTempApi;
    @JsonProperty("weather")
    private List<WeatherDayDescApi> listWeatherDayDescApi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public double getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(double cloudiness) {
        this.cloudiness = cloudiness;
    }

    public WeatherTempApi getWeatherTempApi() {
        return weatherTempApi;
    }

    public void setWeatherTempApi(WeatherTempApi weatherTempApi) {
        this.weatherTempApi = weatherTempApi;
    }

    public List<WeatherDayDescApi> getListWeatherDayDescApi() {
        return listWeatherDayDescApi;
    }

    public void setListWeatherDayDescApi(List<WeatherDayDescApi> listWeatherDayDescApi) {
        this.listWeatherDayDescApi = listWeatherDayDescApi;
    }
}
