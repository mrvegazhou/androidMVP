package com.effortapp.business.view.selectCity;

import com.effortapp.business.data.beans.selectCity.City;
import com.effortapp.corelib.mvp.BasePresenter;
import com.effortapp.corelib.net.BaseHttpResult;
import com.effortapp.corelib.net.BaseObserver;
import com.effortapp.corelib.rx.RxSchedulers;

import java.util.List;

public class SelectCityPresenter extends BasePresenter<SelectCityContract.Model, SelectCityContract.View> {
    @Override
    protected SelectCityContract.Model createModel() {
        return null;
    }

    public void loadCities() {
        getModel().loadCities()
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<List<City>>(getView()) {
                    @Override
                    public void onSuccess(BaseHttpResult<List<City>> result) {
                        if (result != null) {
                            getView().displayCities(result.getData());
                        }
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });
    }
}
