package com.effortapp.business.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.effortapp.business.R;
import com.effortapp.business.databinding.ActivityMainBinding;
import com.effortapp.business.view.Social.SocialFragment;
import com.effortapp.business.view.home.HomeFragment;
import com.effortapp.business.view.mine.MineFragment;
import com.effortapp.corelib.base.BaseMvpActivity;
import com.effortapp.corelib.utils.BottomNavigationViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.noober.background.BackgroundLibrary;

public class MainActivity extends BaseMvpActivity<MainPresenter, ActivityMainBinding> implements MainContract.View {
    private HomeFragment mHomeFragment;
    private MineFragment mMineFragment;
    private SocialFragment mSocialFragment;

    BottomNavigationView navigation;

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_SOCIAL = 1;
    private static final int FRAGMENT_PROFILE = 2;

    @Override
    protected MainPresenter createPresenter() {
        return null;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tools:
                    showFragment(FRAGMENT_HOME);
                    return true;
                case R.id.social:
                    showFragment(FRAGMENT_SOCIAL);
                    return true;
                case R.id.me:
                    showFragment(FRAGMENT_PROFILE);
                    return true;
            }
            return false;
        }
    };

    private void showFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case FRAGMENT_HOME:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance("工具");
                    transaction.add(viewBinding.flContainer.getId(), mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            case FRAGMENT_SOCIAL:
                if (mSocialFragment == null) {
                    mSocialFragment = SocialFragment.getInstance("社交");
                    transaction.add(viewBinding.flContainer.getId(), mSocialFragment);
                } else {
                    transaction.show(mSocialFragment);
                }
                break;
            case FRAGMENT_PROFILE:
                if (mMineFragment == null) {
                    mMineFragment = MineFragment.getInstance("我的");
                    transaction.add(viewBinding.flContainer.getId(), mMineFragment);
                } else {
                    transaction.show(mMineFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有的Fragment
     *
     * @param transaction transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mSocialFragment != null) {
            transaction.hide(mSocialFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
    }

    @Override
    protected void initView() {
        BackgroundLibrary.inject(this);
        navigation = (BottomNavigationView) viewBinding.navigation;
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationViewHelper.disableShiftMode(navigation);
        BottomNavigationViewHelper.setBadge(navigation,0,88);
        BottomNavigationViewHelper.setBadge(navigation,1,66);

        //默认显示HomeFragment
        showFragment(FRAGMENT_HOME);

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

    @Override
    public void setNavigationVisibility(boolean isVISIBLE) {
        if(isVISIBLE){
            navigation.setVisibility(View.VISIBLE);
        }else {
            navigation.setVisibility(View.GONE);
        }
    }
}
