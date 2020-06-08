package com.bupt.ikkott;

import android.os.Bundle;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
    static final int REQUEST_VIDEO_CAPTURE = 1;
    //请求状态码
    private static final int REQUEST_PERMISSION_CODE = 1;
    ResourceParser parser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //状态栏隐藏
        getSupportActionBar().hide(); //标题栏隐藏
        setContentView(R.layout.activity_main_view);
        parser = new ResourceParser(1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            return;
        }
        addDefaultFragment();
    }

    public void addDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RecyclerFragment recyclerFragment = new RecyclerFragment();
        fragmentTransaction.add(R.id.content_view, recyclerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
