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


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFeedURL() {
        return feedURL;
    }

    public void setFeedURL(String feedURL) {
        this.feedURL = feedURL;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likecount) {
        this.likeCount = likecount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
