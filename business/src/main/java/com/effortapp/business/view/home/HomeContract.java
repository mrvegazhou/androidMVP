package com.effortapp.business.view.home;

import com.effortapp.business.data.beans.TestNews;
import com.effortapp.corelib.mvp.IModel;
import com.effortapp.corelib.mvp.IView;
import com.effortapp.corelib.net.BaseHttpResult;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class HomeContract {
    interface View extends IView {
        void showData(List<TestNews> testNews);
    }

    interface Model extends IModel {
        Observable<BaseHttpResult<List<TestNews>>> getGankData();
    }
}
