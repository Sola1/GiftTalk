package com.sola.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String url = "http://api.liwushuo.com/v2/channels/108/items_v2?gender=1&generation=2&limit=20&offset=0";
    private TextView tv;
    //无论在哪里使用的handler它都在主线程中
    private Handler handler = new Handler(Looper.getMainLooper());
    private ListView listView;
    private MyAdapter adapter;
    private List<Bean.DataBean.ItemsBean> itemsBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsBeen = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new MyAdapter(this);
        listView.setAdapter(adapter);

//        //第一步:初始化OkHttpClient对象
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(5, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true)
//                .build();
//        //第二步:创建请求对象
//        Request request = new Request.Builder().url(url).build();
//        //第三步:异步网络请求             ⬇️这是异步请求的方法
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            //当请求失败的时候调用该方法
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//            //请求成功的时候调用该方法
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
////                Log.e( "onResponse: ", response.body().string());
//                final String data = response.body().string();
//                //初始化gson对象
//                Gson gson = new Gson();
//                //解析操作
//                final Bean bean = gson.fromJson(data,Bean.class);
//                for (Bean.DataBean.ItemsBean itemsBean : bean.getData().getItems()) {
//                    Log.e("MainActivity", itemsBean.getIntroduction());
//                }
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        itemsBeen = bean.getData().getItems();
//                        adapter.setItemsBean(itemsBeen);
//                    }
//                });
//
//
//            }
//        });
        NetTool.getInstance().startRequest(url, Bean.class, new CallBack<Bean>() {
            @Override
            public void onSuccess(Bean response) {
                itemsBeen = response.getData().getItems();
                adapter.setItemsBean(itemsBeen);
            }

            @Override
            public void onError(Throwable e) {

            }
        });


    }
}
