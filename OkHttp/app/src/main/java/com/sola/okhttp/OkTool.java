package com.sola.okhttp;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

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
    private OkHttpClient okHttpClient;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Gson gson;

    public OkTool() {
        //初始化OkHttpClient对象
        okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(5, TimeUnit.SECONDS)
                .cache(new Cache(Environment.getExternalStorageDirectory(),10 * 1024 * 1024))//缓存网络数据到SD卡
                .build();
        //初始化Gson对象
        gson = new Gson();
    }
    //请求网络数据
    @Override
    public void startRequest(String url, final CallBack<String> callBack) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String str = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(str);
                    }
                });

            }
        });

    }
    //重载请求网络数据
    @Override
    public <T> void startRequest(String url, final Class<T> tClass, final CallBack<T> callBack) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
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
