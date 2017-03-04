package com.sola.gifttalk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sola.gifttalk.R;
import com.sola.gifttalk.adapter.SingleContentAdapter;
import com.sola.gifttalk.bean.SingleBean;
import com.sola.gifttalk.bean.SingleContent;
import com.sola.gifttalk.inter.CallBack;
import com.sola.gifttalk.tool.NetTool;
import com.sola.gifttalk.urls.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */

public class SingleContentActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private SingleContentAdapter adapter;
    private ArrayList<SingleContent.DataBean.ItemsBean> itemsBeen;
    private SingleBean.DataBean.CategoriesBean.SubcategoriesBean subcategoriesBean;
    @Override
    public int bindLayout() {
        return R.layout.activity_singlecontent;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.recycler_view_single_content);

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        Bundle bundle=intent.getBundleExtra("bundle");
       subcategoriesBean= bundle.getParcelable("subcategoriesBeen");


        adapter = new SingleContentAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false));

        String url = Urls.SINGE_SECOND_UP + subcategoriesBean.getId() + Urls.SINGE_SECOND_DOWN;
        NetTool.getInstance().startRequest(url, SingleContent.class, new CallBack<SingleContent>() {
            @Override
            public void onSuccess(SingleContent response) {
                itemsBeen = (ArrayList<SingleContent.DataBean.ItemsBean>) response.getData().getItems();
                adapter.setItemsBeen(itemsBeen);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    protected void bindEvent() {

    }
}
