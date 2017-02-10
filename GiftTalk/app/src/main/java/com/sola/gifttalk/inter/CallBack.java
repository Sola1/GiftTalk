package com.sola.gifttalk.inter;

/**
 * Created by dllo on 17/2/10.
 */

public interface CallBack<T> {
    //请求数据成功时回调的方法 形参类型不确定 所以写成泛型的形式
    void onSuccess(T response);

    //请求数据失败时回调的方法
    void onError(Throwable e);
}
