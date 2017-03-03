package com.sola.gifttalk.bean;

import java.util.List;

/**
 * Created by dllo on 17/2/14.
 */

public class BannerBean {

    /**
     * code : 200
     * data : {"banners":[{"ad_monitors":[],"channel":"all","id":902,"image_url":"http://img03.liwushuo.com/image/170213/1zi5djbqx.jpg-w720","order":800,"status":0,"target_id":1047450,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1047450","type":"post","webp_url":"http://img03.liwushuo.com/image/170213/1zi5djbqx.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":900,"image_url":"http://img01.liwushuo.com/image/170210/s1ve680sw.jpg-w720","order":700,"status":0,"target_id":1047432,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1047432","type":"post","webp_url":"http://img01.liwushuo.com/image/170210/s1ve680sw.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":903,"image_url":"http://img02.liwushuo.com/image/170214/t5rr9t2bx.jpg-w720","order":610,"status":0,"target_id":null,"target_type":"url","target_url":"liwushuo:///page?url=http%3A%2F%2Fh5.immocha.com%2Factivity%2Floverday2017%2Findex.html%3Fshowbtn%3D1&page_action=navigation&login=false&type=url","type":"url","webp_url":"http://img02.liwushuo.com/image/170214/t5rr9t2bx.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":899,"image_url":"http://img01.liwushuo.com/image/170210/7si71t2jp.jpg-w720","order":600,"status":0,"target_id":null,"target_type":"url","target_url":"liwushuo:///page?url=https%3A%2F%2Fevent.liwushuo.com%2Fadmin%2Fevent%2F80&page_action=navigation&login=false&type=url","type":"url","webp_url":"http://img01.liwushuo.com/image/170210/7si71t2jp.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":898,"image_url":"http://img02.liwushuo.com/image/170210/n17b07omz.jpg-w720","order":500,"status":0,"target_id":null,"target_url":"liwushuo:///page?type=shop_collection&id=41","type":"url","webp_url":"http://img02.liwushuo.com/image/170210/n17b07omz.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":895,"image_url":"http://img01.liwushuo.com/image/170210/1lsitlhf2.jpg-w720","order":400,"status":0,"target_id":1047414,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1047414","type":"post","webp_url":"http://img01.liwushuo.com/image/170210/1lsitlhf2.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":897,"image_url":"http://img03.liwushuo.com/image/170210/dx5i96d4s.jpg-w720","order":0,"status":0,"target_id":1047430,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1047430","type":"post","webp_url":"http://img03.liwushuo.com/image/170210/dx5i96d4s.jpg?imageView2/2/w/720/q/85/format/webp"}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

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

    public static class DataBean {
        private List<BannersBean> banners;

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public static class BannersBean {
            /**
             * ad_monitors : []
             * channel : all
             * id : 902
             * image_url : http://img03.liwushuo.com/image/170213/1zi5djbqx.jpg-w720
             * order : 800
             * status : 0
             * target_id : 1047450
             * target_type : url
             * target_url : liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1047450
             * type : post
             * webp_url : http://img03.liwushuo.com/image/170213/1zi5djbqx.jpg?imageView2/2/w/720/q/85/format/webp
             */

            private String channel;
            private int id;
            private String image_url;
            private int order;
            private int status;
            private int target_id;
            private String target_type;
            private String target_url;
            private String type;
            private String webp_url;
            private List<?> ad_monitors;

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTarget_id() {
                return target_id;
            }

            public void setTarget_id(int target_id) {
                this.target_id = target_id;
            }

            public String getTarget_type() {
                return target_type;
            }

            public void setTarget_type(String target_type) {
                this.target_type = target_type;
            }

            public String getTarget_url() {
                return target_url;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getWebp_url() {
                return webp_url;
            }

            public void setWebp_url(String webp_url) {
                this.webp_url = webp_url;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }

            public void setAd_monitors(List<?> ad_monitors) {
                this.ad_monitors = ad_monitors;
            }
        }
    }
}
