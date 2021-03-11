package com.effortapp.business;

import android.content.Context;
import androidx.multidex.MultiDex;

import com.effortapp.corelib.app.BaseApplication;
import com.effortapp.corelib.net.mock.RetrofitMock;
import com.effortapp.corelib.utils.CommonUtils;

public class MyApplication extends BaseApplication {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Bugly
        initBugly();
        //mock调试
        RetrofitMock.init(this, "mock_demo.json");
    }



    private void initBugly() {
        // 获取当前包名
        String packageName = getApplicationContext().getPackageName();
        // 获取当前进程名
        String processName = CommonUtils.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
//        strategy.setUploadProcess(processName == null || processName.equals(packageName));
//        CrashReport.initCrashReport(getApplicationContext(), MyConstants.BUGLY_ID, false, strategy);
    }

}
