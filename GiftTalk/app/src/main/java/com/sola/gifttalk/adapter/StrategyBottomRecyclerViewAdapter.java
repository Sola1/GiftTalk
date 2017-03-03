package com.sola.gifttalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.sola.gifttalk.BuildConfig;
import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.StrategyBottomBean;
import com.sola.gifttalk.inter.RecyclerItemInter;
import com.sola.gifttalk.tool.BaseViewHoler;
import com.sola.gifttalk.urls.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/16.
 */

public class StrategyBottomRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHoler> {
    private Context context;
    private List<StrategyBottomBean.DataBean.ChannelGroupsBean.ChannelsBean> channelsBeen;
    private RecyclerItemInter itemInter;


    public void setItemInter(RecyclerItemInter itemInter) {
        this.itemInter = itemInter;
        notifyDataSetChanged();
    }

    public void setChannelsBeen(List<StrategyBottomBean.DataBean.ChannelGroupsBean.ChannelsBean> channelsBeen) {
        this.channelsBeen = channelsBeen;
    }

    public StrategyBottomRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHoler.createRecyclerViewHolder(context, parent, R.layout.item_recycler_strategy_bottom_content);
    }

    @Override
    public void onBindViewHolder(BaseViewHoler holder, final int position) {
        holder.setImage(R.id.iv_strategy_bottom_content, channelsBeen.get(position).getCover_image_url());
        holder.getView(R.id.iv_strategy_bottom_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemInter.onItemJump(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return channelsBeen != null ? 6 : 0;
    }
}
