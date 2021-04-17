package com.effortapp.business.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.effortapp.business.R;
import com.effortapp.business.adapter.test.HomeRVadapter;
import com.effortapp.business.data.beans.TestNews;
import com.effortapp.business.databinding.HomeFragmentBinding;
import com.effortapp.business.view.MainActivity;
import com.effortapp.business.view.guide.GuideActivity;
import com.effortapp.business.view.selectCity.SelectCityActivity;
import com.effortapp.business.view.weather.weatherDetail.WeatherDetailFragment;
import com.effortapp.business.widget.pullExtend.ExtendHeaderLayout;
import com.effortapp.business.widget.pullExtend.ExtendLayout;
import com.effortapp.business.widget.pullExtend.IExtendLayout;
import com.effortapp.corelib.app.ActivityToActivity;
import com.effortapp.corelib.base.BaseFragment;
import com.effortapp.corelib.utils.ToastUtils;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter, HomeFragmentBinding> implements HomeContract.View {

    private String mTitle;
    private MainActivity mainActivity;
    private WeatherDetailFragment homeHeaderWeatherFragment;
    private ExtendHeaderLayout extendListHeader;
    private Toolbar toolbar;

    public static HomeFragment getInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        fragment.mTitle = title;
        return fragment;
    }

    @Override
    protected void initData() {
        final List<String> list= Arrays.asList("二狗子","二牛","傻蛋","三多","二狗子","来福","旺财","大傻","刘二根","喜娃","小样","喜洋洋","比卡丘","钢铁侠","三毛","七龙珠");
        HomeRVadapter homeRVadapter=new HomeRVadapter(R.layout.home_chat_item, list);
        RecyclerView myRV = getView().findViewById(R.id.myRV);
        myRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRV.setAdapter(homeRVadapter);
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
        extendListHeader = viewBinding.extendHeader;
        this.hideNavigationView();
        //设置顶部菜单
        toolbar = extendListHeader.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.home_right_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.flush_weather:
                        Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.switc_city:
//                        Intent intent = new Intent(getActivity(), SelectCityActivity.class);
//                        startActivity(intent);
                        ActivityToActivity.toActivity(getActivity(), SelectCityActivity.class);
                        break;
                }
                return true;
            }
        });
    }

    private void hideNavigationView() {
        final LinearLayout headerBar = getActivity().findViewById(R.id.home_header_bar);
        mainActivity = (MainActivity) getActivity();
        extendListHeader.setStateLayout(new ExtendLayout.StateLayout() {
            @Override
            public void onStateChange(IExtendLayout.State state) {
                switch (state){
                    case arrivedHeight:
                        mainActivity.setNavigationVisibility(false);
                        headerBar.setVisibility(View.GONE);
                        break;
                    case RESET:
                        mainActivity.setNavigationVisibility(true);
                        headerBar.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
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
