package com.sola.gifttalk.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.SingleBean;

import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class SingleRightGridViewAdapter extends BaseAdapter {
    private List<SingleBean.DataBean.CategoriesBean.SubcategoriesBean> subcategoriesBeen;
    private Context context;

    public SingleRightGridViewAdapter(Context context) {
        this.context = context;
    }

    public void setSubcategoriesBeen(List<SingleBean.DataBean.CategoriesBean.SubcategoriesBean> subcategoriesBeen) {
        this.subcategoriesBeen = subcategoriesBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return subcategoriesBeen != null ? subcategoriesBeen.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return subcategoriesBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_view_single_right,parent,false);
            holder = new GridViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (GridViewHolder) convertView.getTag();
        }
        holder.rightTv.setText(subcategoriesBeen.get(position).getName());
        Glide.with(context).load(subcategoriesBeen.get(position).getIcon_url()).into(holder.rightIv);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  没错 就是在这写监听事件
                Toast.makeText(context, "啦啦啦", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
    class GridViewHolder{
        ImageView rightIv;
        TextView rightTv;
        public GridViewHolder(View convertView) {
            rightIv = (ImageView) convertView.findViewById(R.id.iv_single_right_grid_view);
            rightTv = (TextView) convertView.findViewById(R.id.tv_single_right_grid_view);
        }
    }
}
