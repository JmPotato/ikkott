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
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.view_pager2);
        viewPager2 = findViewById(R.id.view_pager2);

        // 使用 Intent 传递视频 offset，以定位视频资源位置
        Intent intent = getIntent();
        int initPos = intent.getIntExtra("initPos", 0);
        ViewPagerAdapter adapter = new ViewPagerAdapter(initPos);
        viewPager2.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        // 防止用户退出后视频依然播放，手动进行处理
        super.onDestroy();
        VideoPlayerIJK player = viewPager2.getChildAt(0).findViewById(R.id.ijk_player_view);
        player.pause();
    }
}
