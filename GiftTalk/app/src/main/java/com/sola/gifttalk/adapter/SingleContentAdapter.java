package com.sola.gifttalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sola.gifttalk.R;
import com.sola.gifttalk.tool.BaseViewHoler;

/**
 * Created by dllo on 17/2/28.
 */

public class SingleContentAdapter extends RecyclerView.Adapter<BaseViewHoler> {

    private Context context;

    public SingleContentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHoler.createRecyclerViewHolder(context,parent, R.layout.item_single_content_recycler);
    }

    @Override
    public void onBindViewHolder(BaseViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
