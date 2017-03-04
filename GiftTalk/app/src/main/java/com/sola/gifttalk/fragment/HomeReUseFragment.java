package com.sola.gifttalk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.sola.gifttalk.R;
import com.sola.gifttalk.activity.HomeContentActivity;
import com.sola.gifttalk.adapter.HomeFragmentPagerAdapter;
import com.sola.gifttalk.adapter.HomeRecyclerViewAdapter;
import com.sola.gifttalk.bean.ChannelBean;
import com.sola.gifttalk.bean.HomeImageBean;
import com.sola.gifttalk.bean.RefuseBean;
import com.sola.gifttalk.inter.CallBack;
import com.sola.gifttalk.inter.RecyclerItemInter;
import com.sola.gifttalk.tool.NetTool;
import com.sola.gifttalk.urls.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/14.
 */

public class HomeReUseFragment extends BaseFragment {
    private RefuseBean.DataBean.ItemsBean itemsBean;
    private ArrayList<RefuseBean.DataBean.ItemsBean> itemsBeen;
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private HomeRecyclerViewAdapter adapter;
    private List<HomeImageBean.DataBean.SecondaryBannersBean> secondaryBannersBeen;
    private String contentUrl;
    private RefuseBean refuseBean;
    private String url;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_home_reuse;
    }

    @Override
    protected void initView() {
        pullToRefreshRecyclerView = bindView(R.id.recycler_view_refuse);

    }

    @Override
    protected void initData() {
        adapter = new HomeRecyclerViewAdapter(getContext());
        pullToRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        Bundle result = getArguments();
        final ChannelBean.DataBean.ChannelsBean channelsBean = result.getParcelable("channelsBeen");
        int id = channelsBean.getId();
        final int pos = result.getInt("pos");
        url = "http://api.liwushuo.com/v2/channels/" + id + "/items_v2?gender=1&generation=2&limit=20&offset=0";
        //解析首页数据的方法
        loadHomePage(pos);
        //解析首页图片
        NetTool.getInstance().startRequest(Urls.HOME_PICES, HomeImageBean.class, new CallBack<HomeImageBean>() {
            @Override
            public void onSuccess(HomeImageBean response) {
                secondaryBannersBeen = response.getData().getSecondary_banners();
                if (secondaryBannersBeen != null){
                    adapter.setSecondaryBannersBeen(secondaryBannersBeen);
                    pullToRefreshRecyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onError(Throwable e) {
            }
        });


        //是否开启下拉刷新功能
        pullToRefreshRecyclerView.setPullRefreshEnabled(true);
        //是否开启上拉加载功能
        pullToRefreshRecyclerView.setLoadingMoreEnabled(true);
        //设置是否显示上次刷新的时间
        pullToRefreshRecyclerView.displayLastRefreshTime(true);
        //设置刷新回调
        pullToRefreshRecyclerView.setPullToRefreshListener(new PullToRefreshListener() {

            @Override
            public void onRefresh() {
                pullToRefreshRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadHomePage(pos);
                        //刷新完成
                        pullToRefreshRecyclerView.setRefreshComplete();
                    }
                },1500);

            }


            @Override
            public void onLoadMore() {
                pullToRefreshRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshRecyclerView.setLoadMoreComplete();
                        loadHomePage(pos);
                    }
                },1500);



            }
        });
        //主动触发下拉刷新操作
        //pullToRefreshRV.onRefresh();

    }
    //解析首页数据的方法
    private void loadHomePage(final int pos) {
        NetTool.getInstance().startRequest(url, RefuseBean.class, new CallBack<RefuseBean>() {
            @Override
            public void onSuccess(RefuseBean response) {
                itemsBeen = (ArrayList<RefuseBean.DataBean.ItemsBean>) response.getData().getItems();

                refuseBean = response;

                adapter.setItemsBeen(itemsBeen);
                adapter.setPos(pos);

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    protected void bindEvent() {
        adapter.setRecyclerItemInter(new RecyclerItemInter() {
            @Override
            public void onItemJump(int pos) {

                contentUrl = refuseBean.getData().getItems().get(pos).getUrl();
                Intent intent = new Intent(getContext(),HomeContentActivity.class);
                intent.putExtra("contentUrl",contentUrl);
                startActivity(intent);

            }
        });

    }

    public static HomeReUseFragment newInstance(int position) {
        ChannelBean.DataBean.ChannelsBean channelsBeen = HomeFragmentPagerAdapter.getMessage(position);
        Bundle args = new Bundle();
        args.putParcelable("channelsBeen", channelsBeen);
        args.putInt("pos", position);
        HomeReUseFragment fragment = new HomeReUseFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
