package com.sola.gifttalk.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.sola.gifttalk.BuildConfig;
import com.sola.gifttalk.R;
import com.sola.gifttalk.activity.StrategyBottomActivity;
import com.sola.gifttalk.adapter.StrategyBottomRecyclerViewAdapter;
import com.sola.gifttalk.adapter.StrategyHeadRecyclerViewAdapter;
import com.sola.gifttalk.adapter.StrategyRecyclerAdapter;
import com.sola.gifttalk.bean.StrategyBottomBean;
import com.sola.gifttalk.bean.StrategyBottomCotentBean;
import com.sola.gifttalk.bean.StrategyHeadBean;
import com.sola.gifttalk.inter.CallBack;
import com.sola.gifttalk.inter.RecyclerItemInter;
import com.sola.gifttalk.tool.NetTool;
import com.sola.gifttalk.urls.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class StrategyFragment extends BaseFragment {
    private static final String TAG = "StrategyFragment";
    private StrategyRecyclerAdapter adapter;
    private ArrayList<StrategyHeadBean.DataBean.ColumnsBean> columnsBeen;
    private RecyclerView recyclerView;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_strategy;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.recycler_view_strategy);
    }

    @Override
    protected void initData() {
        adapter = new StrategyRecyclerAdapter();
        adapter.setContext(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        NetTool.getInstance().startRequest(Urls.STRATEGY_UP_TITLE, StrategyHeadBean.class, new CallBack<StrategyHeadBean>() {
            @Override
            public void onSuccess(StrategyHeadBean response) {
                columnsBeen = (ArrayList<StrategyHeadBean.DataBean.ColumnsBean>) response.getData().getColumns();
                adapter.setColumnsBeen(columnsBeen);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        NetTool.getInstance().startRequest(Urls.STRATEGY, StrategyBottomBean.class, new CallBack<StrategyBottomBean>() {
            @Override
            public void onSuccess(StrategyBottomBean response) {
                adapter.setChannelsBeen(response.getData().getChannel_groups());
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
