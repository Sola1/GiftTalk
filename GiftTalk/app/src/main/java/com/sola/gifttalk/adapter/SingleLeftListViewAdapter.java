package com.sola.gifttalk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.SingleBean;

import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class SingleLeftListViewAdapter extends BaseAdapter {
    private List<SingleBean.DataBean.CategoriesBean> categoriesBeen;
    private Context context;
    private int redPos;

    public void setRedPos(int redPos) {
        this.redPos = redPos;
        notifyDataSetChanged();
    }

    public SingleLeftListViewAdapter(Context context) {
        this.context = context;
    }

    public void setCategoriesBeen(List<SingleBean.DataBean.CategoriesBean> categoriesBeen) {
        this.categoriesBeen = categoriesBeen;
        notifyDataSetChanged();
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
        ListViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_left_list_view_single,parent,false);
            holder = new ListViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ListViewHolder) convertView.getTag();
        }
        holder.textView.setText(categoriesBeen.get(position).getName());
        if (redPos == position){
            holder.textView.setTextColor(Color.RED);
        }else {
            holder.textView.setTextColor(Color.BLACK);
        }

        return convertView;
    }
    class ListViewHolder {
        TextView textView;
        public ListViewHolder(View convertView) {
            textView = (TextView) convertView.findViewById(R.id.tv_single_left_list_view);
        }
    }

}
