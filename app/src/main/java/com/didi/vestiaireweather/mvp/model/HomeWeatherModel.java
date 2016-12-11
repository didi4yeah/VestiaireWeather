package com.didi.vestiaireweather.mvp.model;

import com.didi.vestiaireweather.data.converter.DataVestiaireConverter;
import com.didi.vestiaireweather.data.model.api.ForecastResultApi;
import com.didi.vestiaireweather.data.model.api.WeatherDayApi;
import com.didi.vestiaireweather.data.model.vestiaire.WeatherDayVestiaire;
import com.didi.vestiaireweather.mvp.MVP_HomeWeather;
import com.didi.vestiaireweather.network.OpenWeatherApiService;
import com.didi.vestiaireweather.network.ServiceApiGenerator;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by didi on 11/12/2016.
 * "Model" layer for Home weather
 */

public class HomeWeatherModel extends BaseMvpModel<MVP_HomeWeather.RequiredPresenterOps>
        implements MVP_HomeWeather.ProvidedModelOps {

    private OpenWeatherApiService openWeatherApiService;

    /**
     * Method that recovers a reference to the PRESENTER
     * - You must ALWAYS call {@link super#onCreate(Object)} here
     * @param presenterOps Presenter interface
     */
    @Override
    public void onCreate(MVP_HomeWeather.RequiredPresenterOps presenterOps) {
        super.onCreate(presenterOps);
    }

    private OpenWeatherApiService createWeatherService(){
        return openWeatherApiService != null ? openWeatherApiService :
                ServiceApiGenerator.createService(
                        OpenWeatherApiService.class, OpenWeatherApiService.BASE_URL);
    }

    @Override
    public void getWeatherForecastOverApi(int cityId, String units, int total) {
        anySubscription = Observable.just(createWeatherService())
                .flatMap(openWeatherApiService -> getWeatherForecast(openWeatherApiService,
                        //TODO get last both from settings instead
                        cityId, units, total, "fr", "648a3aac37935e5b45e09727df728ac2"
                        ))
                .map(forecastResultApi ->
                {
                    //Convert API objects (can change) to Vestiaire format
                    List<WeatherDayVestiaire> listWeatherDays = new ArrayList<>();
                    if(forecastResultApi != null && forecastResultApi.getListWeatherDays()!= null
                            && !forecastResultApi.getListWeatherDays().isEmpty()){
                        for(WeatherDayApi weatherDayApi: forecastResultApi.getListWeatherDays()) {
                            WeatherDayVestiaire weatherDayVestiaire = DataVestiaireConverter.convertToVestiaire(weatherDayApi);
                            if(weatherDayVestiaire != null)
                                listWeatherDays.add(weatherDayVestiaire);
                        }
                    }
                    return listWeatherDays; //Could send back empty list ;)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listWeatherDays -> getPresenter().onWeatherForecastResult(listWeatherDays), this::showSnackBarErrorApi);
    }

    private Observable<ForecastResultApi> getWeatherForecast(OpenWeatherApiService openWeatherApiService,
                                                             int cityId, String units, int total, String lang, String apiKey){
        return openWeatherApiService.getForecastWeather(cityId, units, total, lang, apiKey)
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext(throwable -> {
                    showSnackBarErrorApi(throwable);
                    return Observable.empty(); //Will stop action
                });
    }
}
