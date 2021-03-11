package com.effortapp.corelib.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.trello.rxlifecycle4.components.RxFragment;
import com.effortapp.corelib.mvp.BasePresenter;
import com.effortapp.corelib.mvp.IView;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class BaseFragment<T extends BasePresenter, V extends ViewBinding> extends RxFragment implements IView {
    /**
     * 将代理类通用行为抽出来
     */
    protected T mPresenter;

    private BaseActivity mActivity;

    protected V viewBinding;

    /**
     * 缓存Fragment view
     */
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView==null){
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            Class cls = (Class) type.getActualTypeArguments()[1];
            try {
                Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
                viewBinding = (V) inflate.invoke(null, inflater, container, false);
            }  catch (NoSuchMethodException | IllegalAccessException| InvocationTargetException e) {
                e.printStackTrace();
            }
            mRootView = viewBinding.getRoot();

            if (useEventBus()) {
                EventBus.getDefault().register(this);//注册eventBus
            }
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if(parent!=null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter =createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initView();
        initListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) this.getActivity();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (useEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);//注销eventBus
            }
        }
//        initLeakCanary();
        viewBinding = null;
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }





    /**
     * 初始数据的代码写在这个方法中，用于从服务器获取数据
     */
    protected abstract void initData();

    /**
     * 返回一个用于显示界面的布局id
     */
    protected abstract @LayoutRes int getLayoutId();

    protected abstract boolean useEventBus();

    protected abstract T createPresenter();

    /**
     * 初始化View的代码写在这个方法中
     */
    protected abstract void initView();

    /**
     * 初始化监听器的代码写在这个方法中
     */
    protected abstract void initListener();


    @Override
    public void showLoading() {
        mActivity.showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        mActivity.hideLoadingDialog();
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getBaseActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getBaseActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }
}
