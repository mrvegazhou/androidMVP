package com.effortapp.business.view.Social;

import com.effortapp.corelib.mvp.BasePresenter;

public class SocialPresenter extends BasePresenter<SocialContract.Model, SocialContract.View> {
    @Override
    protected SocialContract.Model createModel() {
        return new SocialModel();
    }

    public void requestData(){

    }
}
