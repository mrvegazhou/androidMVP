package com.effortapp.business.view.home;

import com.effortapp.business.data.beans.TestNews;
import com.effortapp.corelib.mvp.BasePresenter;
import com.effortapp.corelib.net.BaseHttpResult;
import com.effortapp.corelib.net.BaseObserver;
import com.effortapp.corelib.rx.RxSchedulers;

import java.util.List;

public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {

    @Override
    protected HomeContract.Model createModel() {
        return new HomeModel();
    }

    public void requestData(){
        getModel().getGankData().compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<List<TestNews>>(getView()) {
                    @Override
                    public void onSuccess(BaseHttpResult<List<TestNews>> result) {
                        if (result != null) {
                            getView().showData(result.getData());
                        }
                    }
                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                }
        );
    }
}
