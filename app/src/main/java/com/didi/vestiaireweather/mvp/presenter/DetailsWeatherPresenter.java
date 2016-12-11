package com.didi.vestiaireweather.mvp.presenter;

import com.didi.vestiaireweather.mvp.MVP_DetailsWeather;
import com.didi.vestiaireweather.mvp.model.DetailsWeatherModel;
import com.tinmegali.mvp.mvp.GenericPresenter;

/**
 * Created by didi on 11/12/2016.
 * "Presenter" layer for Details weather
 */

public class DetailsWeatherPresenter extends GenericPresenter<MVP_DetailsWeather.RequiredPresenterOps,
        MVP_DetailsWeather.ProvidedModelOps, MVP_DetailsWeather.RequiredViewOps, DetailsWeatherModel>
        implements MVP_DetailsWeather.RequiredPresenterOps, MVP_DetailsWeather.ProvidedPresenterOps {

    /**
     * Operation called during VIEW creation in
     * {@link com.tinmegali.mvp.mvp.GenericMVPActivity#onCreate(Class, Object)}
     * Responsible to initialize MODEL.
     * @param view  The current VIEW instance
     */
    @Override
    public void onCreate(MVP_DetailsWeather.RequiredViewOps view) {
        super.onCreate(DetailsWeatherModel.class, this);
        setView(view);
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        //Don't call super except activity (not fragment) is being destroyed !!
        //super.onDestroy(isChangingConfiguration);
    }

    @Override
    public void onConfigurationChanged(MVP_DetailsWeather.RequiredViewOps view) {
        setView(view);
    }

    @Override
    public void onBackPressed() {}

    ///My future operations///
}
