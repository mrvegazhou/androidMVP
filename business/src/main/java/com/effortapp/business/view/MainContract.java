package com.effortapp.business.view;

import com.effortapp.corelib.mvp.IModel;
import com.effortapp.corelib.mvp.IView;

import java.util.List;

public interface MainContract {
    interface View extends IView {
        public void setNavigationVisibility(boolean isVISIBLE);
    }

    interface Model extends IModel {
    }
}
