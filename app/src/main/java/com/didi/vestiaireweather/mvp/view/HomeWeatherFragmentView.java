package com.didi.vestiaireweather.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.didi.vestiaireweather.R;
import com.didi.vestiaireweather.activity.BaseActivity;
import com.didi.vestiaireweather.adapter.WeatherDayListAdapter;
import com.didi.vestiaireweather.data.converter.WeatherIconsConverter;
import com.didi.vestiaireweather.data.model.vestiaire.WeatherDayVestiaire;
import com.didi.vestiaireweather.mvp.MVP_HomeWeather;
import com.didi.vestiaireweather.mvp.presenter.HomeWeatherPresenter;
import com.didi.vestiaireweather.utils.ItemClickSupport;
import com.didi.vestiaireweather.utils.StringUtils;
import com.mikepenz.iconics.view.IconicsImageView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by didi on 11/12/2016.
 * "View" layer for Home weather
 */

public class HomeWeatherFragmentView extends BaseMvpFragmentView
        <ActivityViewCommonOps, MVP_HomeWeather.RequiredViewOps,
                MVP_HomeWeather.ProvidedPresenterOps, HomeWeatherPresenter>
        implements MVP_HomeWeather.RequiredViewOps {

    public static final String TAG = HomeWeatherFragmentView.class.getSimpleName();

    WeatherDayVestiaire currentWeatherDay;
    List<WeatherDayVestiaire> listWeatherDays;

    @BindView(R.id.weather_header_tv_desc)
    TextView tvDesc;
    @BindView(R.id.weather_header_iv_icon)
    IconicsImageView ivWeatherIcon;
    @BindView(R.id.weather_header_tv_temp)
    TextView tvTemp;
    @BindView(R.id.weather_rv_forecast)
    RecyclerView recyclerView;
    @BindView(R.id.weather_home_pb_loading)
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(HomeWeatherPresenter.class, this);
    }

    public static HomeWeatherFragmentView newInstance() {
        return new HomeWeatherFragmentView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_home, container, false);
        ButterKnife.bind(this, view);
        showNavigationIcon(false);
        loadWeatherForecast();
        return view;
    }

    /**
     * Get open weather data from API
     */
    private void loadWeatherForecast() {
        //Here we chose Paris but could be dynamic param since could be changed in "View" layer
        int cityId = 2988507;
        //Here we chose metrics but could be dynamic param since could be changed in "View" layer
        String units = "metric";
        int total = 5; //5 days forecast

        //Load data from API only if we dont have it
        //For instance we have already data if coming from backstack
        if(listWeatherDays == null) getPresenter().getWeatherForecast(cityId, units, total);
        else showWeatherForecast(listWeatherDays);
    }

    /**
     * Back from presenter, we can now show data we just collected
     * @param list dataList
     */
    @Override
    public void showWeatherForecast(List<WeatherDayVestiaire> list) {
        listWeatherDays = list;
        if(listWeatherDays != null && !listWeatherDays.isEmpty()) {
            progressBar.setVisibility(View.GONE);

            //Deal first with current weather in header view...
            if(listWeatherDays.size() == 5)
                currentWeatherDay = listWeatherDays.remove(0); //and remove it so list wont contains it
            if(currentWeatherDay != null) {
                //Add current date
                Calendar forecastDate = Calendar.getInstance();
                currentWeatherDay.setDate(forecastDate);
                bindCurrentWeather(currentWeatherDay);
                ((BaseActivity) getActivity()).onCurrentWeatherLoaded(currentWeatherDay);
            }
            //... and then forecast list without current day
            initRecyclerView(listWeatherDays);
        }
    }

    /**
     * Bind data to views
     * @param weatherDayVestiaire weather data
     */
    private void bindCurrentWeather(WeatherDayVestiaire weatherDayVestiaire) {
        tvTemp.setText(getString(R.string.weather_temperature, weatherDayVestiaire.getTempDay()));
        tvDesc.setText(StringUtils.upperCaseFirstLetter(weatherDayVestiaire.getDesc()));
        //Find icon in our weather font icon
        ivWeatherIcon.setIcon(WeatherIconsConverter.getMatchingIconsHashMap().get(weatherDayVestiaire.getWeatherIconId()));
    }

    private void initRecyclerView(List<WeatherDayVestiaire> listWeatherDays){
        //To improve performance because changes in content
        //do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recyclerLayoutManager);

        RecyclerView.Adapter recyclerAdapter = new WeatherDayListAdapter(getActivity(), listWeatherDays);
        recyclerView.setAdapter(recyclerAdapter);

        //Handle click on specific day in list and open "Details view"
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(
                (recyclerView1, position, v) -> {
                    //Show Details frag with necessary elements
                    IconicsImageView ivWeatherIcon = ButterKnife.findById(v, R.id.item_weather_iv_icon);

                    //Inform activity that user chose a forecast day
                    //Info has to get back there since we need to handle tablet mode
                    ((BaseActivity) getActivity()).onItemSelected(ivWeatherIcon, listWeatherDays.get(position));
                }
        );
    }

    @OnClick(R.id.weather_header_cl_root)
    public void clickCurrentWeather(View view){
        if(currentWeatherDay == null) return;
        IconicsImageView ivWeatherIcon = ButterKnife.findById(view, R.id.weather_header_iv_icon);
        ivWeatherIcon.setTransitionName(currentWeatherDay.getWeatherIconId()+"_"+currentWeatherDay.getId());

        //Inform activity that user chose a forecast day
        //Info has to get back there since we need to handle tablet mode
        ((BaseActivity) getActivity()).onItemSelected(ivWeatherIcon, currentWeatherDay);
    }
}
