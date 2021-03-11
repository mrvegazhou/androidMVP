package com.effortapp.corelib.mvp;

public interface IView {

    //显示loading
    void showLoading();

    //隐藏loading
    void hideLoading();

    //显示吐司
    void showError(String msg);
}
