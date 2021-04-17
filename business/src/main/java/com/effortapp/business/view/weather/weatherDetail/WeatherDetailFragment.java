package com.effortapp.business.view.weather.weatherDetail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.effortapp.business.R;
import com.effortapp.business.adapter.weather.DetailAdapter;
import com.effortapp.business.adapter.weather.ForecastAdapter;
import com.effortapp.business.data.beans.TestNews;
import com.effortapp.business.data.beans.weather.WeatherDetail;
import com.effortapp.business.data.beans.weather.WeatherForecast;
import com.effortapp.business.databinding.HomeHeaderWeatherDetailBinding;
import com.effortapp.corelib.base.BaseFragment;
import com.effortapp.corelib.widget.IndicatorView;

import java.util.ArrayList;
import java.util.List;

public class WeatherDetailFragment extends BaseFragment<WeatherDetailPresenter, HomeHeaderWeatherDetailBinding> implements WeatheDetailrContract.View {

    TextView aqiTextView;
    TextView qualityTextView;
    IndicatorView aqiIndicatorView;
    TextView adviceTextView;
    TextView cityRankTextView;

    //详细天气信息
    RecyclerView detailRecyclerView;
    private DetailAdapter detailAdapter;
    private List<WeatherDetail> weatherDetails;

    //预报
    RecyclerView forecastRecyclerView;
    private List<WeatherForecast> weatherForecasts;
    private ForecastAdapter forecastAdapter;

    //生活指数
    RecyclerView lifeIndexRecyclerView;


    public static WeatherDetailFragment getInstance() {
        return new WeatherDetailFragment();
    }


    @Override
    public void showData(List<TestNews> testNews) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected WeatherDetailPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

        getActivity().setTitle(R.string.app_name);
        setHasOptionsMenu(true);

        //天气详情
        detailRecyclerView.setNestedScrollingEnabled(false);
        detailRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        weatherDetails = new ArrayList<>();
        detailAdapter = new DetailAdapter(weatherDetails);
        detailRecyclerView.setAdapter(detailAdapter);

        //天气预报
        forecastRecyclerView.setNestedScrollingEnabled(false);
        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        weatherForecasts = new ArrayList<>();
        forecastAdapter = new ForecastAdapter(weatherForecasts);
        forecastRecyclerView.setItemAnimator(new DefaultItemAnimator());
        forecastRecyclerView.setAdapter(forecastAdapter);

        //生活指数

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showError(String msg) {

    }
}
