package com.effortapp.business.data.beans;

import androidx.fragment.app.Fragment;

/**
 * 底部标签栏标签
 */
public class BottomTab {
    private  int title;
    private  int icon;
    private Fragment fragment;

    public BottomTab(Fragment fragment, int title, int icon) {
        this.title = title;
        this.icon = icon;
        this.fragment = fragment;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
