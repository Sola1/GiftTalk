package com.sola.gifttalk.activity;

import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.sola.gifttalk.R;

/**
 * Created by dllo on 17/2/23.
 */

public class HomeContentActivity extends BaseActivity {
    private ImageView backIv;
    private WebView webView;
    @Override
    public int bindLayout() {
        return R.layout.activity_homecontent;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.web_view_home);
//        backIv = bindView(R.id.iv_home_content_back);

    }

    @Override
    protected void initData() {

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url = getIntent().getStringExtra("contentUrl");
        webView.loadUrl(url);

        Log.d("HomeContentActivity", "backIv:" + backIv);
//        backIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(HomeContentActivity.this, "点击了按钮", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });


    }

    @Override
    protected void bindEvent() {



    }
}
