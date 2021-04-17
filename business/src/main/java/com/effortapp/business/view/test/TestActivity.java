package com.effortapp.business.view.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.effortapp.business.R;
//import com.effortapp.business.view.weather.WeatherFragment;


public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);


//
//        final LinearLayout headerLL = findViewById(R.id.headerLL);
//        final TextView titleTV = findViewById(R.id.titleTV);
//        final ImageView searchIV=findViewById(R.id.searchIV);
//        final ImageView addIV=findViewById(R.id.addIV);
//
//        final PullExtendLayout pullExtend = this.findViewById(R.id.pull_extend);
//        final ExtendHeaderLayout extendListHeader = this.findViewById(R.id.extend_header);
//        extendListHeader.setStateLayout(new ExtendLayout.StateLayout() {
//            @Override
//            public void onStateChange(IExtendLayout.State state) {
//                switch (state) {
//                    case arrivedHeight:
////                        mainActivity.setNavigationVisibility(false);
//                        headerLL.setVisibility(View.GONE);
//                        titleTV.setTextColor(CommonUtils.getColor(R.color.white));
//                        searchIV.setVisibility(View.GONE);
//                        addIV.setVisibility(View.GONE);
//                        break;
//                    case RESET:
//                        headerLL.setBackgroundResource(R.color.colorPrimary);
//                        titleTV.setTextColor(CommonUtils.getColor(R.color.text_black));
//                        searchIV.setVisibility(View.VISIBLE);
//                        addIV.setVisibility(View.VISIBLE);
//                        break;
//                }
//            }
//        });



    }
}
