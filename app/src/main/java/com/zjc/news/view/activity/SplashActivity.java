package com.zjc.news.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.zjc.news.BaseActivity;
import com.zjc.news.R;
import com.zjc.news.controller.ActivityController;

public class SplashActivity extends BaseActivity {

    private static final long DELAY_TIME = 3000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {  //当系统版本大于5.0时执行
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE //两个FLAG一起用表示会让应用的主体内容占用系统状态栏的空间
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;    //让应用的主体内容占用系统导航栏的空间
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);   //设置导航栏透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);   //设置状态栏透明
            //隐藏状态栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY_TIME);

    }
}
