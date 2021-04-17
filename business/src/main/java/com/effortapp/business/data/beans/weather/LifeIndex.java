package com.effortapp.business.data.beans.weather;

public class LifeIndex {

    public static final String ID_FIELD_NAME = "_id";
    public static final String CITY_ID_FIELD_NAME = "cityId";
    public static final String NAME_ID_FIELD_NAME = "name";
    public static final String INDEX_ID_FIELD_NAME = "index";
    public static final String DETAILS_ID_FIELD_NAME = "details";

    private long id;//数据库自增长ID
    private String cityId;
    private String name;
    private String index;
    private String details;

    public LifeIndex() {
    }

    public LifeIndex(String cityId, String name, String index, String details) {

        this.cityId = cityId;
        this.name = name;
        this.index = index;
        this.details = details;
    }

    @Override
    public String toString() {
        return "LifeIndex{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", name='" + name + '\'' +
                ", index='" + index + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
