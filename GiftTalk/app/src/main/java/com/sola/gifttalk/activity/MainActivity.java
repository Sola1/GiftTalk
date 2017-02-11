package com.sola.gifttalk.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.sola.gifttalk.R;
import com.sola.gifttalk.fragment.CategoryFragment;
import com.sola.gifttalk.fragment.GiftFragment;
import com.sola.gifttalk.fragment.HomeFragment;
import com.sola.gifttalk.fragment.MallFragment;
import com.sola.gifttalk.fragment.ProfileFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private FragmentManager fragmentManager;
    private RadioGroup radioGroup;


    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
    }

    @Override
    protected void initData() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.add(R.id.frame, new HomeFragment());
        fragmentTransaction1.commit();

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
                fragmentTransaction.replace(R.id.frame, new ProfileFragment());
                break;
        }
        fragmentTransaction.commit();
    }
}
