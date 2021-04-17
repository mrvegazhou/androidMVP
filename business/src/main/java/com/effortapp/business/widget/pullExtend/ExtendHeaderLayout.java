package com.effortapp.business.widget.pullExtend;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.effortapp.business.R;
import com.effortapp.corelib.utils.DensityUtils;

public class ExtendHeaderLayout extends ExtendLayout {
    private Context context;
    float containerHeight = DensityUtils.dip2px(50);
    float listHeight;
    boolean arrivedHeight = false;
    private CoordinatorLayout canSeeLL;

    private ExpendPoint mExpendPoint;
    private ImageView upIV;
    private NestedScrollView mNestedScrollView;

    public ExtendHeaderLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ExtendHeaderLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    @Override
    protected View createView(Context context, AttributeSet attrs) {
        return LayoutInflater.from(context).inflate(R.layout.home_extend_header, null);
    }

    private void init(final Context context){
        this.context = context;
        //获取屏幕的高度
        DisplayMetrics dm = getResources().getDisplayMetrics();
        listHeight = dm.heightPixels;

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.home_header_weather_detail, this.findViewById(R.id.fragment_weather_container), true);
    }

    private RecyclerView mRecyclerView1;

    protected void bindView(View container) {
        mExpendPoint = findViewById(R.id.expend_point);
        canSeeLL = findViewById(R.id.canSeeLL);
        upIV = findViewById(R.id.upIV);
        upIV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resetLayout!=null){
                    resetLayout.onRest();
                }
            }
        });
        mNestedScrollView = findViewById(R.id.mNestedScrollView);
        childScrollView = mNestedScrollView;
    }

    public void resetHeaderSize(int h) {
        listHeight = h;
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewGroup.LayoutParams layoutParams1 = getLayoutParams();
                layoutParams1.height = (int)listHeight;
                setLayoutParams(layoutParams1);
                ViewGroup.LayoutParams layoutParams = canSeeLL.getLayoutParams();
                layoutParams.height= (int)listHeight;
                canSeeLL.setLayoutParams(layoutParams);
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public int getContentSize() {
        return (int) (containerHeight);
    }

    public int getListSize() {
        return (int) (listHeight);
    }

    @Override
    public void onPull(int offset) {
        if (!arrivedHeight) {
            mExpendPoint.setVisibility(VISIBLE);
            float percent = Math.abs(offset) / containerHeight;
            int moreOffset = Math.abs(offset) - (int) containerHeight;
            if (percent <= 1.0f) {
                mExpendPoint.setPercent(percent);
                mExpendPoint.setTranslationY(-Math.abs(offset) / 2 + mExpendPoint.getHeight() / 2);
                childScrollView.setTranslationY(-containerHeight);
            } else {
                float subPercent = (moreOffset) / (listHeight - containerHeight);
                subPercent = Math.min(1.0f, subPercent);
                mExpendPoint.setTranslationY(-(int) containerHeight / 2 + mExpendPoint.getHeight() / 2 + (int) containerHeight * subPercent / 2);
                mExpendPoint.setPercent(1.0f);
                float alpha = (1 - subPercent * 2);
                mExpendPoint.setAlpha(Math.max(alpha, 0));
                childScrollView.setTranslationY(-(1 - subPercent) * containerHeight);
            }
        }

        if (Math.abs(offset) >= listHeight) {
            mExpendPoint.setVisibility(GONE);
            childScrollView.setTranslationY(-(Math.abs(offset) - listHeight));
        }
    }

    /************************当状态改变时调用的方法 begin *************************/
    @Override
    protected void onReset() {
        mExpendPoint.setVisibility(VISIBLE);
        mExpendPoint.setAlpha(1);
        mExpendPoint.setTranslationY(0);
        childScrollView.setTranslationY(0);
        arrivedHeight = false;
        upIV.setVisibility(GONE);
        if(stateLayout!=null){
            stateLayout.onStateChange(State.RESET);
        }
    }

    @Override
    protected void onReleaseToRefresh() {}

    @Override
    protected void onPullToRefresh() {}

    private int nestedScrollViewTop;
    @Override
    protected void onArrivedHeight() {
        arrivedHeight = true;
        upIV.setVisibility(View.VISIBLE);
        if( nestedScrollViewTop==0 ) {
            int[] intArray = new int[2];
            mNestedScrollView.getLocationOnScreen(intArray);
            nestedScrollViewTop = intArray[1];
        }
        //滑动到canSeeLL的位置
        int[] intArray = new int[2];
        canSeeLL.getLocationOnScreen(intArray);//测量某View相对于屏幕的距离
        int distance = intArray[1] - nestedScrollViewTop;
        mNestedScrollView.fling(distance);//添加上这句滑动才有效
        mNestedScrollView.scrollBy(0, distance);
        if(stateLayout!=null) {
            stateLayout.onStateChange(State.arrivedHeight);
        }
    }

    @Override
    protected void onRefreshing() {
    }

    /************************当状态改变时调用的方法 end *************************/

}
