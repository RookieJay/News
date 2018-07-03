package com.zjc.news.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.zjc.news.R;
import com.zjc.news.model.NewsBean;
import com.zjc.news.view.adapter.NewsAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZJC on 2018/6/15.
 */

public class FragmentNetUtils {

    private static final String TAG = ">>>>>>FragmentNetUtils";

    private RecyclerView recyclerView;

    private  Context context;

    private Activity activity;

    public FragmentNetUtils(RecyclerView recyclerView, Context context, Activity activity) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.activity = activity;
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context1) {
        ConnectivityManager connectivity = (ConnectivityManager) context1
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    public void asyncHttpRequest(String type){
        Log.i(TAG, "加载到asyncHttpRequest()");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://v.juhe.cn/toutiao/index?type="+type+"&key=e0852545993710eda928803afc4b9c1b")
                .build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtil.showToast(context,"新闻加载失败");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Log.d(TAG, "请求成功");
                Log.d(">>>onResponse>>>当前线程", Thread.currentThread().getName());
                final String responseData = response.body().string();
                Log.d(TAG, responseData);
                parseData(responseData);
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ToastUtil.showToast(activity,"成功获取数据");
//                    }
//                });
            }
        });
    }

    public void parseData(String responseData) {
        Log.i(TAG, "加载到parseData()");
        Gson gson = new Gson();
        NewsBean newsBean = gson.fromJson(responseData, NewsBean.class);
        NewsBean.Result result = newsBean.result;
        final List<NewsBean.Result.Data> datas = result.data;
        Log.d(">>>>>>>解析的标题：", datas.get(0).getTitle().toString());
        showResponse(datas);
    }


    public void showResponse(final List<NewsBean.Result.Data> datas) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("parseData>>>当前线程", Thread.currentThread().getName());
                Log.i(TAG, "加载到showResponse: datasize="+datas.size());
                LinearLayoutManager manager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(manager);
                NewsAdapter adapter = new NewsAdapter(datas);
                recyclerView.setAdapter(adapter);
            }
        });

    }

}