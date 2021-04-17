package com.effortapp.business.data.beans.weather;

public class WeatherLive {
    public static final String CITY_ID_FIELD_NAME = "cityId";
    public static final String WEATHER_FIELD_NAME = "weather";
    public static final String TEMP_FIELD_NAME = "temp";
    public static final String HUMIDITY_FIELD_NAME = "humidity";
    public static final String WIND_FIELD_NAME = "wind";
    public static final String WIND_SPEED_FIELD_NAME = "windSpeed";
    public static final String TIME_FIELD_NAME = "time";

    public static final String WIND_POWER_FIELD_NAME = "windPower";
    public static final String RAIN_FIELD_NAME = "rain";
    public static final String FEELS_TEMP_FIELD_NAME = "feelsTemperature";
    public static final String PRESSURE_FIELD_NAME = "airPressure";

    private String cityId;
    private String weather;//天气情况
    private String temp;//温度
    private String humidity;//湿度
    private String wind;//风向
    private String windSpeed;//风速
    private long time;//发布时间（时间戳）

    private String windPower;//风力
    private String rain;//降雨量(mm)
    private String feelsTemperature;//体感温度(℃)
    private String airPressure;//气压(hPa)

    public WeatherLive() {
    }

    public WeatherLive(String cityId, String weather, String temp, String humidity, String wind, String windSpeed, long time) {

        this.cityId = cityId;
        this.weather = weather;
        this.temp = temp;
        this.humidity = humidity;
        this.wind = wind;
        this.windSpeed = windSpeed;
        this.time = time;
    }

    @Override
    public String toString() {
        return "WeatherLive{" +
                "cityId='" + cityId + '\'' +
                ", weather='" + weather + '\'' +
                ", temp='" + temp + '\'' +
                ", humidity='" + humidity + '\'' +
                ", wind='" + wind + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", time=" + time +
                ", windPower='" + windPower + '\'' +
                ", rain='" + rain + '\'' +
                ", feelsTemperature='" + feelsTemperature + '\'' +
                ", airPressure='" + airPressure + '\'' +
                '}';
    }
}
