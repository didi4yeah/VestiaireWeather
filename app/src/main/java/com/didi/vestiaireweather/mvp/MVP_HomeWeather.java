package com.didi.vestiaireweather.mvp;

import com.didi.vestiaireweather.data.model.vestiaire.WeatherDayVestiaire;
import com.didi.vestiaireweather.mvp.presenter.RequiredPresenterCommonOps;
import com.didi.vestiaireweather.mvp.view.FragmentViewCommonOps;
import com.tinmegali.mvp.mvp.ModelOps;
import com.tinmegali.mvp.mvp.PresenterOps;

import java.util.List;

/**
 * Created by didi on 11/12/2016.
 * Will aggregate all operations available between all MVP layers
 */

public interface MVP_HomeWeather {

    /**
     * Presenter -> View
     */
    interface RequiredViewOps extends FragmentViewCommonOps {
        void showWeatherForecast(List<WeatherDayVestiaire> listWeatherDays);
    }

    /**
     * View -> Presenter
     */
    interface ProvidedPresenterOps extends PresenterOps<RequiredViewOps> {
        void getWeatherForecast(int cityId, String units, int total);
    }

    /**
     * Model -> Presenter
     */
    interface RequiredPresenterOps extends RequiredPresenterCommonOps {
        void onWeatherForecastResult(List<WeatherDayVestiaire> listWeatherDays);
    }

    /**
     * Presenter -> Model
     */
    interface ProvidedModelOps extends ModelOps<RequiredPresenterOps> {
        void getWeatherForecastOverApi(int cityId, String units, int total);
    }
}
