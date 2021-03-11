package com.effortapp.corelib.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityToActivity {
    /**
     * 普通Activity之间跳转
     *
     * @param activity activity
     * @param clazz    目标activity
     */
    public static void toActivity(Context activity, Class<? extends Activity> clazz) {
        toActivityForResult(activity, clazz, null, 0);
    }

    /**
     * 普通Activity之间跳转
     *
     * @param activity activity
     * @param clazz    目标activity
     * @param params   携带参数
     */
    public static void toActivity(Context activity, Class<? extends Activity> clazz, Map<String, ?> params) {
        toActivityForResult(activity, clazz, params, 0);
    }

    /**
     * 普通Activity之间跳转
     *
     * @param activity    activity
     * @param clazz       目标activity
     * @param requestCode 请求码  需大于0
     */
    public static void toActivityForResult(Context activity, Class<? extends Activity> clazz, int requestCode) {
        toActivityForResult(activity, clazz, null, requestCode);
    }

    /**
     * 普通Activity之间跳转
     *
     * @param activity    activity
     * @param clazz       目标activity
     * @param params      参数
     * @param requestCode 请求码  需大于0
     */
    public static void toActivityForResult(Context activity, Class<? extends Activity> clazz, Map<String, ?> params, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(activity, clazz);
        assembleParams(intent, params);
        if (requestCode > 0) {
            ((Activity) activity).startActivityForResult(intent, requestCode);
        } else {
            activity.startActivity(intent);
        }
    }

    /**
     * 普通Activity之间跳转
     * ps：兼容从Fragment中使用ActivityForResult。传统方式Fragment中的onActivityResult中无法收到回调
     *
     * @param fragment    当前fragment
     * @param activity    activity
     * @param clazz       目标activity
     * @param requestCode 请求码  需大于0
     */
    public static void toActivityForResult(Fragment fragment, Context activity, Class<? extends Activity> clazz, int requestCode) {
        toActivityForResult(fragment, activity, clazz, null, requestCode);
    }

    /**
     * 在Fragment中调用startActivityForResult
     *
     * @param fragment    跳转所在的Fragment
     * @param activity    activity
     * @param clazz       目标activity
     * @param params      参数
     * @param requestCode 请求码  需大于0
     */
    public static void toActivityForResult(@NotNull Fragment fragment, Context activity, Class<? extends Activity> clazz, Map<String, ?> params, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(activity, clazz);
        assembleParams(intent, params);
        fragment.startActivityForResult(intent, requestCode);
    }

    private static void assembleParams(Intent intent, Map<String, ?> params) {
        if (params != null) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = params.get(key);
                if (value instanceof String) {
                    intent.putExtra(key, (String) value);
                } else if (value instanceof Boolean) {
                    intent.putExtra(key, (boolean) value);
                } else if (value instanceof Integer) {
                    intent.putExtra(key, (int) value);
                } else if (value instanceof Float) {
                    intent.putExtra(key, (float) value);
                } else if (value instanceof Double) {
                    intent.putExtra(key, (double) value);
                } else if (value instanceof Long) {
                    intent.putExtra(key, (long) value);
                } else if (value instanceof Short) {
                    intent.putExtra(key, (short) value);
                } else if (value instanceof Bundle) {
                    intent.putExtra(key, (Bundle) value);
                } else if (value instanceof BaseBean) {
                    intent.putExtra(key, (BaseBean) value);
                } else if (value instanceof ArrayList) {
                    intent.putExtra(key, (ArrayList) value);
                } else if (value instanceof HashMap) {
                    intent.putExtra(key, (HashMap) value);
                }

            }
        }
    }
}
