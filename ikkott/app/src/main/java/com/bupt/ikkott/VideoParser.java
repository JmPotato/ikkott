package com.bupt.ikkott;

import android.util.Log;

import com.bupt.ikkott.api.APIService;
import com.bupt.ikkott.api.VideoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class VideoParser {
    private static List<VideoResponse> videoList;

    // 通过重载构造函数，实现单例模式
    public VideoParser() {

    }

    public VideoParser(int first) {
        getResource();
    }

    private void getResource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);
        apiService.getVideoList().enqueue(new Callback<List<VideoResponse>>() {
            @Override
            public void onResponse(Call<List<VideoResponse>> call, Response<List<VideoResponse>> response) {
                if (response.body() != null) {
                    videoList = response.body();
                    for (VideoResponse uri : videoList) {
                        Log.d("retrofit", uri.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VideoResponse>> call, Throwable t) {
                Log.d("retrofit", t.getMessage());
            }
        });
    }

    public int size() {
        return videoList.size();
    }

    public String getFeedURL(int pos) {
        return videoList.get(pos).getFeedURL();
    }

    public String getDescription(int pos) {
        return videoList.get(pos).getDescription();
    }

    public String getNickname(int pos) {
        return videoList.get(pos).getNickname();
    }
}
