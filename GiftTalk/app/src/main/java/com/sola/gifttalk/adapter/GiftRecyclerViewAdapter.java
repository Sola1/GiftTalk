package com.sola.gifttalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.GiftReuseBean;
import com.sola.gifttalk.inter.RecyclerItemInter;

/**
 * Created by dllo on 17/2/16.
 */

public class GiftRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private GiftReuseBean.DataBean dataBean;
    private Context context;
    private RecyclerItemInter recyclerItemInter;

    public void setRecyclerItemInter(RecyclerItemInter recyclerItemInter) {
        this.recyclerItemInter = recyclerItemInter;
    }

    public GiftRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setDataBean(GiftReuseBean.DataBean dataBean) {
        this.dataBean = dataBean;
        Log.d("GiftRecyclerViewAdapter", "dataBean.getItems().size():" + dataBean.getItems().size());
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View itemView = null;

        itemView = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_gift_bottom, parent, false);
        holder = new BottomViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

                BottomViewHolder bottomViewHolder = (BottomViewHolder) holder;
                Glide.with(context).load(dataBean.getItems().get(position).getCover_image_url()).into(bottomViewHolder.bottomIv);
                bottomViewHolder.desTv.setText(dataBean.getItems().get(position).getShort_description());
                bottomViewHolder.nameTv.setText(dataBean.getItems().get(position).getName());
                bottomViewHolder.priceTv.setText(dataBean.getItems().get(position).getPrice());
                bottomViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerItemInter.onItemJump(position);
                    }
                });
        }

    @Override
    public int getItemCount() {
        return dataBean.getItems().size() > 0 ? dataBean.getItems().size() : 0;
    }


class BottomViewHolder extends RecyclerView.ViewHolder {
    ImageView bottomIv;
    TextView desTv, nameTv, priceTv;

    public BottomViewHolder(View itemView) {
        super(itemView);
        bottomIv = (ImageView) itemView.findViewById(R.id.iv_gift_bottom);
        desTv = (TextView) itemView.findViewById(R.id.tv_gift_description);
        nameTv = (TextView) itemView.findViewById(R.id.tv_gift_name);
        priceTv = (TextView) itemView.findViewById(R.id.tv_gift_price);
    }
}
}
