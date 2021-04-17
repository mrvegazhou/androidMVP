package com.effortapp.business.view.selectCity;

import com.effortapp.business.data.beans.selectCity.City;
import com.effortapp.corelib.mvp.IModel;
import com.effortapp.corelib.mvp.IView;
import com.effortapp.corelib.net.BaseHttpResult;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface SelectCityContract {
    interface View extends IView {
        void displayCities(List<City> cities);
    }

    interface Model extends IModel {
        Observable<BaseHttpResult<List<City>>> loadCities();
    }
}
