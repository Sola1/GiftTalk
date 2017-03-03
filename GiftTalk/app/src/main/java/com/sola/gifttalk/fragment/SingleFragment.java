package com.sola.gifttalk.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.sola.gifttalk.R;
import com.sola.gifttalk.adapter.SingleLeftListViewAdapter;
import com.sola.gifttalk.adapter.SingleRightListViewAdapter;
import com.sola.gifttalk.bean.SingleBean;
import com.sola.gifttalk.inter.CallBack;
import com.sola.gifttalk.tool.NetTool;
import com.sola.gifttalk.urls.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class SingleFragment extends BaseFragment{
    private ListView leftListView,rightListView;
    private SingleLeftListViewAdapter singleLeftListViewAdapter;
    private SingleRightListViewAdapter singleRightListViewAdapter;
    private List<SingleBean.DataBean.CategoriesBean> categoriesBeen;
    private int scrollPos;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_single;
    }

    @Override
    protected void initView() {
        leftListView = bindView(R.id.list_view_single_left);
        rightListView = bindView(R.id.list_view_single_right);

    }

    @Override
    protected void initData() {
        singleLeftListViewAdapter = new SingleLeftListViewAdapter(getContext());
        leftListView.setAdapter(singleLeftListViewAdapter);
        NetTool.getInstance().startRequest(Urls.SINGLE, SingleBean.class, new CallBack<SingleBean>() {
            @Override
            public void onSuccess(SingleBean response) {
                categoriesBeen = response.getData().getCategories();
                singleLeftListViewAdapter.setCategoriesBeen(categoriesBeen);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        singleRightListViewAdapter = new SingleRightListViewAdapter(getContext());
        rightListView.setAdapter(singleRightListViewAdapter);
        NetTool.getInstance().startRequest(Urls.SINGLE, SingleBean.class, new CallBack<SingleBean>() {
            @Override
            public void onSuccess(SingleBean response) {
                categoriesBeen = response.getData().getCategories();
                singleRightListViewAdapter.setCategoriesBeen(categoriesBeen);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

    @Override
    protected void bindEvent() {
        //左边listView的点击事件
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //刷新适配器
                singleLeftListViewAdapter.notifyDataSetChanged();
                //让右边的listView滑动到指定的position
                rightListView.setSelection(position);
                singleLeftListViewAdapter.setRedPos(position);
            }
        });

        //右边listView的滑动事件
        rightListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //获得现在滑动的状态
                scrollPos = scrollState;

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //滑动停止
                if (scrollPos == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) return;
                //左边滑动到指定位置
                leftListView.setSelection(firstVisibleItem);
                singleLeftListViewAdapter.setRedPos(firstVisibleItem);

            }
        });

    }
}
