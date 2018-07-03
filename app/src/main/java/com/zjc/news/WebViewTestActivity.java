package com.zjc.news;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;


public class WebViewTestActivity extends BaseActivity implements View.OnClickListener{

    private ImageView backImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);
        initSystemBar(true);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
        WebView webView = findViewById(R.id.test_web_view);
        webView.loadUrl("http://mini.eastday.com/mobile/180628015345926.html");
        backImage = findViewById(R.id.tool_bar_back);
        backImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tool_bar_back:
                finish();
                break;
            default:
                break;
        }
    }
}
