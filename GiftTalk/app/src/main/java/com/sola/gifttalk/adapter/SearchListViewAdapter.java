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

import com.sola.gifttalk.R;
import com.sola.gifttalk.db.DBTool;
import com.sola.gifttalk.db.Search;

import java.util.List;

/**
 * Created by dllo on 17/3/2.
 */

public class SearchListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Search> searchList;
    private SearchHolder holder;
    private int index;

    public SearchListViewAdapter(Context context) {
        this.context = context;
    }

    public void setSearchList(List<Search> searchList) {
        this.searchList = searchList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return searchList != null ? searchList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return searchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        index = position;
        holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_view_search,parent,false);
            holder = new SearchHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (SearchHolder) convertView.getTag();
        }
       // searchList = DBTool.getInstance().querySearch();
        Log.d("SearchListViewAdapter", "searchList.size():" + searchList.size());
       // holder.textView.setText(searchList.get(position).getThings());




        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBTool.getInstance().deleteByWords(searchList.get(position).getWords());
                searchList.remove(position);
                notifyDataSetChanged();
                Log.d("SearchListViewAdapter", "adapter");

            }
        });
        holder.textView.setText(searchList.get(position).getWords());

        return convertView;
    }

    class SearchHolder{
        TextView textView;
        ImageView imageView;
        public SearchHolder(View convertView) {
            textView = (TextView) convertView.findViewById(R.id.tv_search_history);
            imageView = (ImageView) convertView.findViewById(R.id.iv_search_delete);
        }
    }
}
