package com.didi.vestiaireweather.mvp.presenter;

import com.didi.vestiaireweather.data.model.vestiaire.WeatherDayVestiaire;
import com.didi.vestiaireweather.mvp.MVP_HomeWeather;
import com.didi.vestiaireweather.mvp.model.HomeWeatherModel;

import java.util.List;

/**
 * Created by didi on 11/12/2016.
 * "Presenter" layer for Home weather
 */

public class HomeWeatherPresenter extends BaseMVPPresenter<MVP_HomeWeather.RequiredPresenterOps,
        MVP_HomeWeather.ProvidedModelOps, MVP_HomeWeather.RequiredViewOps, HomeWeatherModel>
        implements MVP_HomeWeather.RequiredPresenterOps, MVP_HomeWeather.ProvidedPresenterOps {

    /**
     * Operation called during VIEW creation in
     * {@link com.tinmegali.mvp.mvp.GenericMVPActivity#onCreate(Class, Object)}
     * Responsible to initialize MODEL.
     * @param view  The current VIEW instance
     */
    @Override
    public void onCreate(MVP_HomeWeather.RequiredViewOps view) {
        super.onCreate(HomeWeatherModel.class, this);
        setView(view);
    }

    @Override
    public void onConfigurationChanged(MVP_HomeWeather.RequiredViewOps view) {
        setView(view);
    }

    ///My operations///

    @Override
    public void getWeatherForecast(int cityId, String units, int total) {
        getModel().getWeatherForecastOverApi(cityId, units, total);
    }

    @Override
    public void onWeatherForecastResult(List<WeatherDayVestiaire> listWeatherDays) {
        getView().showWeatherForecast(listWeatherDays);
    }
}
