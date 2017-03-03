package com.sola.gifttalk.activity;

import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sola.gifttalk.R;
import com.sola.gifttalk.adapter.MallRecyclerViewAdapter;
import com.sola.gifttalk.bean.MallHeadBean;

/**
 * Created by dllo on 17/2/24.
 */

public class MallContentActivity extends BaseActivity {
    private MallHeadBean.DataBean.ItemsBeanX.ItemsBean itemsBean;
    private WebView webView;

    @Override
    public int bindLayout() {
        return R.layout.activity_mallcontent;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.web_view_mall);

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
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);

//        webView.setInitialScale(100);

        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        String mallHeadContentUrl = getIntent().getStringExtra("mallHeadContentUrl");
        webView.loadDataWithBaseURL(null,mallHeadContentUrl,"text/html","utf-8",null);
    }

    @Override
    protected void bindEvent() {


    }
}
