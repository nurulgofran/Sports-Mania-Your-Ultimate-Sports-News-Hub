package com.nurul.sportmania.Models;

/**
 * Created by SESAM on 01.09.2018.
 */

import com.google.gson.annotations.SerializedName;

public class News {

    private int id;
    private String category_name,heading,image;
    @SerializedName("nid")
    private String nid;
    @SerializedName("news_category_name")
    private String news_category_name;
    @SerializedName("news_heading")
    private String news_heading;
    @SerializedName("news_image")
    private String news_image;

    @SerializedName("total_news")
    private String total_news;

    public News(String nid, String news_category_name, String news_heading, String news_image) {
        this.nid = nid;
        this.news_category_name = news_category_name;
        this.news_heading = news_heading;
        this.news_image = news_image;
    }

    public String getNid() {
        return nid;
    }

    public String getNews_category_name() {
        return news_category_name;
    }

    public String getNews_heading() {
        return news_heading;
    }

    public String getNews_image() {
        return news_image;
    }

    public News() {
    }

    public String getTotal_news() {
        return total_news;
    }

    public void setTotal_news(String total_news) {
        this.total_news = total_news;
    }
}
