package com.effortapp.business.view.mine;

import com.effortapp.corelib.mvp.BasePresenter;

public class MinePresenter extends BasePresenter<MineContract.Model, MineContract.View> {
    @Override
    protected MineContract.Model createModel() {
        return new MineModel();
    }

    public void requestData(){

    }
}
