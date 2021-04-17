package com.effortapp.business.view.selectCity;

import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.effortapp.business.adapter.selectCity.SelectCityListAdapter;
import com.effortapp.business.data.beans.selectCity.City;
import com.effortapp.business.data.setting.WeatherSetting;
import com.effortapp.business.databinding.HomeSelectCityFragmentBinding;
import com.effortapp.corelib.base.BaseFragment;
import com.effortapp.corelib.cache.MmkvHelper;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

public class SelectCityFragment extends BaseFragment<SelectCityPresenter, HomeSelectCityFragmentBinding> implements SelectCityContract.View {

    public List<City> cities;
    public SelectCityListAdapter cityListAdapter;
    RecyclerView recyclerView;

    public static SelectCityFragment newInstance() {
        return new SelectCityFragment();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected SelectCityPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        recyclerView = viewBinding.selectCityList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

        cities = new ArrayList<>();
        cityListAdapter = new SelectCityListAdapter(cities);
        cityListAdapter.setOnItemClickListener((parent, view, position, id) -> {
            City selectedCity = cityListAdapter.mFilterData.get(position);
            MmkvHelper.getInstance().saveStr(WeatherSetting.SETTINGS_CURRENT_CITY_ID, selectedCity.getCityId() + "");
            Toast.makeText(this.getActivity(), selectedCity.getCityName(), Toast.LENGTH_LONG).show();
            getActivity().finish();
        });
        recyclerView.setAdapter(cityListAdapter);
        mPresenter.loadCities();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void displayCities(List<City> cities) {
        this.cities.addAll(cities);
        cityListAdapter.notifyDataSetChanged();
    }
}
