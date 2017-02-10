package com.sola.okhttp;

/**
 * Created by dllo on 17/2/10.
 */

public interface NetInterface {
    //拉取网络数据
    void startRequest(String url, CallBack<String> callBack);
    //声明这个方法是需要泛型的
    //拉取并解析网络数据
    <T> void startRequest(String url, Class<T> tClass, CallBack<T> callBack);
}
