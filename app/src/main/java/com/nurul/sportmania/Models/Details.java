package com.nurul.sportmania.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SESAM on 01.09.2018.
 */

public class Details {

    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public class Detail {

        @SerializedName("news_description")
        @Expose
        private String newsDescription;
        @SerializedName("news_date")
        @Expose
        private String newsDate;
        @SerializedName("news_emoji_loved")
        @Expose
        private String newsEmojiLoved;
        @SerializedName("news_emoji_lol")
        @Expose
        private String newsEmojiLol;
        @SerializedName("news_emoji_angry")
        @Expose
        private String newsEmojiAngry;
        @SerializedName("news_emoji_cry")
        @Expose
        private String newsEmojiCry;
        @SerializedName("news_emoji_omg")
        @Expose
        private String newsEmojiOmg;

        public String getNewsDescription() {
            return newsDescription;
        }

        public void setNewsDescription(String newsDescription) {
            this.newsDescription = newsDescription;
        }

        public String getNewsDate() {
            return newsDate;
        }

        public void setNewsDate(String newsDate) {
            this.newsDate = newsDate;
        }

        public String getNewsEmojiLoved() {
            return newsEmojiLoved;
        }

        public void setNewsEmojiLoved(String newsEmojiLoved) {
            this.newsEmojiLoved = newsEmojiLoved;
        }

        public String getNewsEmojiLol() {
            return newsEmojiLol;
        }

        public void setNewsEmojiLol(String newsEmojiLol) {
            this.newsEmojiLol = newsEmojiLol;
        }

        public String getNewsEmojiAngry() {
            return newsEmojiAngry;
        }

        public void setNewsEmojiAngry(String newsEmojiAngry) {
            this.newsEmojiAngry = newsEmojiAngry;
        }

        public String getNewsEmojiCry() {
            return newsEmojiCry;
        }

        public void setNewsEmojiCry(String newsEmojiCry) {
            this.newsEmojiCry = newsEmojiCry;
        }

        public String getNewsEmojiOmg() {
            return newsEmojiOmg;
        }

        public void setNewsEmojiOmg(String newsEmojiOmg) {
            this.newsEmojiOmg = newsEmojiOmg;
        }

    }

}