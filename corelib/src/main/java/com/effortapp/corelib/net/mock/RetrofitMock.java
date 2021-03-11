package com.effortapp.corelib.net.mock;

import android.content.Context;

import com.effortapp.corelib.net.mock.data.MockDataManager;

public final class RetrofitMock {
    private RetrofitMock() {
    }

    public static void init(Context context, String path) {
        MockDataManager.get().init(context.getApplicationContext(), path);
    }
}
