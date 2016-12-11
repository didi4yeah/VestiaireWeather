package com.didi.vestiaireweather.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.didi.vestiaireweather.R;
import com.didi.vestiaireweather.data.converter.WeatherIconsConverter;
import com.didi.vestiaireweather.data.model.vestiaire.WeatherDayVestiaire;
import com.didi.vestiaireweather.mvp.MVP_DetailsWeather;
import com.didi.vestiaireweather.mvp.presenter.DetailsWeatherPresenter;
import com.didi.vestiaireweather.utils.DateConversionUtils;
import com.didi.vestiaireweather.utils.ExtraKeysUtils;
import com.didi.vestiaireweather.utils.StringUtils;
import com.mikepenz.iconics.view.IconicsImageView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by didi on 11/12/2016.
 * "View" layer for Details weather
 */

public class DetailsWeatherFragmentView extends BaseMvpFragmentView
        <ActivityViewCommonOps, MVP_DetailsWeather.RequiredViewOps,
                MVP_DetailsWeather.ProvidedPresenterOps, DetailsWeatherPresenter>
        implements MVP_DetailsWeather.RequiredViewOps {

    public static final String TAG = DetailsWeatherFragmentView.class.getSimpleName();

    private WeatherDayVestiaire currentWeatherDay;

    @BindView(R.id.weather_details_tv_city)
    TextView tvCity;
    @BindView(R.id.weather_details_tv_desc)
    TextView tvDesc;
    @BindView(R.id.weather_details_iv_icon)
    IconicsImageView ivWeatherIcon;
    @BindView(R.id.weather_details_tv_temp)
    TextView tvTemp;
    @BindView(R.id.weather_details_tv_humidity)
    TextView tvHumidity;
    @BindView(R.id.weather_details_tv_pressure)
    TextView tvPressure;
    @BindView(R.id.weather_details_tv_wind_speed)
    TextView tvWindSpeed;
    @BindView(R.id.weather_details_tv_wind_direction)
    TextView tvWindDirection;
    @BindView(R.id.weather_details_cv_more)
    CardView cvMore;
    @BindView(R.id.weather_details_ll_loading)
    LinearLayout progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(DetailsWeatherPresenter.class, this);
    }

    public static DetailsWeatherFragmentView newInstance(WeatherDayVestiaire weatherDayVestiaire) {
        Bundle args = new Bundle();
        DetailsWeatherFragmentView fragment = new DetailsWeatherFragmentView();
        args.putSerializable(ExtraKeysUtils.EXTRA_OBJECT, weatherDayVestiaire);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_details, container, false);
        ButterKnife.bind(this, view);
        showNavigationIcon(true);
        loadDetailsWeather();
        return view;
    }

    ///INFO: We load now weather from memory but later could be loaded from DV for instance in "Model layer"
    private void loadDetailsWeather(){
        if(!getArguments().isEmpty())
            currentWeatherDay = (WeatherDayVestiaire) getArguments().getSerializable(ExtraKeysUtils.EXTRA_OBJECT);
        showWeatherDetails(currentWeatherDay);
    }

    /**
     * Back from presenter, we can now show data we just collected
     * For later use with DB for instance, already ready ;)
     * @param weatherDayVestiaire details weather
     */
    @Override
    public void showWeatherDetails(WeatherDayVestiaire weatherDayVestiaire) {
        if(weatherDayVestiaire != null) {
            progressBar.setVisibility(View.GONE);
            cvMore.setVisibility(View.VISIBLE);
            bindCurrentWeather(weatherDayVestiaire);
        }
    }

    /**
     * Bind data to views
     * @param weatherDayVestiaire weather data
     */
    private void bindCurrentWeather(WeatherDayVestiaire weatherDayVestiaire) {
        if(DateConversionUtils.isToday(weatherDayVestiaire.getDate()))
            tvCity.setText(getString(R.string.weather_city_today));
        else
            tvCity.setText(getString(R.string.weather_city_date, DateConversionUtils.formatDate(getActivity(), Locale.FRANCE, weatherDayVestiaire.getDate(),
                DateUtils.FORMAT_SHOW_WEEKDAY|DateUtils.FORMAT_SHOW_DATE)));
        tvTemp.setText(getString(R.string.weather_temperature, weatherDayVestiaire.getTempDay()));
        tvDesc.setText(StringUtils.upperCaseFirstLetter(weatherDayVestiaire.getDesc()));
        tvHumidity.setText(getString(R.string.weather_humidity_with_value, (int) weatherDayVestiaire.getHumidity()));
        tvPressure.setText(getString(R.string.weather_pressure_with_value, (int) weatherDayVestiaire.getPressure()));
        tvWindSpeed.setText(getString(R.string.weather_wind_speed_with_value, (int) weatherDayVestiaire.getWindSpeed()));
        tvWindDirection.setText(getString(R.string.weather_wind_direction_with_value, (int) weatherDayVestiaire.getWindDirection()));

        //Find icon in our weather font icon
        ivWeatherIcon.setIcon(WeatherIconsConverter.getMatchingIconsHashMap().get(weatherDayVestiaire.getWeatherIconId()));
        ivWeatherIcon.setTransitionName(weatherDayVestiaire.getWeatherIconId()+"_"+weatherDayVestiaire.getId());
    }
}
