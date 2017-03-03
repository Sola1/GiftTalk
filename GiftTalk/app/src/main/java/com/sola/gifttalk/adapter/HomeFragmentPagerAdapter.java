package com.sola.gifttalk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sola.gifttalk.bean.ChannelBean;
import com.sola.gifttalk.fragment.HomeReUseFragment;

import java.util.ArrayList;


/**
 * Created by dllo on 17/2/14.
 */

public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private static ArrayList<ChannelBean.DataBean.ChannelsBean> channelsBeen;

    public void setChannelsBeen(ArrayList<ChannelBean.DataBean.ChannelsBean> channelsBeen) {
        HomeFragmentPagerAdapter.channelsBeen = channelsBeen;
        notifyDataSetChanged();
    }

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return HomeReUseFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return channelsBeen != null ? channelsBeen.size() : 0;
    }

    public static ChannelBean.DataBean.ChannelsBean getMessage(int position) {
        return  channelsBeen.get(position);
    }

    public String getTitles(int position) {
        return channelsBeen.get(position).getName();
    }
}
