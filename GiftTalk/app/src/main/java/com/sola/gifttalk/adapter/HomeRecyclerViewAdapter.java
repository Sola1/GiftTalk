package com.sola.gifttalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.HomeImageBean;
import com.sola.gifttalk.bean.RefuseBean;
import com.sola.gifttalk.inter.RecyclerItemInter;
import com.sola.gifttalk.tool.BaseViewHoler;
import com.sola.gifttalk.urls.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/14.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHoler> {
    private Context context;
    private final int TYPE_LBT = 1000;
    private final int TYPE_SECOND = 1002;
    private final int TYPE_RECYCLE = 1001;
    private RecyclerItemInter recyclerItemInter;

    private ArrayList<RefuseBean.DataBean.ItemsBean> itemsBeen;
    private List<HomeImageBean.DataBean.SecondaryBannersBean> secondaryBannersBeen;

    private int pos;


    public void setRecyclerItemInter(RecyclerItemInter recyclerItemInter) {
        this.recyclerItemInter = recyclerItemInter;
    }

    public void setSecondaryBannersBeen(List<HomeImageBean.DataBean.SecondaryBannersBean> secondaryBannersBeen) {
        this.secondaryBannersBeen = secondaryBannersBeen;
        notifyDataSetChanged();
    }

    public void setItemsBeen(ArrayList<RefuseBean.DataBean.ItemsBean> itemsBeen) {
        this.itemsBeen = itemsBeen;
        notifyDataSetChanged();
    }


    public HomeRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LBT && pos == 0) {
            return BaseViewHoler.createRecyclerViewHolder(context, parent, R.layout.item_home_banner);
        } else if (viewType == TYPE_SECOND && pos == 0){
            return BaseViewHoler.createRecyclerViewHolder(context,parent,R.layout.item_home_send_type_iv);
        }else {
            return BaseViewHoler.createRecyclerViewHolder(context, parent, R.layout.item_refuse);

        }
    }

    @Override
    public void onBindViewHolder(BaseViewHoler holder, final int position) {
        int type = getItemViewType(position);
        if (type == TYPE_LBT && pos == 0) {
            holder.setBanner(R.id.banner, Urls.BANNER);
        }else if (type == TYPE_SECOND && pos == 0){
            holder.setImage(R.id.iv_home_one,secondaryBannersBeen.get(0).getImage_url());
            holder.setImage(R.id.iv_home_two,secondaryBannersBeen.get(1).getImage_url());
            holder.setImage(R.id.iv_home_three,secondaryBannersBeen.get(2).getImage_url());
            holder.setImage(R.id.iv_home_four,secondaryBannersBeen.get(3).getImage_url());
            holder.setImage(R.id.iv_home_five,secondaryBannersBeen.get(4).getImage_url());
            holder.setImage(R.id.iv_home_six,secondaryBannersBeen.get(5).getImage_url());
        }
        else if (type == TYPE_RECYCLE && pos == 0) {
            holder.setText(R.id.tv_refuse_nick_name, itemsBeen.get(position - 2).getAuthor().getNickname());
            holder.setText(R.id.tv_refuse_author_introduction, itemsBeen.get(position - 2).getAuthor().getIntroduction());
            holder.setText(R.id.tv_refuse_title, itemsBeen.get(position - 2).getTitle());
            holder.setText(R.id.tv_refuse_introduction, itemsBeen.get(position - 2).getIntroduction());
            String count = String.valueOf(itemsBeen.get(position - 2).getLikes_count());
            holder.setText(R.id.tv_refuse_like_count, count);
            holder.setImage(R.id.iv_refuse_cover_image_url, itemsBeen.get(position - 2).getCover_image_url());
            holder.setImage(R.id.iv_refuse_icon,itemsBeen.get(position - 2).getAuthor().getAvatar_url(),context);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerItemInter.onItemJump(position - 2);
                }
            });



        } else {
            holder.setText(R.id.tv_refuse_nick_name, itemsBeen.get(position).getAuthor().getNickname());
            holder.setText(R.id.tv_refuse_author_introduction, itemsBeen.get(position).getAuthor().getIntroduction());
            holder.setText(R.id.tv_refuse_title, itemsBeen.get(position).getTitle());
            holder.setText(R.id.tv_refuse_introduction, itemsBeen.get(position).getIntroduction());
            String count = String.valueOf(itemsBeen.get(position).getLikes_count());
            holder.setText(R.id.tv_refuse_like_count, count);
            holder.setImage(R.id.iv_refuse_cover_image_url, itemsBeen.get(position).getCover_image_url());
            holder.setImage(R.id.iv_refuse_icon,itemsBeen.get(position).getAuthor().getAvatar_url(),context);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerItemInter.onItemJump(position);
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        if (pos == 0){
            return itemsBeen != null ? itemsBeen.size() + 2 : 0;
        }else {
            return itemsBeen != null ? itemsBeen.size() : 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (pos == 0) {
            if (position == 0) {
                return TYPE_LBT;
            } else if(position == 1){
                return TYPE_SECOND;
            } else {
                return TYPE_RECYCLE;
            }
        } else {
            return TYPE_RECYCLE;
        }
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
