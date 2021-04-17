package com.effortapp.corelib.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.effortapp.corelib.cache.cacheUtil.CacheUtil;
import com.effortapp.corelib.cache.cacheUtil.CacheUtilConfig;
import com.effortapp.corelib.cache.cacheUtil.strategy.KeyStoreEncryptStrategy;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class BaseApplication extends Application {
    private RefWatcher mRefWatcher;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //初始化缓存
        CacheUtil.init(this.initCacheConfig(mContext));
        //检测内存泄漏
        initLeakCanary();

        //注册监听每个activity的生命周期,便于堆栈式管理
        registerActivityLifecycleCallbacks(mCallbacks);
    }

    public static Context getContext() {
        return mContext;
    }


    private CacheUtilConfig initCacheConfig(Context mContext) {
        return CacheUtilConfig.builder(mContext)
                .setIEncryptStrategy(new KeyStoreEncryptStrategy(mContext, "cacheUtil"))
                .allowMemoryCache(true)
                .allowEncrypt(false)
                .build();
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        mRefWatcher = LeakCanary.install(this);
    }


    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }


    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            AppManager.getInstance().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            AppManager.getInstance().removeActivity(activity);
        }
    };
}
