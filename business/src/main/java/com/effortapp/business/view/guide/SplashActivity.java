package com.effortapp.business.view.guide;

import android.content.Intent;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.effortapp.business.R;
import com.effortapp.business.utils.Constants;
import com.effortapp.business.view.MainActivity;
import com.effortapp.corelib.app.ActivityToActivity;
import com.effortapp.corelib.base.BaseActivity;
import com.effortapp.corelib.utils.SpUtils;

public class SplashActivity extends BaseActivity {
    @Override
    protected void initView() {
        setTheme(R.style.AppThemeNoTitle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(() -> {
            boolean isFirst = (boolean) SpUtils.get(Constants.SP_IS_FIRST, true);
            if (isFirst) {
                ActivityToActivity.toActivity(this, GuideActivity.class);
            } else {
                ActivityToActivity.toActivity(this, MainActivity.class);
            }
            SplashActivity.this.finish();
        }, 800);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


}
