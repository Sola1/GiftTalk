package com.sola.gifttalk.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dllo on 17/2/16.
 */

public class GiftTabBean implements Parcelable{


    /**
     * code : 200
     * data : {"ranks":[{"id":1,"mark_style":0,"name":"每日推荐","title":"每日推荐好礼：一天都不愿错过 ","update_at":1487174400},{"id":2,"mark_style":1,"name":"TOP100","title":"TOP100榜单，把最受欢迎的好礼打包送上","update_at":1487075957},{"id":3,"mark_style":1,"name":"独立原创榜","title":"独立原创榜，帮你找到独一无二的好礼","update_at":1487076228},{"id":4,"mark_style":1,"name":"新星榜","title":"礼物新星榜，最新最火单品推荐","update_at":1487059791}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    protected GiftTabBean(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<GiftTabBean> CREATOR = new Creator<GiftTabBean>() {
        @Override
        public GiftTabBean createFromParcel(Parcel in) {
            return new GiftTabBean(in);
        }

        @Override
        public GiftTabBean[] newArray(int size) {
            return new GiftTabBean[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(message);
    }

    public static class DataBean {
        private List<RanksBean> ranks;

        public List<RanksBean> getRanks() {
            return ranks;
        }

        public void setRanks(List<RanksBean> ranks) {
            this.ranks = ranks;
        }

        public static class RanksBean implements Parcelable{
            /**
             * id : 1
             * mark_style : 0
             * name : 每日推荐
             * title : 每日推荐好礼：一天都不愿错过
             * update_at : 1487174400
             */

            private int id;
            private int mark_style;
            private String name;
            private String title;
            private int update_at;

            protected RanksBean(Parcel in) {
                id = in.readInt();
                mark_style = in.readInt();
                name = in.readString();
                title = in.readString();
                update_at = in.readInt();
            }

            public static final Creator<RanksBean> CREATOR = new Creator<RanksBean>() {
                @Override
                public RanksBean createFromParcel(Parcel in) {
                    return new RanksBean(in);
                }

                @Override
                public RanksBean[] newArray(int size) {
                    return new RanksBean[size];
                }
            };

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMark_style() {
                return mark_style;
            }

            public void setMark_style(int mark_style) {
                this.mark_style = mark_style;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUpdate_at() {
                return update_at;
            }

            public void setUpdate_at(int update_at) {
                this.update_at = update_at;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeInt(mark_style);
                dest.writeString(name);
                dest.writeString(title);
                dest.writeInt(update_at);
            }
        }
    }
}
