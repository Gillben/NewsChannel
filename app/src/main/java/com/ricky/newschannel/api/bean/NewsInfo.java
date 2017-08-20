package com.ricky.newschannel.api.bean;

import java.util.List;

/**
 * 新闻页面内容的 javaBean
 * Created by Administrator on 2017/4/11.
 */

public class NewsInfo {

    /**
     * ***
     * ***  AppKey：
     *
     * "reason":"成功的返回",
     * <p>
     * result":{ "stat":"1", "data":[] }
     * <p>
     * data [
     * <p>
     * {"uniquekey":  "b70d7ffde0bd4de6d9a0ce23e819ad7a",
     * " title":      "那个“放跑丁义珍”的美联航，居然这么狂？！",
     * " date":       "2017-04-11 21:03",
     * " category":   "头条",
     * " author_name":"环球网",
     * " url":         "http:\/\/mini.eastday.com\/mobile\/170411210301981.html",
     * " thumbnail_pic_s":    "http:\/\/02.imgmini.eastday.com\/mobile\/20170411\/20170411210301_9064599db17f92f97ee50e09b17a0031_25_mwpm_03200403.png",
     * " thumbnail_pic_s02":  "http:\/\/02.imgmini.eastday.com\/mobile\/20170411\/20170411210301_9064599db17f92f97ee50e09b17a0031_24_mwpm_03200403.png",
     * " thumbnail_pic_s03":  "http:\/\/02.imgmini.eastday.com\/mobile\/20170411\/20170411210301_9064599db17f92f97ee50e09b17a0031_23_mwpm_03200403.jpeg"
     * }
     * ......
     */


    public String reason;
    public NewsResult result;
    public String error_code;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(NewsResult result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public NewsResult getResult() {
        return result;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_code() {
        return error_code;
    }

    public static class NewsResult {
        public String stat;
        public List<NewsData> data;

        public void setStat(String stat) {
            this.stat = stat;
        }

        public void setData(List<NewsData> data) {
            this.data = data;
        }

        public String getStat() {
            return stat;
        }

        public List<NewsData> getData() {
            return data;
        }

        public static class NewsData {
            public String uniquekey;
            public String title;
            public String  date;
            public String  category;
            public String author_name;
            public String url;
            public String thumbnail_pic_s;
            public String thumbnail_pic_s02;
            public String thumbnail_pic_s03;

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }

            public String getUniquekey() {
                return uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public String getDate() {
                return date;
            }

            public String getCategory() {
                return category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public String getUrl() {
                return url;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public int getImageNumber(){
                if ((null != getThumbnail_pic_s()) && (null != getThumbnail_pic_s02()) && (null != getThumbnail_pic_s03())){
                    return 3;
                }
                if ((null != getThumbnail_pic_s()) && (null != getThumbnail_pic_s02()) && (null == getThumbnail_pic_s03())){
                    return 2;
                }
                if ((null != getThumbnail_pic_s()) && (null == getThumbnail_pic_s02()) && (null == getThumbnail_pic_s03())){
                    return 1;
                }
                return 0;
            }
        }
    }

}
