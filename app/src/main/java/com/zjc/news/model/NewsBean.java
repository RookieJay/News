package com.zjc.news.model;

import java.util.Date;
import java.util.List;

/**
 * Created by ZJC on 2018-06-13.
 */

public class NewsBean {
    public String reason;
    public Result result;
    public int error_code;

    public class Result{
        public String stat;
        public List<Data> data;
        public class Data{
            public String title;
            public String date;
            public String category;
            public String author_name;
            public String url;
            public String thumbnail_pic_s;
            public String thumbnail_pic_s02;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }
        }
    }
}