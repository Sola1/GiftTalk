package com.sola.gifttalk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 17/2/9.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        initView();
        initData();
        bindEvent();

    }
    //绑定布局的方法
    public abstract int bindLayout();
    //绑定组件的方法
    protected abstract void initView();
    //操作数据的方法
    protected abstract void initData();
    //设置监听的方法
    protected abstract void bindEvent();

    protected  < T extends View > T bindView(int resId){
        return (T) findViewById(resId);
    }

}
