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

@SuppressWarnings("WeakerAccess")
public class VideoParser {
    private static VideoParser singleVideoPaser;
    private List<VideoResponse> videoList;

    private VideoParser() {
        getResource();
    }

    /*public VideoParser(int first) {
        getResource();
    }*/

    //懒汉式单例
    public static VideoParser getInstance(){
        if (singleVideoPaser == null){
            singleVideoPaser = new VideoParser();
        }
        return singleVideoPaser;
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

    //确定recycleview的列表长度
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
