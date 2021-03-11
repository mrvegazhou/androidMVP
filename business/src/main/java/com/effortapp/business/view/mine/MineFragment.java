package com.effortapp.business.view.mine;

import com.effortapp.business.databinding.FragmentMineBinding;
import com.effortapp.corelib.base.BaseFragment;

public class MineFragment extends BaseFragment<MinePresenter, FragmentMineBinding> {
    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected MinePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showError(String msg) {

    }
}
