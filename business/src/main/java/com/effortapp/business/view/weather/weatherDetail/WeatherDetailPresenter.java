package com.effortapp.business.view.weather.weatherDetail;

import com.effortapp.corelib.mvp.BasePresenter;

public class WeatherDetailPresenter extends BasePresenter<WeatheDetailrContract.Model, WeatheDetailrContract.View> {
    @Override
    protected WeatheDetailrContract.Model createModel() {
        return new WeatherDetailModel();
    }

    public void requestData(){

    }
}
