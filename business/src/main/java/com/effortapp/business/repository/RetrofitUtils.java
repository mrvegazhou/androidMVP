package com.effortapp.business.repository;

import com.effortapp.business.data.api.ApiService;
import com.effortapp.corelib.net.BaseRetrofit;

public class RetrofitUtils extends BaseRetrofit {
    private static ApiService httpService;

    /**
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public static ApiService getHttpService() {
        if (httpService == null) {
            httpService = getRetrofit().create(ApiService.class);
        }
        return httpService;
    }


}