package com.sola.gifttalk.tool;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sola.gifttalk.BuildConfig;
import com.sola.gifttalk.R;
import com.sola.gifttalk.bean.BannerBean;
import com.sola.gifttalk.inter.CallBack;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;


import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 17/2/14.
 */

public class BaseViewHoler extends RecyclerView.ViewHolder {
    private Context context;
    //行布局的view
    private View mView;
    //用来装载id的集合 用法和map类似
    private SparseArray<View> sparseArray;


    public BaseViewHoler(View itemView, Context context) {
        super(itemView);
        mView = itemView;
        sparseArray = new SparseArray<>();
        this.context = context;
    }

    //初始化RecyclerView的
    public static BaseViewHoler createRecyclerViewHolder(Context context, ViewGroup group, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, group, false);
        BaseViewHoler baseViewHoler = new BaseViewHoler(itemView, context);
        return baseViewHoler;
    }

    //初始化listView的
    public static BaseViewHoler createListViewHolder(View view, ViewGroup group, int layoutId) {
        BaseViewHoler baseViewHoler = null;
        if (view == null) {
            view = LayoutInflater.from(group.getContext()).inflate(layoutId, group, false);
            baseViewHoler = new BaseViewHoler(view, group.getContext());
            view.setTag(baseViewHoler);
        } else {
            baseViewHoler = (BaseViewHoler) view.getTag();
        }
        return baseViewHoler;
    }

    //根据id来获得组件
    public <T extends View> T getView(int id) {
        //创建一个view 根据id从集合里取出
        View view = sparseArray.get(id);
        //如果没有取到  那么就通过findViewById的方式找到这个组件,然后将组件存放在集合里
        if (view == null) {
            view = mView.findViewById(id);
            sparseArray.put(id, view);
        }
        //最后返回的这个就是我们想要的组件了
        return (T) view;
    }

    //自己随意的定义方法
    //最基本的设置文字的方法
    public BaseViewHoler setText(int id, String str) {
        TextView textView = getView(id);
        if (str != null) {
            textView.setText(str);
        }
        return this;
    }




    public BaseViewHoler setBanner(int id, String url) {
        final Banner banner = getView(id);
        if (url != null) {
            NetTool.getInstance().startRequest(url, BannerBean.class, new CallBack<BannerBean>() {
                @Override
                public void onSuccess(BannerBean response) {
                    List<String> bannerBean = new ArrayList<String>();
                    for (int i = 0; i < response.getData().getBanners().size(); i++) {
                        bannerBean.add(response.getData().getBanners().get(i).getImage_url());
                    }
                    banner.setImageLoader(new GlideImageLoader());
                    banner.setImages(bannerBean);
                    banner.isAutoPlay(true);//自动播放
                    banner.setDelayTime(3000);//设置延时时间
                    banner.setIndicatorGravity(BannerConfig.CENTER);//设置小圆点的位置
                    banner.start();
                }

                @Override
                public void onError(Throwable e) {

                }
            });


        }
        return null;
    }
    class GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }


    //设置网络图片
    public BaseViewHoler setImage(int id, String url) {
        ImageView imageView = getView(id);
        if (url != null) {
            Glide.with(context).load(url).into(imageView);
        }
        return this;
    }
    //设置圆形图
    public BaseViewHoler setImage(int id, String url,Context context){
        ImageView imageView = getView(id);
        if (url != null){
            Glide.with(context).load(url).bitmapTransform(new CropCircleTransformation(context)).into(imageView);
        }
        return this;
    }

    //设置本地图片
    public BaseViewHoler setImage(int id, int resId) {
        ImageView imageView = getView(id);
        if (resId != 0) {
            imageView.setImageResource(resId);
        }
        return this;
    }


}
