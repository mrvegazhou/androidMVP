package com.effortapp.business.view.selectCity;

import androidx.appcompat.widget.Toolbar;

import com.effortapp.business.R;
import com.effortapp.corelib.base.BaseActivity;

public class SelectCityActivity  extends BaseActivity {
    Toolbar toolbar;

    @Override
    protected void initView() {
        setContentView(R.layout.home_select_city_activity);
        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            //给左上角图标的左边加上一个返回的图标
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //使左上角图标是否显示
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
