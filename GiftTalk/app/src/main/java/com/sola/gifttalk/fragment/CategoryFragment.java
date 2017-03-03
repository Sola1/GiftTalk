package com.sola.gifttalk.fragment;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.sola.gifttalk.R;
import com.sola.gifttalk.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/10.
 */

public class CategoryFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CategoryAdapter adapter;
    private List<Fragment> fragments;
    private TextView searchTv;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.tab_layout_category);
        viewPager = bindView(R.id.view_pager_category);
        searchTv = bindView(R.id.tv_category_search);

    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new StrategyFragment());
        fragments.add(new SingleFragment());
        adapter = new CategoryAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < adapter.getCount(); i++) {
            tabLayout.getTabAt(i).setText(adapter.getTitles(i));
        }

//        Drawable drawable = getResources().getDrawable(R.drawable.search);
//        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
//        searchTv.setCompoundDrawables(drawable,null,null,null);
//        searchTv.setCompoundDrawablePadding(10);

    }

    @Override
    protected void bindEvent() {

    }
}
