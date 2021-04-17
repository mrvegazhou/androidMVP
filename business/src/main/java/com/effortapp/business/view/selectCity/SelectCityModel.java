package com.effortapp.business.view.selectCity;

import com.effortapp.business.data.beans.selectCity.City;
import com.effortapp.corelib.mvp.BaseModel;
import com.effortapp.corelib.net.BaseHttpResult;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class SelectCityModel extends BaseModel implements SelectCityContract.Model {
    @Override
    public Observable<BaseHttpResult<List<City>>> loadCities() {
        return null;
    }
}
