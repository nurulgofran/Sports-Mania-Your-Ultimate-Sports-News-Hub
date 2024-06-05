package com.nurul.sportmania.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SESAM on 01.09.2018.
 */

public class Category {
    @SerializedName("cid")
    private String cid;
    @SerializedName("category_name")
    private String category_name;
    @SerializedName("category_image")
    private String category_image;

    public Category(String cid, String category_name, String category_image) {
        this.cid = cid;
        this.category_name = category_name;
        this.category_image = category_image;
    }

    public String getCid() {
        return cid;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getCategory_image() {
        return category_image;
    }
}
