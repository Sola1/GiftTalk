package com.sola.gifttalk.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.SingleBean;

import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class SingleRightListViewAdapter extends BaseAdapter {
    private Context context;
    private List<SingleBean.DataBean.CategoriesBean> categoriesBeen;
    private int index = 0;

    public SingleRightListViewAdapter(Context context) {
        this.context = context;
    }

    public void setCategoriesBeen(List<SingleBean.DataBean.CategoriesBean> categoriesBeen) {
        this.categoriesBeen = categoriesBeen;
    }

    @Override
    public int getCount() {
        return categoriesBeen != null ? categoriesBeen.size() : 0;

    }

    @Override
    public Object getItem(int position) {
        return categoriesBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RightListViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_right_list_view_single,parent,false);
            holder = new RightListViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (RightListViewHolder) convertView.getTag();
        }

        SingleRightGridViewAdapter singleRightGridViewAdapter = new SingleRightGridViewAdapter(context);
        singleRightGridViewAdapter.setSubcategoriesBeen(categoriesBeen.get(position).getSubcategories());
        holder.gridView.setAdapter(singleRightGridViewAdapter);
        return convertView;

    }
    class RightListViewHolder{
        GridView gridView;
        public RightListViewHolder(View convertView) {
            gridView = (GridView) convertView.findViewById(R.id.grid_view_single_right);
        }
    }
}
