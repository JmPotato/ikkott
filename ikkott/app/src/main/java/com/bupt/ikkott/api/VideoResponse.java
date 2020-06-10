package com.bupt.ikkott.api;

import com.google.gson.annotations.SerializedName;

public class VideoResponse {
    @SerializedName("_id")
    public String _id;
    @SerializedName("feedurl")
    public String feedURL;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("description")
    public String description;
    @SerializedName("likecount")
    public int likeCount;
    @SerializedName("avatar")
    public String avatar;

    public String getFeedURL() {
        return feedURL;
    }

    public String getNickname() {
        return nickname;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "VideoInfo {" +
                "id = " + _id +
                ", feedurl = " + feedURL +
                ", nickname = " + nickname +
                ", description = " + description +
                ", likecount = " + likeCount +
                ", avatar = " + avatar +
                "}";
    }
}
