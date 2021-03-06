package com.didi.vestiaireweather.mvp;

import com.didi.vestiaireweather.data.model.vestiaire.WeatherDayVestiaire;
import com.didi.vestiaireweather.mvp.presenter.RequiredPresenterCommonOps;
import com.didi.vestiaireweather.mvp.view.FragmentViewCommonOps;
import com.tinmegali.mvp.mvp.ModelOps;
import com.tinmegali.mvp.mvp.PresenterOps;

/**
 * Created by didi on 11/12/2016.
 * Will aggregate all operations available between all MVP layers
 * For now no need MVP operation but could be if loading data from DB for instance
 */

public interface MVP_DetailsWeather {

    /**
     * Presenter -> View
     */
    interface RequiredViewOps extends FragmentViewCommonOps {
        void showWeatherDetails(WeatherDayVestiaire weatherDayVestiaire);
    }

    /**
     * View -> Presenter
     */
    interface ProvidedPresenterOps extends PresenterOps<RequiredViewOps> {}

    /**
     * Model -> Presenter
     */
    interface RequiredPresenterOps extends RequiredPresenterCommonOps {}

    /**
     * Presenter -> Model
     */
    interface ProvidedModelOps extends ModelOps<RequiredPresenterOps> {}
}
