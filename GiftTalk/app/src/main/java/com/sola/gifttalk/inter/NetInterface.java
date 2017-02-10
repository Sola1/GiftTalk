package com.sola.gifttalk.inter;

/**
 * Created by dllo on 17/2/10.
 */

public interface NetInterface {
    //请求数据 形参是url和请求成功传入的数据(因为这个没有解析的操作 只是拉取了数据 所以没有用到实体类(我们在解析的时候需要url和实体类))
    void startRequest(String url, CallBack<String> callBack);
    //请求并且解析数据 形参是url,实体类,解析好的数据
    <T> void startRequest(String url, Class<T> tClass, CallBack<T> callBack);

}
