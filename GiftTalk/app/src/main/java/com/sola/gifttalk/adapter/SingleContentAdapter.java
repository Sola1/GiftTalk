package com.sola.gifttalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.SingleContent;
import com.sola.gifttalk.tool.BaseViewHoler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */

public class SingleContentAdapter extends RecyclerView.Adapter<BaseViewHoler> {

    private ArrayList<SingleContent.DataBean.ItemsBean> itemsBeen;

    private Context context;

    public SingleContentAdapter(Context context) {
        this.context = context;
    }

    public void setItemsBeen(ArrayList<SingleContent.DataBean.ItemsBean> itemsBeen) {
        this.itemsBeen = itemsBeen;
    }

    @Override
    public BaseViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHoler.createRecyclerViewHolder(context,parent, R.layout.item_single_content_recycler);
    }

    @Override
    public void onBindViewHolder(BaseViewHoler holder, int position) {
        holder.setText(R.id.tv_single_right_content_title,itemsBeen.get(position).getName());
        holder.setImage(R.id.iv_single_right_content,itemsBeen.get(position).getCover_image_url());

    }

    @Override
    public int getItemCount() {
        return itemsBeen != null ? itemsBeen.size() : 0;
    }
}
