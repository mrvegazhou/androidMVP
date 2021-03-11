package com.effortapp.business.data.api;

import com.effortapp.business.data.beans.TestNews;
import com.effortapp.corelib.net.BaseHttpResult;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/data/Android/10/1")
    Observable<BaseHttpResult<List<TestNews>>> getGankData();
}
