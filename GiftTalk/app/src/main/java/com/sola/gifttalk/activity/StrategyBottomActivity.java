package com.sola.gifttalk.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sola.gifttalk.R;
import com.sola.gifttalk.adapter.StrategyBottomContentAdapter;
import com.sola.gifttalk.bean.StrategyBottomCotentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/25.
 */

public class StrategyBottomActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private StrategyBottomContentAdapter adapter;
    private List<StrategyBottomCotentBean.DataBean.ItemsBean> itemsBeen;
    private StrategyBottomCotentBean.DataBean.ItemsBean itemsBean;

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

        itemsBeen = getIntent().getParcelableArrayListExtra("itemsBeen");

        adapter.setItemsBeen(itemsBeen);

        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void bindEvent() {

    }
}
