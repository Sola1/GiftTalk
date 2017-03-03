package com.sola.gifttalk.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.MallBottomBean;
import com.sola.gifttalk.bean.MallHeadBean;
import com.sola.gifttalk.inter.RecyclerItemInter;

import java.util.List;

/**
 * Created by dllo on 17/2/17.
 */

public class MallRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEAD_IMAGE_VIEW = 0;
    private final int HEAD_RECYCLER_VIEW = 1;
    private final int BOTTOM_RECYCLER_VIEW = 2;
    private Context context;
    private List<MallHeadBean.DataBean.ItemsBeanX> itemsBeanXList;
    private List<MallBottomBean.DataBean.ItemsBean> itemsBeen;
    private RecyclerItemInter recyclerItemInter;

    public void setRecyclerItemInter(RecyclerItemInter recyclerItemInter) {
        this.recyclerItemInter = recyclerItemInter;
    }

    public void setItemsBeen(List<MallBottomBean.DataBean.ItemsBean> itemsBeen) {
        this.itemsBeen = itemsBeen;
        notifyDataSetChanged();
    }

    public void setItemsBeanXList(List<MallHeadBean.DataBean.ItemsBeanX> itemsBeanXList) {
        this.itemsBeanXList = itemsBeanXList;
    }

    public MallRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View itemView = null;
        switch (viewType) {
            case HEAD_RECYCLER_VIEW:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_mall_head, parent, false);
                holder = new HeadRecyclerViewHoldler(itemView);
                break;
            case BOTTOM_RECYCLER_VIEW:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_mall_bottom, parent, false);
                holder = new BottomRecyclerViewHolder(itemView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        switch (type) {

            case HEAD_RECYCLER_VIEW:
                HeadRecyclerViewHoldler headRecyclerViewHoldler = (HeadRecyclerViewHoldler) holder;
                Glide.with(context).load(itemsBeanXList.get(position).getCover_image_url()).into(headRecyclerViewHoldler.headIv);
                headRecyclerViewHoldler.headTv.setText(itemsBeanXList.get(position).getTitle());
                MallHeadAdapter mallHeadAdapter = new MallHeadAdapter(context);
                headRecyclerViewHoldler.headRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                mallHeadAdapter.setItemsBeanList(itemsBeanXList.get(position).getItems());
                headRecyclerViewHoldler.headRecyclerView.setAdapter(mallHeadAdapter);
                headRecyclerViewHoldler.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerItemInter.onItemJump(position);
                    }
                });
                break;
            case BOTTOM_RECYCLER_VIEW:
                BottomRecyclerViewHolder bottomRecyclerViewHolder = (BottomRecyclerViewHolder) holder;
                MallBottomAdapter mallBottomAdapter = new MallBottomAdapter(context);
                bottomRecyclerViewHolder.bottomRcyclerView.setLayoutManager(new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false));
                mallBottomAdapter.setItemsBeen(itemsBeen);
                bottomRecyclerViewHolder.bottomRcyclerView.setAdapter(mallBottomAdapter);
//                bottomRecyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        recyclerItemInter.onItemJump(position);
//                    }
//                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return itemsBeen != null ? itemsBeen.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 1 || position == 2 || position == 3) return HEAD_RECYCLER_VIEW;
        else return BOTTOM_RECYCLER_VIEW;
    }



    class HeadRecyclerViewHoldler extends RecyclerView.ViewHolder {
        RecyclerView headRecyclerView;
        ImageView headIv;
        TextView headTv;

        public HeadRecyclerViewHoldler(View itemView) {
            super(itemView);
            headRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_mall_head);
            headIv = (ImageView) itemView.findViewById(R.id.iv_mall_head);
            headTv = (TextView) itemView.findViewById(R.id.tv_mall_head);
        }
    }

    class BottomRecyclerViewHolder extends RecyclerView.ViewHolder {
        RecyclerView bottomRcyclerView;

        public BottomRecyclerViewHolder(View itemView) {
            super(itemView);
            bottomRcyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_mall_bottom);
        }
    }

}
