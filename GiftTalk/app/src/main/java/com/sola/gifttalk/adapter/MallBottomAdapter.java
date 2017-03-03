package com.sola.gifttalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.MallBottomBean;
import com.sola.gifttalk.tool.BaseViewHoler;

import java.util.List;

/**
 * Created by dllo on 17/2/17.
 */

public class MallBottomAdapter extends RecyclerView.Adapter<BaseViewHoler> {
    private Context context;
    private List<MallBottomBean.DataBean.ItemsBean> itemsBeen;

    public void setItemsBeen(List<MallBottomBean.DataBean.ItemsBean> itemsBeen) {
        this.itemsBeen = itemsBeen;
        notifyDataSetChanged();
    }

    public MallBottomAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHoler.createRecyclerViewHolder(context,parent, R.layout.item_recycler_view_mall_bottom_recycler);
    }

    @Override
    public void onBindViewHolder(BaseViewHoler holder, int position) {
        holder.setImage(R.id.iv_mall_bottom_image,itemsBeen.get(position ).getCover_image_url());
        holder.setText(R.id.tv_mall_bottom_short,itemsBeen.get(position ).getShort_description());
        holder.setText(R.id.tv_mall_bottom_title,itemsBeen.get(position).getTitle());
        //holder.setText(R.id.tv_mall_bottom_price,itemsBeen.get(position))

    }

    @Override
    public int getItemCount() {
        return itemsBeen != null ? itemsBeen.size() : 0;
    }
}
