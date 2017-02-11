package com.sola.gifttalk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class CategoryAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    private String[] titles = new String[]{"攻略", "单品"};


    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        Log.e("CategoryAdapter", "fragments.size():" + fragments.size());
    }

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public String getTitles(int index) {
        return titles[index];
    }

}
