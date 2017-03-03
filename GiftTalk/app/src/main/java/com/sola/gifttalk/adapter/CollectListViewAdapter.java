package com.sola.gifttalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sola.gifttalk.R;
import com.sola.gifttalk.db.Collect;

import java.util.List;

/**
 * Created by dllo on 17/3/3.
 */

public class CollectListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Collect> collectList;

    public CollectListViewAdapter(Context context) {
        this.context = context;
    }

    public void setCollectList(List<Collect> collectList) {
        this.collectList = collectList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return collectList != null ? collectList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return collectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CollectHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_view_collect,parent,false);
            holder = new CollectHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (CollectHolder) convertView.getTag();
        }
        holder.textView.setText(collectList.get(position).getUrl());
        return convertView;
    }
    class CollectHolder{
        TextView textView;

        public CollectHolder(View convertView) {
            textView = (TextView) convertView.findViewById(R.id.tv_url_collect);
        }
    }
}
