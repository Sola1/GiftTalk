package com.sola.gifttalk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sola.gifttalk.R;
import com.sola.gifttalk.adapter.StrategyBottomContentAdapter;
import com.sola.gifttalk.bean.StrategyBottomBean;
import com.sola.gifttalk.bean.StrategyBottomCotentBean;
import com.sola.gifttalk.inter.CallBack;
import com.sola.gifttalk.tool.NetTool;
import com.sola.gifttalk.urls.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/25.
 */

public class StrategyBottomActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private StrategyBottomContentAdapter adapter;
    private List<StrategyBottomCotentBean.DataBean.ItemsBean> itemsBeen;
    private StrategyBottomBean.DataBean.ChannelGroupsBean.ChannelsBean channelsBean;
    @Override
    public int bindLayout() {
        return R.layout.activity_strategybottom;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.recycler_view_strategy_bottom_content);

    }

    @Override
    protected void initData() {
        adapter = new StrategyBottomContentAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        Bundle bundle = getIntent().getBundleExtra("bundle");

        channelsBean = bundle.getParcelable("channelsBean");

        String url = Urls.STRATEGY_BOTTOM_CONTENT_UP + channelsBean.getId() + Urls.STRATEGY_BOTTOM_CONTENT_DOWN;

        NetTool.getInstance().startRequest(url, StrategyBottomCotentBean.class, new CallBack<StrategyBottomCotentBean>() {
            @Override
            public void onSuccess(StrategyBottomCotentBean response) {
                itemsBeen = response.getData().getItems();

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
