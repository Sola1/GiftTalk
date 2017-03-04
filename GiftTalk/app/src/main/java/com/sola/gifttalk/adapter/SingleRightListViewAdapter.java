package com.sola.gifttalk.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.sola.gifttalk.R;
import com.sola.gifttalk.activity.SingleContentActivity;
import com.sola.gifttalk.bean.SingleBean;
import com.sola.gifttalk.bean.SingleContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class SingleRightListViewAdapter extends BaseAdapter {
    private Context context;
    private List<SingleBean.DataBean.CategoriesBean> categoriesBeen;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Intent intent = new Intent(context, SingleContentActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable("subcategoriesBeen", categoriesBeen.get(position).getSubcategories().get(pos));
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
            }
        });
        return convertView;

    }
    class RightListViewHolder{
        GridView gridView;
        public RightListViewHolder(View convertView) {
            gridView = (GridView) convertView.findViewById(R.id.grid_view_single_right);
        }
    }
}
