package com.sola.gifttalk.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dllo on 17/3/1.
 */

public class HotSearchBean implements Parcelable{
    /**
     * code : 200
     * data : {"hot_words":["情侣","生日","项链","手机壳","杯子","手表","钱包","双肩包"],"placeholder":"选份走心好礼送给Ta"}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    protected HotSearchBean(Parcel in) {
        code = in.readInt();
        data = in.readParcelable(DataBean.class.getClassLoader());
        message = in.readString();
    }

    public static final Creator<HotSearchBean> CREATOR = new Creator<HotSearchBean>() {
        @Override
        public HotSearchBean createFromParcel(Parcel in) {
            return new HotSearchBean(in);
        }

        @Override
        public HotSearchBean[] newArray(int size) {
            return new HotSearchBean[size];
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
        dest.writeParcelable(data, flags);
        dest.writeString(message);
    }

    public static class DataBean implements Parcelable{
        /**
         * hot_words : ["情侣","生日","项链","手机壳","杯子","手表","钱包","双肩包"]
         * placeholder : 选份走心好礼送给Ta
         */

        private String placeholder;
        private List<String> hot_words;

        protected DataBean(Parcel in) {
            placeholder = in.readString();
            hot_words = in.createStringArrayList();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public List<String> getHot_words() {
            return hot_words;
        }

        public void setHot_words(List<String> hot_words) {
            this.hot_words = hot_words;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(placeholder);
            dest.writeStringList(hot_words);
        }
    }
}
