package com.effortapp.corelib.utils;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.FrameLayout;

import com.effortapp.corelib.R;
import com.effortapp.corelib.widget.MyBadge;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class BottomNavigationViewHelper {
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        view.setItemTextAppearanceActive(R.style.bottom_selected_text);
        view.setItemTextAppearanceInactive(R.style.bottom_normal_text);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        menuView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
            item.setShifting(false);
        }
    }

    /**
     * 设置第几个下巴是否显示角标
     */
    public static void setBadge(BottomNavigationView bottomNavigationView, int i, int num){
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        //这里就是获取所添加的每一个Tab(或者叫menu)，
        BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
        //先判断是否存在角标
        if(!(itemView.getChildAt(itemView.getChildCount()-1) instanceof MyBadge)){
            MyBadge myBadge=new MyBadge(bottomNavigationView.getContext());
            itemView.addView(myBadge);//不存在就添加角标
        }
        MyBadge myBadge = (MyBadge) itemView.getChildAt(itemView.getChildCount()-1);
        myBadge.setNum(num);
    }

}
