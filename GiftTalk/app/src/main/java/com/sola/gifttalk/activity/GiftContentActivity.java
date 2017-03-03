package com.sola.gifttalk.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.GiftReuseBean;
import com.sola.gifttalk.db.Collect;
import com.sola.gifttalk.db.DBTool;
import com.sola.gifttalk.tool.BaseViewHoler;
import com.sola.gifttalk.tool.MyApp;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 17/2/23.
 */

public class GiftContentActivity extends BaseActivity {
    private WebView webView;
    private Banner banner;
    private TextView shortTv, nameTv, priceTv;
    private ScrollView scrollView;
    private TextView shareTv;
    private CheckBox checkBox;
    private int type = 0;
    private String url;
    private boolean isLoad = false;
    private int id = 1;

    @Override
    public int bindLayout() {
        return R.layout.activity_giftcontent;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.web_view_gift);
        banner = bindView(R.id.banner_gift_content);
        shortTv = bindView(R.id.tv_gift_content_short);
        nameTv = bindView(R.id.tv_gift_content_name);
        priceTv = bindView(R.id.tv_gift_content_fixed_price);
        scrollView = bindView(R.id.scroll_view_gift_content);
        shareTv = bindView(R.id.share);
        checkBox = bindView(R.id.fa);

    }

    @Override
    protected void initData() {
        ShareSDK.initSDK(this);
        showWebView();
        String title = getIntent().getStringExtra("title");
        String name = getIntent().getStringExtra("name");
        ArrayList<String> images = getIntent().getStringArrayListExtra("images");
        String price = getIntent().getStringExtra("price");

        shortTv.setText(title);
        nameTv.setText(name);
        priceTv.setText(price);

        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        banner.isAutoPlay(false);//自动播放
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置小圆点的位置
        banner.start();

        SharedPreferences sharedPreferences = getSharedPreferences("Load",MODE_PRIVATE);
        isLoad = sharedPreferences.getBoolean("isLoad",false);
        Log.d("GiftContentActivity", "isLoad:" + isLoad);

    }

    private void showWebView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);

        webView.setInitialScale(100);

        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);

        url = getIntent().getStringExtra("giftContentUrl");
        webView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
    }

    @Override
    protected void bindEvent() {
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                //scrollView.scrollTo(0,300);
            }
        });
        //分享
        shareTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //如果选中就收藏
                if (isChecked = true) {
                    if (isLoad = true) {
                        //TODO 判断如果登录再操作
                        Collect collect = new Collect();
                        collect.setUrl(url);
                        if (DBTool.getInstance().isSaveCollect(collect) == false) {
                            DBTool.getInstance().insertCollectBean(collect);
                        }
                    } else {
                        //传个id到MainActivity
                        Intent intent = new Intent(GiftContentActivity.this,MainActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                }
                //TODO 再点击取消收藏
                else {

                }
            }
        });


    }

    class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("QQ");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("分享文本写什么");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
