package com.effortapp.business.view.home;

import com.effortapp.business.data.beans.TestNews;
import com.effortapp.business.repository.RetrofitUtils;
import com.effortapp.corelib.mvp.BaseModel;
import com.effortapp.corelib.net.BaseHttpResult;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class HomeModel extends BaseModel implements HomeContract.Model {
    @Override
    public Observable<BaseHttpResult<List<TestNews>>> getGankData() {
        return RetrofitUtils.getHttpService().getGankData();
    }
}
