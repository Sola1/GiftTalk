package com.sola.gifttalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.StrategyBottomCotentBean;
import com.sola.gifttalk.tool.BaseViewHoler;

import java.util.List;

/**
 * Created by dllo on 17/2/25.
 */

public class StrategyBottomContentAdapter extends RecyclerView.Adapter<BaseViewHoler> {
    private List<StrategyBottomCotentBean.DataBean.ItemsBean> itemsBeen;
    private Context context;


    public StrategyBottomContentAdapter(Context context) {
        this.context = context;
    }

    public void setItemsBeen(List<StrategyBottomCotentBean.DataBean.ItemsBean> itemsBeen) {
        this.itemsBeen = itemsBeen;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {


        return BaseViewHoler.createRecyclerViewHolder(context,parent, R.layout.item_recycler_view_strategy_bottom_content);
    }

    @Override
    public void onBindViewHolder(BaseViewHoler holder, int position) {

        holder.setText(R.id.tv_strategy_bottom_content_name, itemsBeen.get(position).getAuthor().getNickname());
        holder.setText(R.id.tv_strategy_bottom_content_introduction,itemsBeen.get(position).getAuthor().getIntroduction());
        holder.setText(R.id.tv_strategy_bottom_content_title,itemsBeen.get(position).getTitle());
        holder.setImage(R.id.iv_strategy_bottom_content_icon,itemsBeen.get(position).getAuthor().getAvatar_url(),context);
        holder.setImage(R.id.iv_strategy_bottom_content_image_url,itemsBeen.get(position).getCover_image_url());
        holder.setText(R.id.tv_strategy_bottom_content_short,itemsBeen.get(position).getShare_msg());
        holder.setText(R.id.tv_strategy_bottom_content_count, String.valueOf(itemsBeen.get(position).getLikes_count()));

    }

    @Override
    public int getItemCount() {
        return itemsBeen != null ? itemsBeen.size() : 0;
    }
}
