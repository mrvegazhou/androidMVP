package com.effortapp.business.adapter.selectCity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.effortapp.business.R;
import com.effortapp.business.adapter.BaseRecyclerViewAdapter;
import com.effortapp.business.data.beans.selectCity.City;

import java.util.ArrayList;
import java.util.List;
import com.annimon.stream.Stream;

public class SelectCityListAdapter extends BaseRecyclerViewAdapter<SelectCityListAdapter.ViewHolder> implements Filterable {
    private List<City> cities;
    public List<City> mFilterData;//过滤后的数据

    private RecyclerViewFilter filter;

    public SelectCityListAdapter(List<City> cities) {
        this.cities = cities;
        mFilterData = cities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_select_item_city, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        City city = mFilterData.get(position);
        String cityName = city.getCityName();
        String parentName = city.getParent();
        if (!cityName.equals(parentName)) {
            cityName = parentName + "." + cityName;
        }
        holder.cityNameTextView.setText(cityName);
    }

    @Override
    public int getItemCount() {
        return mFilterData == null ? 0 : mFilterData.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new RecyclerViewFilter();
        }
        return filter;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView cityNameTextView;

        ViewHolder(View itemView, SelectCityListAdapter cityListAdapter) {
            super(itemView);
            cityNameTextView = itemView.findViewById(R.id.city_name_text_view);
            itemView.setOnClickListener(v -> cityListAdapter.onItemHolderClick(this));
        }
    }

    private class RecyclerViewFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            if (charSequence == null || charSequence.length() == 0) {
                results.values = null;
                results.count = 0;
            } else {
                String prefixString = charSequence.toString().toLowerCase();
                ArrayList<City> newValues = new ArrayList<>();
                Stream.of(cities)
                        .filter(city -> (city.getCityName().contains(prefixString)
                                || city.getCityNameEn().contains(prefixString) || city.getParent().contains(prefixString)
                                || city.getRoot().contains(prefixString)))
                        .forEach(newValues::add);
                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mFilterData = (List<City>) filterResults.values;
            if (filterResults.count > 0) {
                notifyDataSetChanged();//重绘当前可见区域
            } else {
                mFilterData = cities;
                notifyDataSetChanged();//会重绘控件（还原到初始状态）
            }
        }
    }

}
