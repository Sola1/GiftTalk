package com.sola.gifttalk.fragment;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.sola.gifttalk.R;
import com.sola.gifttalk.adapter.GiftFragmentPagerAdapter;
import com.sola.gifttalk.bean.GiftTabBean;
import com.sola.gifttalk.inter.CallBack;
import com.sola.gifttalk.tool.NetTool;
import com.sola.gifttalk.urls.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/10.
 */

public class GiftFragment extends BaseFragment {
    private List<GiftTabBean.DataBean.RanksBean> ranksBeen;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private GiftFragmentPagerAdapter adapter;
    private ImageView popIv;
    private PopupWindow popupWindow;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_gift;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.tab_layout_gift);
        viewPager = bindView(R.id.view_pager_gift);
        popIv = bindView(R.id.iv_gift_top);
    }

    @Override
    protected void initData() {

        ranksBeen = new ArrayList<>();
        adapter = new GiftFragmentPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        NetTool.getInstance().startRequest(Urls.LIST_TITLE, GiftTabBean.class, new CallBack<GiftTabBean>() {
            @Override
            public void onSuccess(GiftTabBean response) {
                ranksBeen = response.getData().getRanks();
                adapter.setRanksBeen(ranksBeen);
                Log.e("GiftFragment", "ranksBeen.size():" + ranksBeen.size());
                for (int i = 0; i < adapter.getCount(); i++) {
                    tabLayout.getTabAt(i).setText(adapter.getTabs(i));
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

    @Override
    protected void bindEvent() {
        popIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpWindow();
            }
        });

    }

    private void showPopUpWindow() {
        final View popView = LayoutInflater.from(getContext()).inflate(R.layout.pop_up_window_gift,null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        popupWindow.setTouchable(true);
//        popupWindow.setOutsideTouchable(true);
//
//        popupWindow.getContentView().setFocusableInTouchMode(true);
        popupWindow.getContentView().setFocusable(true);//设置可以弹出窗口
        popupWindow.setAnimationStyle(R.style.gift_pop_window);
        popupWindow.getContentView().setBackgroundColor(Color.WHITE);
        popupWindow.showAtLocation(LayoutInflater.from(getContext()).inflate(R.layout.fragment_gift,null),Gravity.BOTTOM,0,0);
        //点击外部消失
        popView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = popView.findViewById(R.id.layout_pop).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });

    }



    }

