package com.sola.gifttalk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;
    private List<HomeImageBean.DataBean.SecondaryBannersBean> secondaryBannersBeen;
    private String contentUrl;
    private RefuseBean refuseBean;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_home_reuse;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.recycler_view_refuse);

    }

    @Override
    protected void initData() {
        adapter = new HomeRecyclerViewAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        Bundle result = getArguments();
        final ChannelBean.DataBean.ChannelsBean channelsBean = result.getParcelable("channelsBeen");
        int id = channelsBean.getId();
        final int pos = result.getInt("pos");
        String url = "http://api.liwushuo.com/v2/channels/" + id + "/items_v2?gender=1&generation=2&limit=20&offset=0";

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
        //解析首页图片
        NetTool.getInstance().startRequest(Urls.HOME_PICES, HomeImageBean.class, new CallBack<HomeImageBean>() {
            @Override
            public void onSuccess(HomeImageBean response) {
                secondaryBannersBeen = response.getData().getSecondary_banners();
                if (secondaryBannersBeen != null){
                    adapter.setSecondaryBannersBeen(secondaryBannersBeen);
                    recyclerView.setAdapter(adapter);
                }

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
