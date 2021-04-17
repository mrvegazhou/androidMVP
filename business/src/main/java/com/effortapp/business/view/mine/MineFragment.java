package com.effortapp.business.view.mine;

import android.os.Bundle;

import com.effortapp.business.databinding.MineFragmentBinding;
import com.effortapp.corelib.base.BaseFragment;

public class MineFragment extends BaseFragment<MinePresenter, MineFragmentBinding> {

    private String mTitle;

    @Override
    protected void initData() {

    }

    public static MineFragment getInstance(String title) {
        MineFragment fragment = new MineFragment();
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
    protected MinePresenter createPresenter() {
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
