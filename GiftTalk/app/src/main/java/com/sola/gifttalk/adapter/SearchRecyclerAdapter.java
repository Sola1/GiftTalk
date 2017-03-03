package com.sola.gifttalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.HotSearchBean;
import com.sola.gifttalk.inter.RecyclerItemInter;
import com.sola.gifttalk.tool.BaseViewHoler;

import java.util.List;

/**
 * Created by dllo on 17/3/2.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<BaseViewHoler> {
    private HotSearchBean.DataBean dataBean;
    private Context context;
    private RecyclerItemInter inter;

    public void setInter(RecyclerItemInter inter) {
        this.inter = inter;
    }

    public SearchRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setDataBean(HotSearchBean.DataBean dataBean) {
        this.dataBean = dataBean;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHoler.createRecyclerViewHolder(context,parent, R.layout.item_recycler_search);
    }

    @Override
    public void onBindViewHolder(BaseViewHoler holder, final int position) {
        holder.setText(R.id.tv_home_search_recycler,dataBean.getHot_words().get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inter.onItemJump(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataBean.getHot_words()!= null ? dataBean.getHot_words().size() : 0;
    }
}
