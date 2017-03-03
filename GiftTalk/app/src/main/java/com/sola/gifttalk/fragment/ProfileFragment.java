package com.sola.gifttalk.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mob.tools.utils.UIHandler;
import com.sola.gifttalk.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 17/2/9.
 */

public class ProfileFragment extends BaseFragment {
    private ImageView iconIv;
    private TextView nameTv;
    private String name;
    private LinearLayout layout;
    private String icon;
    private String id;
    private boolean isLoad = false;
    private FragmentTransaction transaction;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView() {
        iconIv = bindView(R.id.iv_pro_icon);
        nameTv = bindView(R.id.tv_pro_name);
//        layout = bindView(R.id.pro_linear);
    }

    @Override
    protected void initData() {
        ShareSDK.initSDK(getContext());
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Override
    protected void bindEvent() {
        logInQQ();
    }

    private void logInQQ() {
        bindView(R.id.iv_pro_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Platform qq = ShareSDK.getPlatform(QQ.NAME);
                //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                qq.setPlatformActionListener(new PlatformActionListener() {

                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        // arg0.getDb().exportData();
                        if (Platform.ACTION_USER_INFOR == arg1) {
                            if (qq.getDb().getUserId() != null) {
                                isLoad = true;
                                icon = qq.getDb().getUserIcon();
                                name = qq.getDb().getUserName();
                                SharedPreferences sharedPreferences = getContext().getSharedPreferences("Load", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("isLoad", isLoad);
                                editor.putString("icon",icon);
                                editor.putString("name",name);
                                editor.commit();
                                //通知已登录状态
                                getContext().sendBroadcast(new Intent("complete"), null);
                                transaction.replace(R.id.frame, new LoadFragment());
                                transaction.commit();
                            }
                        }
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
                //authorize与showUser单独调用一个即可
//                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息
                //移除授权
                //qq.removeAccount(true);
            }
        });
    }

}
