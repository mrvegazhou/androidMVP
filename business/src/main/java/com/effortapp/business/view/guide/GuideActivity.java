package com.effortapp.business.view.guide;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.effortapp.business.R;
import com.effortapp.business.view.MainActivity;
import com.effortapp.business.view.guide.adapter.GuideAdapter;
import com.effortapp.corelib.app.ActivityToActivity;
import com.effortapp.corelib.base.BaseActivity;
import com.effortapp.corelib.utils.CommonUtils;

import java.util.ArrayList;

public class GuideActivity extends BaseActivity {

    private ViewPager vp;
    private LinearLayout llPoints;
    private Button experiencedBtn;
    private Button experiencedBtnNow;
    private int lastPointIndex = 0;

    int[] guides = new int[]{R.mipmap.guide_bg1, R.mipmap.guide_bg2, R.mipmap.guide_bg3, R.mipmap.guide_bg4};

    @Override
    protected void initView() {
        setTheme(R.style.AppThemeNoTitle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);

        vp = findViewById(R.id.guideActivity_vp);
        llPoints = findViewById(R.id.guideActivity_ll_points);
        experiencedBtn = findViewById(R.id.guideActivity_bt_experience_now);
        experiencedBtnNow = findViewById(R.id.guideActivity_bt_experience_now);
    }

    @Override
    protected void initListener() {
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                llPoints.getChildAt(lastPointIndex).setEnabled(false);
                //将当前的点选中
                llPoints.getChildAt(position).setEnabled(true);

                if (position==3){
                    experiencedBtn.setVisibility(View.VISIBLE);
                }else {
                    experiencedBtn.setVisibility(View.GONE);
                }
                lastPointIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        experiencedBtnNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GuideActivity.this,"立即体验被点击了",Toast.LENGTH_SHORT).show();
                ActivityToActivity.toActivity(GuideActivity.this, MainActivity.class);
            }
        });
    }

    @Override
    protected void initData() {
        ArrayList<ImageView> imageViews = new ArrayList<>();
        ImageView imageView = null;
        View pointView = null;
        LinearLayout.LayoutParams layoutParams = null;
        for (int i = 0; i < guides.length; i++) {
            //添加图片
            imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(guides[i]);
            imageViews.add(imageView);

            //添加指针
            pointView = new View(this);
            pointView.setBackgroundResource(R.drawable.point_seleoter);
            //指针的宽度和高度
            layoutParams = new LinearLayout.LayoutParams(CommonUtils.dp2px(5), CommonUtils.dp2px(5));
            if (i==0) {
                pointView.setEnabled(true);
            } else {
                //指针距离左边的间距
                layoutParams.leftMargin = CommonUtils.dp2px(5);
                pointView.setEnabled(false);
            }
            llPoints.addView(pointView, layoutParams);
        }
        GuideAdapter guideAdapter = new GuideAdapter(imageViews);
        vp.setAdapter(guideAdapter);
    }
}
