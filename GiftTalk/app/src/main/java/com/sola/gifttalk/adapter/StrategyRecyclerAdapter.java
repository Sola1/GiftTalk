package com.sola.gifttalk.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sola.gifttalk.BuildConfig;
import com.sola.gifttalk.R;
import com.sola.gifttalk.activity.StrategyBottomActivity;
import com.sola.gifttalk.bean.StrategyBottomBean;
import com.sola.gifttalk.bean.StrategyBottomCotentBean;
import com.sola.gifttalk.bean.StrategyHeadBean;
import com.sola.gifttalk.inter.RecyclerItemInter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/13.
 */

public class StrategyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEAD_VIEW = 0;
    private final int BOTTOM_VIEW = 1;
    private Context context;
    private ArrayList<StrategyHeadBean.DataBean.ColumnsBean> columnsBeen;
    private List<StrategyBottomBean.DataBean.ChannelGroupsBean> channelGroups;


    public void setContext(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    public void setColumnsBeen(ArrayList<StrategyHeadBean.DataBean.ColumnsBean> columnsBeen) {
        this.columnsBeen = columnsBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (channelGroups != null && columnsBeen != null) {
            return 4;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case BOTTOM_VIEW:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_strategy_bottom, parent, false);
                holder = new StrategyBottomHolder(itemView);
                break;
            case HEAD_VIEW:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_strategy_head, parent, false);
                holder = new StrategyHeadHolder(itemView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        switch (type) {
            case BOTTOM_VIEW:
                StrategyBottomHolder strategyBottomHolder = (StrategyBottomHolder) holder;
                StrategyBottomRecyclerViewAdapter strategyBottomRecyclerViewAdapter = new StrategyBottomRecyclerViewAdapter(context);
                strategyBottomHolder.bottomRecyclerView
                        .setLayoutManager(new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false));
                strategyBottomRecyclerViewAdapter.setChannelsBeen(channelGroups.get(position - 1).getChannels());
                strategyBottomHolder.bottomRecyclerView.setAdapter(strategyBottomRecyclerViewAdapter);

                strategyBottomRecyclerViewAdapter.setItemInter(new RecyclerItemInter() {
                    @Override
                    public void onItemJump(int pos) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(context, StrategyBottomActivity.class);
                        bundle.putParcelable("channelsBean",channelGroups.get(position - 1).getChannels().get(pos));
                        intent.putExtra("bundle",bundle);
                        context.startActivity(intent);
                    }
                });
                break;
            case HEAD_VIEW:
                StrategyHeadHolder strategyHeadHolder = (StrategyHeadHolder) holder;
                StrategyHeadRecyclerViewAdapter strategyHeadRecyclerViewAdapter = new StrategyHeadRecyclerViewAdapter(context);
                strategyHeadHolder.headRecyclerView.setLayoutManager(new GridLayoutManager(context, 3, LinearLayoutManager.HORIZONTAL, false));
                strategyHeadRecyclerViewAdapter.setColumnsBeen(columnsBeen);
                strategyHeadHolder.headRecyclerView.setAdapter(strategyHeadRecyclerViewAdapter);

                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return HEAD_VIEW;
        else return BOTTOM_VIEW;


    }

    public void setChannelsBeen(List<StrategyBottomBean.DataBean.ChannelGroupsBean> channel_groups) {
        this.channelGroups = channel_groups;
    }

    class StrategyBottomHolder extends RecyclerView.ViewHolder {
        private RecyclerView bottomRecyclerView;

        public StrategyBottomHolder(View itemView) {
            super(itemView);
            bottomRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_strategy_bottom);
        }
    }

    class StrategyHeadHolder extends RecyclerView.ViewHolder {
        private RecyclerView headRecyclerView;

        public StrategyHeadHolder(View itemView) {
            super(itemView);
            headRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_strategy_head);
        }
    }


}
