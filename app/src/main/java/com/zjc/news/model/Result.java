package com.zjc.news.model;


public class Result {

    private String title;
    private String content;
    private String img_width;
    private String full_title;
    private String pdate;
    private String src;
    private String img_length;
    private String img;
    private String url;
    private String pdate_src;

    public Result(String title, String content, String img) {
        this.title = title;
        this.content = content;
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setImg_width(String img_width) {
        this.img_width = img_width;
    }
    public String getImg_width() {
        return img_width;
    }

    public void setFull_title(String full_title) {
        this.full_title = full_title;
    }
    public String getFull_title() {
        return full_title;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }
    public String getPdate() {
        return pdate;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    public String getSrc() {
        return src;
    }

    public void setImg_length(String img_length) {
        this.img_length = img_length;
    }
    public String getImg_length() {
        return img_length;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getImg() {
        return img;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setPdate_src(String pdate_src) {
        this.pdate_src = pdate_src;
    }
    public String getPdate_src() {
        return pdate_src;
    }

    @Override
    public String toString() {
        return "Result{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img_width='" + img_width + '\'' +
                ", full_title='" + full_title + '\'' +
                ", pdate='" + pdate + '\'' +
                ", src='" + src + '\'' +
                ", img_length='" + img_length + '\'' +
                ", img='" + img + '\'' +
                ", url='" + url + '\'' +
                ", pdate_src='" + pdate_src + '\'' +
                '}';
    }
}