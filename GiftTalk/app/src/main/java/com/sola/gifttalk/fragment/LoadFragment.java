package com.sola.gifttalk.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sola.gifttalk.R;
import com.sola.gifttalk.activity.CollectActivity;
import com.sola.gifttalk.activity.GiftContentActivity;
import com.sola.gifttalk.adapter.CollectListViewAdapter;
import com.sola.gifttalk.db.Collect;
import com.sola.gifttalk.db.DBTool;
import com.sola.gifttalk.tool.MyApp;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 17/3/3.
 */

public class LoadFragment extends BaseFragment {
    private ImageView iconIv;
    private TextView nameTv;
    private String icon;
    private String name;
    private ListView listView;
    private CollectListViewAdapter adapter;
    private List<Collect> collectList;
    private String url;
    private WebView webView;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_load;
    }

    @Override
    protected void initView() {
        iconIv = bindView(R.id.iv_load_icon);
        nameTv = bindView(R.id.tv_load_name);
        listView = bindView(R.id.list_view_load);

    }

    @Override
    protected void initData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Load", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("name","没有值");
        icon = sharedPreferences.getString("icon","也没有值");
        Glide.with(MyApp.getContext()).load(icon).bitmapTransform(new CropCircleTransformation(getContext())).into(iconIv);
        nameTv.setText(name);

        collectList = DBTool.getInstance().queryCollect();

        for (Collect collect : collectList) {
            url = collect.getUrl();
        }

        adapter = new CollectListViewAdapter(getContext());

        adapter.setCollectList(collectList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(),CollectActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void bindEvent() {

    }
}
