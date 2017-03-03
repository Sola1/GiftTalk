package com.sola.gifttalk.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sola.gifttalk.R;
import com.sola.gifttalk.adapter.SingleContentAdapter;

/**
 * Created by dllo on 17/2/28.
 */

public class SingleContentActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private SingleContentAdapter adapter;
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
        adapter = new SingleContentAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void bindEvent() {

    }
}
