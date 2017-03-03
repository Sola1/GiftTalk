package com.sola.gifttalk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.sola.gifttalk.R;
import com.sola.gifttalk.activity.GiftContentActivity;
import com.sola.gifttalk.adapter.GiftFragmentPagerAdapter;
import com.sola.gifttalk.adapter.GiftRecyclerViewAdapter;
import com.sola.gifttalk.bean.GiftReuseBean;
import com.sola.gifttalk.bean.GiftTabBean;
import com.sola.gifttalk.inter.CallBack;
import com.sola.gifttalk.inter.RecyclerItemInter;
import com.sola.gifttalk.tool.MyApp;
import com.sola.gifttalk.tool.NetTool;
import com.sola.gifttalk.urls.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/16.
 */

public class GiftReUseFragment extends BaseFragment {
    private GiftReuseBean.DataBean dataBean;
    private RecyclerView recyclerView;
    private GiftRecyclerViewAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private ImageView headIv;
    private List<GiftReuseBean.DataBean.ItemsBean> itemsBeen;
    private GiftReuseBean.DataBean.ItemsBean itemsBean;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_gift_reuse;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.recycler_view_gift_reuse);

    }

    @Override
    protected void initData() {
        adapter = new GiftRecyclerViewAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false));
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        Bundle bundle = getArguments();

        int id = bundle.getInt("ranksBean");
        String url = Urls.LIST + id + Urls.LIST_OTHER;
        NetTool.getInstance().startRequest(url, GiftReuseBean.class, new CallBack<GiftReuseBean>() {
            @Override
            public void onSuccess(GiftReuseBean response) {

                itemsBeen = response.getData().getItems();
                for (int i = 0; i < itemsBeen.size(); i++) {
                    itemsBean = itemsBeen.get(i);
                }

                dataBean = response.getData();
                View header = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.item_recycler_view_gift_head,recyclerView,false);
                headIv = (ImageView) header.findViewById(R.id.iv_gift_head);
                Glide.with(MyApp.getContext()).load(dataBean.getCover_image()).into(headIv);
                adapter.setDataBean(dataBean);
                lRecyclerViewAdapter.addHeaderView(header);
                recyclerView.setAdapter(lRecyclerViewAdapter);

                adapter.setRecyclerItemInter(new RecyclerItemInter() {
                    @Override
                    public void onItemJump(int pos) {
                        Intent intent = new Intent(getContext(), GiftContentActivity.class);
                        String contentUrl = dataBean.getItems().get(pos).getDetail_html();
                        ArrayList<String> images = (ArrayList<String>) dataBean.getItems().get(pos).getImage_urls();
                        String name = dataBean.getItems().get(pos).getName();
                        String title = dataBean.getItems().get(pos).getShort_description();
                        String price = dataBean.getItems().get(pos).getPrice();
                        intent.putExtra("price",price);
                        intent.putExtra("giftContentUrl",contentUrl);
                        intent.putStringArrayListExtra("images",images);
                        intent.putExtra("name",name);
                        intent.putExtra("title",title);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }
        });


    }

    @Override
    protected void bindEvent() {

    }

    public static GiftReUseFragment newInstance(int position) {
        GiftTabBean.DataBean.RanksBean ranksBean = GiftFragmentPagerAdapter.getMessage(position);
        Bundle args = new Bundle();
        args.putInt("ranksBean", ranksBean.getId());
        GiftReUseFragment fragment = new GiftReUseFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
