package com.sola.gifttalk.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sola.gifttalk.R;
import com.sola.gifttalk.activity.MallContentActivity;
import com.sola.gifttalk.adapter.MallRecyclerViewAdapter;
import com.sola.gifttalk.bean.MallBottomBean;
import com.sola.gifttalk.bean.MallHeadBean;
import com.sola.gifttalk.inter.CallBack;
import com.sola.gifttalk.inter.RecyclerItemInter;
import com.sola.gifttalk.tool.NetTool;
import com.sola.gifttalk.urls.Urls;

import java.util.List;

/**
 * Created by dllo on 17/2/10.
 */

public class MallFragment extends BaseFragment {
    private List<MallHeadBean.DataBean.ItemsBeanX> itemsBeanXList;
    private RecyclerView recyclerView;
    private MallRecyclerViewAdapter adapter;
    private List<MallBottomBean.DataBean.ItemsBean> itemsBeen;
    private List<MallHeadBean.DataBean.ItemsBeanX> itemsBeenX;
    private MallHeadBean.DataBean.ItemsBeanX itemsBeanX;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_mall;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.recycler_view_mall);

    }

    @Override
    protected void initData() {
        adapter = new MallRecyclerViewAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        NetTool.getInstance().startRequest(Urls.MallUP, MallHeadBean.class, new CallBack<MallHeadBean>() {
            @Override
            public void onSuccess(MallHeadBean response) {
                itemsBeanXList =  response.getData().getItems();
                adapter.setItemsBeanXList(itemsBeanXList);
                itemsBeenX =  response.getData().getItems();
                for (int i = 0; i < itemsBeenX.size(); i++) {
                    itemsBeanX = itemsBeenX.get(i);
                }
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable e) {

            }
        });

        NetTool.getInstance().startRequest(Urls.Malldown, MallBottomBean.class, new CallBack<MallBottomBean>() {
            @Override
            public void onSuccess(MallBottomBean response) {
                itemsBeen = response.getData().getItems();
                adapter.setItemsBeen(itemsBeen);
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
                Intent intent = new Intent(getContext(), MallContentActivity.class);
                String mallHeadContent = itemsBeanX.getItems().get(pos).getDetail_html();
                Log.e("MallFragment", itemsBeanX.getItems().get(pos).getDetail_html());
                intent.putExtra("mallHeadContentUrl",mallHeadContent);
                startActivity(intent);
            }
        });

    }
}
