package com.bupt.ikkott;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bupt.ikkott.player.VideoPlayerIJK;


public class ViewPagerActivity extends AppCompatActivity {
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //状态栏隐藏
        getSupportActionBar().hide(); //标题栏隐藏
        setContentView(R.layout.view_pager2);
        viewPager2 = findViewById(R.id.view_pager2);
        //从recyclerview拿到初始点击位置，传给viewpager2的adapter
        Intent intent = getIntent();
        int initPos = intent.getIntExtra("initPos", 0);
        ViewPagerAdapter adapter = new ViewPagerAdapter(initPos);
        viewPager2.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        //界面退出时，还在播放
        //为解决这一问题使用从容器外部使用getChildAt()方法拿到内部的item
        super.onDestroy();
        VideoPlayerIJK player = viewPager2.getChildAt(0).findViewById(R.id.ijk_player_view);
        player.pause();
    }
}
