package com.bupt.ikkott.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("api/invoke/video/invoke/video")
    Call<List<VideoResponse>> getVideoList();
}
