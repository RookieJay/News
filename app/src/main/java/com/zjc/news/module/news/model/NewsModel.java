package com.zjc.news.module.news.model;

import com.google.gson.Gson;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.zjc.news.model.NewsBean;
import com.zjc.news.module.news.contract.NewsContract;
import com.zjc.news.utils.NetworkUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NewsModel implements NewsContract.Model {

    private static final String TAG = NewsModel.class.getSimpleName();
    private NewsContract.Presenter presenter;
    private NewsContract.View view;

    public NewsModel(NewsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getDataFromNetWork(String url) {
        NetworkUtil networkUtil = new NetworkUtil(new OkHttpClient(), new Handler(Looper.getMainLooper()));
        networkUtil.asyncGet(url, new NetworkUtil.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {

            }

            @Override
            public void onSuccess(Request request, String result) {
                Log.d(TAG+"onSuccess当前线程", Thread.currentThread().getName());
                if (null != result) {
                    Log.d(TAG+"result", result);
                    Gson gson = new Gson();
                    NewsBean newsBean = gson.fromJson(result, NewsBean.class);
                    NewsBean.Result newsResult = newsBean.result;
                    List<NewsBean.Result.Data> datas = newsResult.data;
                    if (null != datas) {
                        Log.d(TAG, datas.toString());
                        presenter.showData(datas);
                    }
                }
            }
        });
    }
}
