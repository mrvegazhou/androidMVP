package com.effortapp.business.view.home;

import android.os.Bundle;
import android.widget.TextView;

import com.effortapp.business.data.beans.TestNews;
import com.effortapp.business.databinding.FragmentHomeBinding;
import com.effortapp.corelib.base.BaseFragment;
import com.effortapp.corelib.utils.ToastUtils;
import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter, FragmentHomeBinding> implements HomeContract.View {

    TextView textView;

    private String mTitle;

    public static HomeFragment getInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        fragment.mTitle = title;
        return fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected HomePresenter createPresenter() {
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
        ToastUtils.showShort(msg);
    }

    @Override
    public void showData(List<TestNews> testNews) {
        ToastUtils.showShort(testNews.get(0).toString());
    }
}
