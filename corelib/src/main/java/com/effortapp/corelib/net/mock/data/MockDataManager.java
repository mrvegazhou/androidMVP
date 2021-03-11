package com.effortapp.corelib.net.mock.data;

import android.content.Context;
import android.text.TextUtils;

import java.lang.reflect.Type;

import com.effortapp.corelib.utils.AssetsUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class MockDataManager {
    private static final String DEFAULT_DATA_PATH = "response_demo.json";

    private static class Holder {
        private static final MockDataManager INSTANCE = new MockDataManager();
    }

    public static MockDataManager get() {
        return Holder.INSTANCE;
    }

    private Map<String, ResponseInfo> infoMap = new HashMap<>();

    private Gson gson = new Gson();

    private MockDataManager() {
    }

    public void init(Context context, String path) {
        try {
            Type type = new TypeToken<Map<String, ResponseInfo>>() {
            }.getType();
            String json = AssetsUtil.getAssetsAsString(context,
                    TextUtils.isEmpty(path) ? DEFAULT_DATA_PATH : path);
            infoMap = gson.fromJson(json, type);
        } catch (Exception e) {
            infoMap = new HashMap<>();
            e.printStackTrace();
        }
    }

    public Map<String, ResponseInfo> getInfoMap() {
        return infoMap;
    }
}
