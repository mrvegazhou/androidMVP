package com.effortapp.corelib.mvp;

import com.effortapp.corelib.utils.Preconditions;
import com.trello.rxlifecycle4.LifecycleProvider;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class BasePresenter<M extends IModel,V extends IView> implements IPresenter<V> {
    /**弱引用, 防止内存泄漏*/
    private WeakReference<V> weakReference;
    private V mProxyView;

    private  M mModel;

    private CompositeDisposable compositeDisposable;

    public V getView() {
        return mProxyView;
    }

    /**
     * 获取 Model
     * @return
     */
    protected abstract M createModel();

    public BasePresenter() {
        this.mModel = createModel();
    }

    public M getModel() {
        Preconditions.checkNotNull(mModel, "%s cannot be null", IModel.class.getName());
        return mModel;
    }

    /**
     * 绑定 View
     * @param v
     */
    @Override
    public void attachView(V v) {
        weakReference = new WeakReference<>(v);
        MvpViewHandler viewHandler = new MvpViewHandler(weakReference.get());
        mProxyView = (V) Proxy.newProxyInstance(v.getClass().getClassLoader(), v.getClass().getInterfaces(), viewHandler);
    }

    /**
     * 解除绑定
     */
    @Override
    public void detachView() {
        unDispose();
        if (isViewAttached()) {
            weakReference.clear();
            weakReference = null;
        }
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();// 保证 Activity 结束时取消所有正在执行的订阅
        }
    }

    protected <T> LifecycleProvider<T> getLifecycleProvider() {
        LifecycleProvider<T> provider = null;
        if (null != mProxyView && mProxyView instanceof LifecycleProvider) {
            provider = (LifecycleProvider<T>) mProxyView;
        }
        return provider;
    }

    private class MvpViewHandler implements InvocationHandler {
        private IView mvpView;

        MvpViewHandler(IView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果V层没被销毁, 执行V层的方法.
            if (isViewAttached()) {
                return method.invoke(mvpView, args);
            }
            //P层不需要关注V层的返回值
            return null;
        }
    }

    /**
     * @return P层和V层是否关联.
     */
    public boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

}
