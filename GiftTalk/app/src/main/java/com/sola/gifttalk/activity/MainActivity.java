package com.sola.gifttalk.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sola.gifttalk.R;
import com.sola.gifttalk.fragment.CategoryFragment;
import com.sola.gifttalk.fragment.GiftFragment;
import com.sola.gifttalk.fragment.HomeFragment;
import com.sola.gifttalk.fragment.LoadFragment;
import com.sola.gifttalk.fragment.MallFragment;
import com.sola.gifttalk.fragment.ProfileFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private FragmentManager fragmentManager;
    private RadioGroup radioGroup;
    private SharedPreferences sharedPreferences;
    private boolean isLoad = false;
    private MyReceiver myReceiver;
    private int id = 0;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        radioGroup = bindView(R.id.radio_group);
    }


    @Override
    protected void initData() {
        //注册广播接收器
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("complete");
        registerReceiver(myReceiver,filter);

        sharedPreferences = getSharedPreferences("Load", MODE_PRIVATE);
        isLoad = sharedPreferences.getBoolean("isLoad",false);
        Log.d("MainActivity", "isLoad:" + isLoad);

        fragmentManager = getSupportFragmentManager();

        //接收从GiftActivity传过来的id 如果id为1 就让默认显示登录的fragment
        id = getIntent().getIntExtra("id",id);
        Log.d("MainActivity", "id:" + id);
        if(id == 1){
            RadioButton childAt = (RadioButton) radioGroup.getChildAt(4);
            childAt.setChecked(true);
            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
            fragmentTransaction1.add(R.id.frame, new ProfileFragment());
            fragmentTransaction1.commit();
        } else {
            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
            fragmentTransaction1.add(R.id.frame, new HomeFragment());
            fragmentTransaction1.commit();
        }

    }

    @Override
    protected void bindEvent() {
        //radioGroup的点击事件是这样的
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //每个业务对象只能提交一次 所以我们要这样写
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        isLoad = sharedPreferences.getBoolean("isLoad",false);
        Log.d("MainActivity", "isLoad:" + isLoad);
        switch (checkedId) {
            case R.id.rb_home:
                fragmentTransaction.replace(R.id.frame, new HomeFragment());
                break;
            case R.id.rb_gift:
                fragmentTransaction.replace(R.id.frame, new GiftFragment());
                break;
            case R.id.rb_mall:
                fragmentTransaction.replace(R.id.frame, new MallFragment());
                break;
            case R.id.rb_category:
                fragmentTransaction.replace(R.id.frame, new CategoryFragment());
                break;
            case R.id.rb_profile:
                Log.d("MainActivity", "`````````");
                if (isLoad == false){
                    fragmentTransaction.replace(R.id.frame, new ProfileFragment());
                }else {
                    fragmentTransaction.replace(R.id.frame,new LoadFragment());
                }
                break;
        }
        fragmentTransaction.commit();
    }

    //通知刷新登录成功的方法
    private void refresh(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        isLoad = sharedPreferences.getBoolean("isLoad",false);
        if (isLoad == false){
            fragmentTransaction.replace(R.id.frame, new ProfileFragment());
        }else {
            fragmentTransaction.replace(R.id.frame,new LoadFragment());
        }
    }

    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("MyReceiver", "kkkk");
            refresh();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
