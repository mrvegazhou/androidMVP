package com.effortapp.business.adapter.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.effortapp.business.R;
import com.effortapp.business.adapter.BaseRecyclerViewAdapter;
import com.effortapp.business.data.beans.weather.WeatherDetail;

import java.util.List;

public class DetailAdapter extends BaseRecyclerViewAdapter<DetailAdapter.ViewHolder> {

    private List<WeatherDetail> details;

    public DetailAdapter(List<WeatherDetail> details) {
        this.details = details;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_header_weather_item_detail, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherDetail detail = details.get(position);
        holder.detailIconImageView.setImageResource(detail.getIconResourceId());
        holder.detailKeyTextView.setText(detail.getKey());
        holder.detailValueTextView.setText(detail.getValue());
    }

    @Override
    public int getItemCount() {
        return details == null ? 0 : details.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView detailIconImageView;
        TextView detailKeyTextView;
        TextView detailValueTextView;
        ViewHolder(View itemView, DetailAdapter adapter) {
            super(itemView);
            detailIconImageView = itemView.findViewById(R.id.detail_icon_image_view);
            detailKeyTextView = itemView.findViewById(R.id.detail_key_text_view);
            detailValueTextView = itemView.findViewById(R.id.detail_value_text_view);
            itemView.setOnClickListener(v -> adapter.onItemHolderClick(DetailAdapter.ViewHolder.this));
        }
    }
}
