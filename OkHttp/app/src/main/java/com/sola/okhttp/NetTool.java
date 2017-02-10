package com.sola.okhttp;

/**
 * Created by dllo on 17/2/10.
 */
public class NetTool implements NetInterface{
    private NetInterface netInterface;
    private static NetTool ourInstance;
    //双重校验锁单例模式
    public static NetTool getInstance() {
        if (ourInstance == null){
            synchronized (NetTool.class){
                if (ourInstance == null){
                    ourInstance = new NetTool();
                }
            }
        }
        return ourInstance;
    }

    private NetTool() {
        netInterface = new OkTool();
    }

    @Override
    public void startRequest(String url, CallBack<String> callBack) {
        netInterface.startRequest(url,callBack);
    }

    @Override
    public <T> void startRequest(String url, Class<T> tClass, CallBack<T> callBack) {
        netInterface.startRequest(url,tClass,callBack);
    }
}
