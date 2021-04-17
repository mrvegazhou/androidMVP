package com.effortapp.business.view.Social;

import android.os.Bundle;

import com.effortapp.business.databinding.SocialFragmentBinding;
import com.effortapp.corelib.base.BaseFragment;

public class SocialFragment  extends BaseFragment<SocialPresenter, SocialFragmentBinding>  {
    private String mTitle;

    @Override
    protected void initData() {

    }

    public static SocialFragment getInstance(String title) {
        SocialFragment fragment = new SocialFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        fragment.mTitle = title;
        return fragment;
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected SocialPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showError(String msg) {

    }
}
