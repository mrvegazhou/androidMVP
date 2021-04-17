package com.effortapp.business.data.beans.weather;

public class AirQualityLive {

    public static final String CITY_ID_FIELD_NAME = "cityId";
    public static final String AQI_FIELD_NAME = "aqi";
    public static final String PM25_FIELD_NAME = "pm25";
    public static final String PM10_FIELD_NAME = "pm10";
    public static final String PUBLISH_TIME_FIELD_NAME = "publishTime";
    public static final String ADVICE_FIELD_NAME = "advice";
    public static final String CITY_RANK_FIELD_NAME = "cityRank";
    public static final String QUALITY_FIELD_NAME = "quality";

    public static final String CO_FIELD_NAME = "co";
    public static final String SO2_FIELD_NAME = "so2";
    public static final String NO2_FIELD_NAME = "no2";
    public static final String O3_FIELD_NAME = "o3";
    public static final String PRIMARY_FIELD_NAME = "primary";

    private String cityId;
    private int aqi;
    private int pm25;
    private int pm10;
    private String publishTime;
    private String advice;//建议
    private String cityRank;//城市排名
    private String quality;//空气质量
    private String co;//一氧化碳浓度(mg/m3)
    private String so2;//二氧化硫浓度(μg/m3)
    private String no2;//二氧化氮浓度(μg/m3)
    private String o3;//臭氧浓度(μg/m3)
    private String primary;//首要污染物


    public AirQualityLive() {
    }
}
