package com.sola.gifttalk.activity;

import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sola.gifttalk.R;

/**
 * Created by dllo on 17/3/3.
 */

public class CollectActivity extends BaseActivity {
    private WebView webView;
    private String url;
    @Override
    public int bindLayout() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.web_view_collect);
    }

    @Override
    protected void initData() {
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

        url = getIntent().getStringExtra("url");
        webView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);

    }

    @Override
    protected void bindEvent() {

    }
}
