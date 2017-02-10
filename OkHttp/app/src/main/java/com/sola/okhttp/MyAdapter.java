package com.sola.okhttp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by dllo on 17/2/10.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Bean.DataBean.ItemsBean> itemsBean;

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setItemsBean(List<Bean.DataBean.ItemsBean> itemsBean) {
        this.itemsBean = itemsBean;
    }

    @Override
    public int getCount() {
        return itemsBean != null ? itemsBean.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return itemsBean != null ? itemsBean.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_view,parent,false);
            holder = new MyHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyHolder) convertView.getTag();
        }
        holder.textView.setText(itemsBean.get(position).getIntroduction());
        Glide.with(context).load(itemsBean.get(position).getCover_image_url()).into(holder.imageView);



        return convertView;
    }
    class MyHolder{
        ImageView imageView;
        TextView textView;

        public MyHolder(View convertView) {
            imageView = (ImageView) convertView.findViewById(R.id.image_view1);
            textView = (TextView) convertView.findViewById(R.id.text_view1);
        }
    }
}
