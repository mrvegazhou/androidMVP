package com.effortapp.business.view;

import android.content.Intent;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.effortapp.business.R;
import com.effortapp.business.databinding.ActivityMainBinding;
import com.effortapp.business.view.home.HomeFragment;
import com.effortapp.business.view.mine.MineFragment;
import com.effortapp.corelib.base.BaseMvpActivity;

import java.util.ArrayList;

public class MainActivity extends BaseMvpActivity<MainPresenter, ActivityMainBinding> implements MainContract.View {
    private HomeFragment mHomeFragment;
    private MineFragment mMineFragment;

    private String[] mTitles = {"首页", "我的"};
    // 未被选中的图标
    private int[] mIconUnSelectIds = {
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher
    };
    // 被选中的图标
    private int[] mIconSelectIds = {
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher
    };
    private ArrayList mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    // 默认为0;
    private int mCurrIndex = 0;

    @Override
    protected MainPresenter createPresenter() {
        return null;
    }


    /**
     * 初始化底部菜单
     */
    private void initTab() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mCurrIndex = savedInstanceState.getInt("currTabIndex");
        }
        switchFragment(mCurrIndex);
    }

    /**
     * 切换Fragment
     *
     * @param position 下标
     */
    private void switchFragment(int position) {
        // Fragment事务管理器
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideFragments(transaction);
        switch (position) {
            case 0: //首页
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance(mTitles[0]);
                    transaction.add(viewBinding.flContainer.getId(), mHomeFragment, "home");
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            case 2:
//                if (mMineFragment == null) {
//                    mMineFragment = MineFragment.getInstance(mTitles[2]);
//                    transaction.add(R.id.fl_container, mMineFragment, "mine");
//                } else {
//                    transaction.show(mMineFragment);
//                }
                break;
            default:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance(mTitles[0]);
                    transaction.add(R.id.fl_container, mHomeFragment, "home");
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
        }
    }

    /**
     * 隐藏所有的Fragment
     *
     * @param transaction transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (null != mHomeFragment) {
            transaction.hide(mHomeFragment);
        }
        if (null != mMineFragment) {
            transaction.hide(mMineFragment);
        }

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showError(String msg) {

    }
}
