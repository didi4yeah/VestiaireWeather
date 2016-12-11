package com.didi.vestiaireweather.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.didi.vestiaireweather.R;
import com.didi.vestiaireweather.data.converter.WeatherIconsConverter;
import com.didi.vestiaireweather.data.model.vestiaire.WeatherDayVestiaire;
import com.didi.vestiaireweather.utils.DateConversionUtils;
import com.didi.vestiaireweather.utils.StringUtils;
import com.mikepenz.iconics.view.IconicsImageView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by didi on 11/12/16.
 * Forecast list adapter
 */
public class WeatherDayListAdapter extends RecyclerView.Adapter<WeatherDayListAdapter.ViewHolder> {

    private Context context;
    private List<WeatherDayVestiaire> listWeatherDays;

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_weather_cl_root)
        ConstraintLayout rootLayout;
        @BindView(R.id.item_weather_tv_date)
        TextView tvDate;
        @BindView(R.id.item_weather_tv_desc)
        TextView tvDesc;
        @BindView(R.id.item_weather_iv_icon)
        IconicsImageView ivWeatherIcon;
        @BindView(R.id.item_weather_tv_temp)
        TextView tvTemp;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public WeatherDayListAdapter(Context ctx, List<WeatherDayVestiaire> list) {
        context = ctx;
        listWeatherDays = list;
    }

    @Override
    public WeatherDayListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_day, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherDayVestiaire weatherDayVestiaire = listWeatherDays.get(position);
        if(weatherDayVestiaire != null){
            Calendar forecastDate = Calendar.getInstance();
            forecastDate.add(Calendar.DATE, position + 1);
            weatherDayVestiaire.setDate(forecastDate);
            holder.tvDate.setText(StringUtils.upperCaseFirstLetter(DateConversionUtils.formatDate(context, Locale.FRANCE, forecastDate,
                    DateUtils.FORMAT_SHOW_WEEKDAY|DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_ABBREV_MONTH)));
            holder.tvDesc.setText(StringUtils.upperCaseFirstLetter(weatherDayVestiaire.getDesc()));
            holder.tvTemp.setText(context.getString(R.string.weather_temperature, weatherDayVestiaire.getTempDay()));
            //Find icon in our weather font icon
            holder.ivWeatherIcon.setIcon(WeatherIconsConverter.getMatchingIconsHashMap().get(weatherDayVestiaire.getWeatherIconId()));
            //Add transition elements to be used since LOLLIPOP
            //Be careful to have a unique transition name id (especially in viewPager with same layout many times) !!
            holder.ivWeatherIcon.setTransitionName(weatherDayVestiaire.getWeatherIconId()+"_"+weatherDayVestiaire.getId());

            //Change background color alternatively
            if(position % 2 == 0) holder.rootLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.ripple_effect_200));
            else holder.rootLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.ripple_effect_300));
        }
    }

    @Override
    public int getItemCount() {
        return listWeatherDays.size();
    }
}