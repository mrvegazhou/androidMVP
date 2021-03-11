package com.effortapp.corelib.net;

import java.io.Serializable;

/**
 * @author xuhao
 * @date 2018/6/12 00:58
 * @desc 抽取的一个基类的bean, 直接在泛型中传data就行
 */
public class BaseHttpResult<T> implements Serializable {
    public static final int SUCCESS_CODE = 0;

    /** test**/
    private boolean error;

    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getData() {
        return results;
    }

    public void setData(T data) {
        this.results = data;
    }

    @Override
    public String toString() {
        return "BaseHttpResult{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }

    /**
     * 正常返回
     *
     * @return
     */
    public boolean isSuccessFul() {
        return !isError();
    }
}
