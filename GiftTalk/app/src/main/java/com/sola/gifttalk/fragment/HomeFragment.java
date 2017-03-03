package com.sola.gifttalk.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.sola.gifttalk.BuildConfig;
import com.sola.gifttalk.R;
import com.sola.gifttalk.activity.SearchActivity;
import com.sola.gifttalk.adapter.HomeFragmentPagerAdapter;
import com.sola.gifttalk.bean.ChannelBean;
import com.sola.gifttalk.bean.HomeImageBean;
import com.sola.gifttalk.bean.HotSearchBean;
import com.sola.gifttalk.inter.CallBack;
import com.sola.gifttalk.tool.NetTool;
import com.sola.gifttalk.urls.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/10.
 */

public class HomeFragment extends BaseFragment {
    private HomeFragmentPagerAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<ChannelBean.DataBean.ChannelsBean> channelsBeen;
    private HotSearchBean.DataBean dataBean;

    @Override
    protected int bindLayout() {

        return R.layout.fragment_home;

    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.view_pager_home);
        tabLayout = bindView(R.id.tab_layout_home);

    }

    @Override
    protected void initData() {
        channelsBeen = new ArrayList<>();
        adapter = new HomeFragmentPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

        NetTool.getInstance().startRequest(Urls.CHANNELS, ChannelBean.class, new CallBack<ChannelBean>() {
            @Override
            public void onSuccess(ChannelBean response) {
                channelsBeen = (ArrayList<ChannelBean.DataBean.ChannelsBean>) response.getData().getChannels();
                    Log.e("HomeFragment", "channelsBeen.size():" + channelsBeen.size());
                adapter.setChannelsBeen(channelsBeen);
                for (int i = 0; i < adapter.getCount(); i++) {
                    tabLayout.getTabAt(i).setText(adapter.getTitles(i));
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        //热门搜索
        NetTool.getInstance().startRequest(Urls.SEARCH, HotSearchBean.class, new CallBack<HotSearchBean>() {
            @Override
            public void onSuccess(HotSearchBean response) {
                dataBean = response.getData();
            }

            @Override
            public void onError(Throwable e) {

            }
        });


    }

    @Override
    protected void bindEvent() {
        bindView(R.id.tv_home_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SearchActivity.class);
                intent.putExtra("dataBean",dataBean);
                startActivity(intent);
            }
        });

    }

}
