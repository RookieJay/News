package com.zjc.news.utils;

import android.app.Activity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.google.gson.Gson;
import com.zjc.news.model.JsonRootBean;
import com.zjc.news.model.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZJC on 2018-06-15.
 */

public class TestUtil {

    public static List<Result> resultList = new ArrayList<>();

    public void sendRequestWithOkHttp() {
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://api.avatardata.cn/ActNews/Query?key=6fd577efde774b92b84f308d48de5198&keyword=一加")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseWithGson(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public List<Result> parseWithGson(String jsonData) {
        Gson gson = new Gson();
        JsonRootBean bean = gson.fromJson(jsonData,JsonRootBean.class);
        List<Result> resultList = bean.getResult();
//        for (int i=0;i<results.size();i++){
//            Log.d("解析出来的第"+(i+1)+"个标题", results.get(i).getTitle().toString());
//            Log.d("解析出来的第"+(i+1)+"个内容", results.get(i).getContent().toString());
//            newsList.add(new Result(results.get(i).getTitle(),bean.getResult().get(i).getContent(), bean.getResult().get(i).getImg()));
//        }
//        for (int i=0;i<newsList.size();i++){
//            Log.d("newsList+++++++", "标题"+(i+1)+"："+newsList.get(i).getTitle()+"    内容"+(i+1)+"："+newsList.get(i).getContent()+"    图片"+(i+1)+"："+newsList.get(i).getImg());
//        }
        Log.d(">>>>>>>>解析出来的list", String.valueOf(resultList));
        return resultList;
    }

}
