package com.sola.gifttalk.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dllo on 17/2/14.
 */

public class ChannelBean implements Parcelable{

    /**
     * code : 200
     * data : {"candidates":[{"editable":true,"id":121,"name":"数码","url":""},{"editable":true,"id":111,"name":"礼物","url":""},{"editable":true,"id":118,"name":"美食","url":""},{"editable":true,"id":26,"name":"送基友","url":""},{"editable":true,"id":6,"name":"送爸妈","url":""},{"editable":true,"id":126,"name":"奇葩搞怪","url":""},{"editable":true,"id":127,"name":"设计感","url":""},{"editable":true,"id":124,"name":"爱读书","url":""},{"editable":true,"id":28,"name":"科技范","url":""}],"channels":[{"editable":false,"id":108,"name":"精选"},{"editable":true,"id":121,"name":"数码","url":""},{"editable":true,"id":111,"name":"礼物","url":""},{"editable":true,"id":118,"name":"美食","url":""},{"editable":true,"id":26,"name":"送基友","url":""},{"editable":true,"id":6,"name":"送爸妈","url":""},{"editable":true,"id":126,"name":"奇葩搞怪","url":""},{"editable":true,"id":127,"name":"设计感","url":""},{"editable":true,"id":124,"name":"爱读书","url":""},{"editable":true,"id":28,"name":"科技范","url":""}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    protected ChannelBean(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<ChannelBean> CREATOR = new Creator<ChannelBean>() {
        @Override
        public ChannelBean createFromParcel(Parcel in) {
            return new ChannelBean(in);
        }

        @Override
        public ChannelBean[] newArray(int size) {
            return new ChannelBean[size];
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
        private List<CandidatesBean> candidates;
        private List<ChannelsBean> channels;

        public List<CandidatesBean> getCandidates() {
            return candidates;
        }

        public void setCandidates(List<CandidatesBean> candidates) {
            this.candidates = candidates;
        }

        public List<ChannelsBean> getChannels() {
            return channels;
        }

        public void setChannels(List<ChannelsBean> channels) {
            this.channels = channels;
        }

        public static class CandidatesBean {
            /**
             * editable : true
             * id : 121
             * name : 数码
             * url :
             */

            private boolean editable;
            private int id;
            private String name;
            private String url;

            public boolean isEditable() {
                return editable;
            }

            public void setEditable(boolean editable) {
                this.editable = editable;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ChannelsBean implements Parcelable {
            /**
             * editable : false
             * id : 108
             * name : 精选
             * url :
             */

            private boolean editable;
            private int id;
            private String name;
            private String url;

            protected ChannelsBean(Parcel in) {
                editable = in.readByte() != 0;
                id = in.readInt();
                name = in.readString();
                url = in.readString();
            }

            public static final Creator<ChannelsBean> CREATOR = new Creator<ChannelsBean>() {
                @Override
                public ChannelsBean createFromParcel(Parcel in) {
                    return new ChannelsBean(in);
                }

                @Override
                public ChannelsBean[] newArray(int size) {
                    return new ChannelsBean[size];
                }
            };

            public boolean isEditable() {
                return editable;
            }

            public void setEditable(boolean editable) {
                this.editable = editable;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeByte((byte) (editable ? 1 : 0));
                dest.writeInt(id);
                dest.writeString(name);
                dest.writeString(url);
            }
        }
    }
}
