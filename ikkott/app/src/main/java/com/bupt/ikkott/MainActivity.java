package com.bupt.ikkott;

import android.os.Bundle;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
    VideoParser parser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 隐藏状态栏和标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        // 初始化 MainActivity
        setContentView(R.layout.activity_main_view);

        // 拉取并解析视频资源
        parser = VideoParser.getInstance();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            return;
        }

        // 初始化添加一些组件
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RecyclerFragment recyclerFragment = new RecyclerFragment();
        fragmentTransaction.add(R.id.content_view, recyclerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
