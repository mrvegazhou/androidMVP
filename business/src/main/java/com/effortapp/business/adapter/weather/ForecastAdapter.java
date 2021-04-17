package com.effortapp.business.adapter.weather;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.effortapp.business.R;
import com.effortapp.business.adapter.BaseRecyclerViewAdapter;
import com.effortapp.business.data.beans.weather.WeatherForecast;

import java.util.List;

public class ForecastAdapter extends BaseRecyclerViewAdapter<ForecastAdapter.ViewHolder> {

    private List<WeatherForecast> weatherForecasts;

    public ForecastAdapter(List<WeatherForecast> weatherForecasts) {
        this.weatherForecasts = weatherForecasts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_header_weather_item_forecast, parent, false);
        return new ViewHolder(itemView, this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ForecastAdapter.ViewHolder holder, int position) {
        WeatherForecast weatherForecast = weatherForecasts.get(position);
        holder.weekTextView.setText(weatherForecast.getWeek());
        holder.dateTextView.setText(weatherForecast.getDate());
        holder.weatherIconImageView.setImageResource(R.mipmap.ic_launcher);
        holder.weatherTextView.setText(TextUtils.isEmpty(weatherForecast.getWeather()) ?
                (weatherForecast.getWeatherDay().equals(weatherForecast.getWeatherNight()) ?
                        weatherForecast.getWeatherDay() : weatherForecast.getWeatherDay() + "转" + weatherForecast.getWeatherNight())
                : weatherForecast.getWeather());
        holder.tempMaxTextView.setText(weatherForecast.getTempMax() + "°");
        holder.tempMinTextView.setText(weatherForecast.getTempMin() + "°");
    }

    @Override
    public int getItemCount() {
        return weatherForecasts == null ? 0 : weatherForecasts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView weekTextView;
        TextView dateTextView;
        ImageView weatherIconImageView;
        TextView weatherTextView;
        TextView tempMaxTextView;
        TextView tempMinTextView;

        ViewHolder(View itemView, ForecastAdapter adapter) {
            super(itemView);
            weekTextView = itemView.findViewById(R.id.week_text_view);
            dateTextView = itemView.findViewById(R.id.date_text_view);
            weatherIconImageView = itemView.findViewById(R.id.weather_icon_image_view);
            weatherTextView = itemView.findViewById(R.id.weather_text_view);
            tempMaxTextView = itemView.findViewById(R.id.temp_max_text_view);
            tempMinTextView = itemView.findViewById(R.id.temp_min_text_view);

            itemView.setOnClickListener(v -> adapter.onItemHolderClick(ViewHolder.this));
        }
    }
}
