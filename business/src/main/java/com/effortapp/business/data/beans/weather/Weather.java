package com.effortapp.business.data.beans.weather;

import java.util.List;

public class Weather {
    public static final String CITY_ID_FIELD_NAME = "cityId";
    public static final String CITY_NAME_FIELD_NAME = "cityName";
    public static final String CITY_NAME_EN_FIELD_NAME = "cityNameEn";

    private String cityId;
    private String cityName;
    private String cityNameEn;

    private WeatherLive weatherLive;

    private List<WeatherForecast> weatherForecasts;

    private AirQualityLive airQualityLive;

    private List<LifeIndex> lifeIndexes;

    @Override
    public String toString() {
        return "WeatherData{" +
                "aqi=" + airQualityLive +
                ", cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityNameEn='" + cityNameEn + '\'' +
                ", realTime=" + weatherLive +
                ", forecasts=" + weatherForecasts +
                ", lifeIndexes=" + lifeIndexes +
                '}';
    }
}
