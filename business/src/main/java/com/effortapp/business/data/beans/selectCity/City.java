package com.effortapp.business.data.beans.selectCity;

public class City {

    public static final String ID_FIELD_NAME = "_id";
    public static final String ROOT_FIELD_NAME = "root";
    public static final String PARENT_FIELD_NAME = "parent";
    public static final String CITY_NAME_FIELD_NAME = "name";
    public static final String CITY_NAME_EN_FIELD_NAME = "pinyin";
    public static final String LON_FIELD_NAME = "x";
    public static final String LAT_FIELD_NAME = "y";
    public static final String CITY_ID_FIELD_NAME = "posID";

    private int id;
    private String root;
    private String parent;
    private int cityId;
    private String cityName;
    private String cityNameEn;
    private String lon;
    private String lat;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "id=" + id +
                ", root='" + root + '\'' +
                ", parent='" + parent + '\'' +
                ", cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityNameEn='" + cityNameEn + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
