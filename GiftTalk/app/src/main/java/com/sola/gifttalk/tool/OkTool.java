package com.sola.gifttalk.tool;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.sola.gifttalk.inter.CallBack;
import com.sola.gifttalk.inter.NetInterface;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 17/2/10.
 */

public class OkTool implements NetInterface {
    //声明OkHttpClient对象
    private OkHttpClient okHttpClient;
    //声明解析用的Gson对象
    private Gson gson;
    //保证能将子线程中的数据传到主线程中(因为子线程不能操作UI)
    private Handler handler = new Handler(Looper.getMainLooper());

    public OkTool() {
        //第一步:初始化OkHttpClient对象 并对其设置一些属性
        okHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
                .cache(new Cache(Environment.getExternalStorageDirectory(), 10 * 1024 * 1024))
                .retryOnConnectionFailure(true)
                .build();
        //初始化Gson对象
        gson = new Gson();
    }

    @Override
    public void startRequest(String url, CallBack<String> callBack) {

    }

    @Override
    public <T> void startRequest(final String url, final Class<T> tClass, final CallBack<T> callBack) {
        //第二步:初始化请求对象
        Request request = new Request.Builder().url(url).build();
        //第三步:进行异步请求            //⬇️这是进行异步请求的方法
        okHttpClient.newCall(request).enqueue(new Callback() {
            //拉取数据失败
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    callBack.onError(e);
                    }
                });
            }
            //成功拉取数据
            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                //拉取网络数据
                String str = response.body().string();
                //解析网络数据
                final T result = gson.fromJson(str,tClass);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onSuccess(result);
                }
            });
            }
        });

    }
}
