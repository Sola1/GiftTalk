package com.sola.gifttalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.MallHeadBean;

import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class MallHeadAdapter extends RecyclerView.Adapter<MallHeadAdapter.HeadViewHolder> {
    private Context context;
    private List<MallHeadBean.DataBean.ItemsBeanX.ItemsBean> itemsBeanList;

    public void setItemsBeanList(List<MallHeadBean.DataBean.ItemsBeanX.ItemsBean> itemsBeanList) {
        this.itemsBeanList = itemsBeanList;
        notifyDataSetChanged();
    }

    public MallHeadAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HeadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_mall_head_recycler,parent,false);
        HeadViewHolder holder = new HeadViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(HeadViewHolder holder, int position) {
        Glide.with(context).load(itemsBeanList.get(position).getCover_image_url()).into(holder.headRecyclerIv);
        holder.headRecyclerNameTv.setText(itemsBeanList.get(position).getShort_description());
        holder.headRecyclerPriceTv.setText(itemsBeanList.get(position).getSkus().get(0).getPrice());


    }

    @Override
    public int getItemCount() {
        return itemsBeanList != null ? itemsBeanList.size() : 0;
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        ImageView headRecyclerIv;
        TextView headRecyclerNameTv,headRecyclerPriceTv;
        public HeadViewHolder(View itemView) {
            super(itemView);
            headRecyclerIv = (ImageView) itemView.findViewById(R.id.iv_mall_head_recycler);
            headRecyclerNameTv = (TextView) itemView.findViewById(R.id.tv_mall_head__name);
            headRecyclerPriceTv = (TextView) itemView.findViewById(R.id.tv_mall_head__price);
        }
    }
}
