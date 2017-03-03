package com.sola.gifttalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.sola.gifttalk.BuildConfig;
import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.StrategyHeadBean;
import com.sola.gifttalk.tool.BaseViewHoler;

import java.util.ArrayList;

/**
 * Created by dllo on 17/2/16.
 */

public class StrategyHeadRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHoler> {
    private Context context;
    private ArrayList<StrategyHeadBean.DataBean.ColumnsBean> columnsBeen;

    public void setColumnsBeen(ArrayList<StrategyHeadBean.DataBean.ColumnsBean> columnsBeen) {
        this.columnsBeen = columnsBeen;
        notifyDataSetChanged();
    }

    public StrategyHeadRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHoler.createRecyclerViewHolder(context,parent, R.layout.item_recycler_strategy_head_content);
    }

    @Override
    public void onBindViewHolder(BaseViewHoler holder, int position) {

        holder.setImage(R.id.iv_strategy_head_banner_image_url,columnsBeen.get(position).getBanner_image_url());
        holder.setText(R.id.tv_strategy_head_title,columnsBeen.get(position).getTitle());
        holder.setText(R.id.tv_strategy_head_author,columnsBeen.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return columnsBeen != null ? columnsBeen.size() : 0;
    }
}
