package com.didi.vestiaireweather.data.model.vestiaire;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by didi on 11/12/2016.
 */

public class WeatherDayVestiaire implements Serializable {
    private int id; //Will be useful to user shared element transitions
    private String weatherIconId; //Will help to find matching icon
    private String desc;
    private int tempDay;
    private double pressure;
    private double humidity;
    private double windSpeed;
    private double windDirection; //angle in degrees
    private double cloudiness;
    private Calendar date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeatherIconId() {
        return weatherIconId;
    }

    public void setWeatherIconId(String weatherIconId) {
        this.weatherIconId = weatherIconId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getTempDay() {
        return tempDay;
    }

    public void setTempDay(int tempDay) {
        this.tempDay = tempDay;
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
