package com.effortapp.corelib.net.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.effortapp.corelib.net.BaseHttpResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * GsonConverterBodyFactory
 * 自定义 Gson 转换器
 */
public class GsonConverterBodyFactory extends Converter.Factory {

    private final Gson gson;

    public static GsonConverterBodyFactory create() {
        GsonBuilder builder = new GsonBuilder().
                registerTypeAdapter(BaseHttpResult.class, new GsonResponseDeserializer());
        return create(builder);
    }

    public static GsonConverterBodyFactory create(GsonBuilder builder) {
        return new GsonConverterBodyFactory(builder.create());
    }

    public static GsonConverterBodyFactory create(Gson gson) {
        return new GsonConverterBodyFactory(gson);
    }

    private GsonConverterBodyFactory(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("Gson == null");
        }

        this.gson = gson;
    }

    // 这里用于对Field、FieldMap、Header、Path、Query、QueryMap注解的处理
    // Retrfofit对于上面的几个注解默认使用的是调用toString方法
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonResponseBodyConverter<>(gson, adapter);
    }

    // 在这里创建 从自定类型到ResponseBody 的Converter,不能处理就返回null，
    // 主要用于对Part、PartMap、Body注解的处理
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }
}