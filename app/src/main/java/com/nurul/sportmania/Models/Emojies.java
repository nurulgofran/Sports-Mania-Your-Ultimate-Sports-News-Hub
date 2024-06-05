package com.nurul.sportmania.Models;

/**
 * Created by SESAM on 08.09.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Emojies {

    @SerializedName("emojies")
    @Expose
    private List<Emojy> emojies = null;

    public List<Emojy> getEmojies() {
        return emojies;
    }

    public void setEmojies(List<Emojy> emojies) {
        this.emojies = emojies;
    }

    public class Emojy {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("added")
        @Expose
        private String added;
        @SerializedName("message")
        @Expose
        private String message;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getAdded() {
            return added;
        }

        public void setAdded(String added) {
            this.added = added;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}