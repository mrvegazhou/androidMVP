package com.effortapp.business.view.weather.weatherDetail;

import com.effortapp.business.data.beans.TestNews;
import com.effortapp.corelib.mvp.BaseModel;
import com.effortapp.corelib.net.BaseHttpResult;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class WeatherDetailModel extends BaseModel implements WeatheDetailrContract.Model {
    @Override
    public Observable<BaseHttpResult<List<TestNews>>> getWeatherData() {
        return null;
    }
}
