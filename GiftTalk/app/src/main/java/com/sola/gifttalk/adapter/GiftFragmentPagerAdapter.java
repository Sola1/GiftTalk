package com.sola.gifttalk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.sola.gifttalk.bean.GiftTabBean;
import com.sola.gifttalk.fragment.GiftFragment;
import com.sola.gifttalk.fragment.GiftReUseFragment;

import java.util.List;

/**
 * Created by dllo on 17/2/16.
 */

public class GiftFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private static List<GiftTabBean.DataBean.RanksBean> ranksBeen;

    public void setRanksBeen(List<GiftTabBean.DataBean.RanksBean> ranksBeen) {
        this.ranksBeen = ranksBeen;
        notifyDataSetChanged();
    }

    public GiftFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GiftReUseFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return ranksBeen != null ? ranksBeen.size() : 0;
    }
    public String getTabs(int position){
        return ranksBeen.get(position).getName();
    }
    public static GiftTabBean.DataBean.RanksBean getMessage(int position){
        return ranksBeen.get(position);
    }

}
