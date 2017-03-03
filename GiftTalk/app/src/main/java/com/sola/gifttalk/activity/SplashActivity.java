package com.sola.gifttalk.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import com.sola.gifttalk.R;

/**
 * Created by dllo on 17/2/21.
 */

public class SplashActivity extends BaseActivity {
    private Handler handler;
    private SharedPreferences sharedPreferences;


    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 0){
                    toNext();
                }
                return false;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }).start();


    }

    private void toNext() {
        //获取对象
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        Boolean first = sharedPreferences.getBoolean("isFirst",true);
        //如果是第一次运行程序就把"first"的true改为false
        if (first){
            sharedPreferences.edit().putBoolean("isFirst",false).commit();
            startActivity(new Intent(SplashActivity.this,WelcomeActivity.class));
        }else {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
        }
    }

    @Override
    protected void bindEvent() {

    }
}
