package com.sola.gifttalk.tool;

import com.sola.gifttalk.inter.CallBack;
import com.sola.gifttalk.inter.NetInterface;

/**
 * Created by dllo on 17/2/10.
 */
public class NetTool implements NetInterface {
    private static NetTool ourInstance;
    //声明NetInterface接口对象
    private NetInterface netInterface;

    public static NetTool getInstance() {
        //单例模式 保证了全局只用这一个对象
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
        //在构造方法中对其进行初始化
        netInterface = new OkTool();

    }

    @Override
    public void startRequest(String url, CallBack<String> callBack) {

    }

    @Override
    public <T> void startRequest(String url, Class<T> tClass, CallBack<T> callBack) {
        netInterface.startRequest(url,tClass,callBack);
    }
}
