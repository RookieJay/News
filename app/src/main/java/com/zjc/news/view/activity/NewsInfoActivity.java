package com.zjc.news.view.activity;

import android.content.Intent;
import android.net.http.SslError;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.zjc.news.BaseActivity;
import com.zjc.news.R;

public class NewsInfoActivity extends BaseActivity implements View.OnClickListener{

    private View backImage;
    private String TAG = "NewsInfoActivity";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        initSystemBar(true);
        backImage = findViewById(R.id.tool_bar_back);
        webView = findViewById(R.id.web_view);
        //设置标题栏Toolbar

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }

        backImage.setOnClickListener(this);

//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                handler.proceed();
//            }
//        });
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.d(TAG, "我的Url"+url);
        webView.loadUrl(url);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tool_bar_back:
                finish();
        }
    }
}
