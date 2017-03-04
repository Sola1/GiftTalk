package com.sola.gifttalk.urls;

/**
 * Created by dllo on 17/2/13.
 */

public class Urls {
    //公共的
    public static final String BASE_URL = "http://api.liwushuo.com/v2/";
    //攻略
    public static final String STRATEGY = BASE_URL + "channel_groups/all";//下半部分
    public static final String STRATEGY_UP_TITLE = BASE_URL + "columns?limit=20&offset=0";//上半部分

    public static final String STRATEGY_BOTTOM_CONTENT = BASE_URL + "channels/111/items_v2?order_by=now&limit=20&offset=0";
    //  频道列表的接口
    public static final String CHANNELS = BASE_URL + "channels/preset?gender=1&generation=4";
    //  轮播图接口
    public static final String BANNER = BASE_URL + "banners?channel=IOS";
    //榜单页tab接口
    public static final String LIST_TITLE = BASE_URL + "ranks_v2/ranks?";
    //榜单页拼接接口
    public static final String LIST = BASE_URL + "ranks_v3/ranks/";
    public static final String LIST_OTHER = "?limit=20&offset=0";
    //商店页
    public static final String MallUP = "http://api.liwushuo.com/v2/shopitem_collections";
    public static final String Malldown = "http://api.liwushuo.com/v2/shop/items?limit=20&offset=0";
    //  单品页接口
    public static final String SINGLE = "http://api.liwushuo.com/v2/item_categories/tree";
    //首页图片接口
    public final static String HOME_PICES="http://api.liwushuo.com/v2/secondary_banners?gender=1&generation=2";
    //搜索页接口
    public final static String SEARCH = "http://api.liwushuo.com/v2/search/hot_words";
    //单品页二级页面
    public static final String SINGE_SECOND_UP = "http://api.liwushuo.com/v2/item_subcategories/";
    public static final String SINGE_SECOND_DOWN = "/items?limit=20&offset=0";
    //攻略下半部分二级界面
    public static final String STRATEGY_BOTTOM_CONTENT_UP = "http://api.liwushuo.com/v2/channels/";
    public static final String STRATEGY_BOTTOM_CONTENT_DOWN ="/items_v2?order_by=now&limit=20&offset=0";




}
