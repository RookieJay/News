package com.zjc.news.utils;

import android.os.Handler;
import android.os.Looper;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZJC on 2018-06-24.
 */

public class NetworkUtil {
    private static NetworkUtil networkUtil;
    private OkHttpClient okHttpClient;
    private Handler handler;

    private NetworkUtil() {
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }


    /**
     * synchronized 关键字，代表这个方法加锁,
     * 相当于不管哪一个线程（例如线程A），运行到这个方法时,
     * 都要检查有没有其它线程B（或者C、 D等）正在用这个方法(或者该类的其他同步方法)，
     * 有的话要等正在使用synchronized方法的线程B（或者C 、D）运行完这个方法后再运行此线程A,
     * 没有的话,锁定调用者,然后直接运行。它包括两种用法：synchronized 方法和 synchronized 块。
     * 此处修饰的是代码块。
     * */
    public static NetworkUtil getInstance() {
        if (networkUtil == null) {
            synchronized (NetworkUtil.class) {
                if (networkUtil == null) {
                    networkUtil = new NetworkUtil();
                }
            }
        }

        return networkUtil;
    }

    class StringCallBack implements Callback {
        private HttpCallBack httpCallBack;
        private Request request;

        public StringCallBack(Request request, HttpCallBack httpCallBack) {
            this.request = request;
            this.httpCallBack = httpCallBack;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            final IOException fe = e;
            if (httpCallBack != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpCallBack.onError(request, fe);
                    }
                });
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String result = response.body().string();
            if (httpCallBack != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpCallBack.onSuccess(request, result);
                    }
                });
            }
        }
    }

    public void asyncGet(String url, HttpCallBack httpCallBack) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new StringCallBack(request, httpCallBack));
    }


    public void asyncPost(String url, FormBody formBody, HttpCallBack httpCallBack) {
        Request request = new Request.Builder().url(url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new StringCallBack(request, httpCallBack));
    }

    public interface HttpCallBack {
        void onError(Request request, IOException e);

        void onSuccess(Request request, String result);
    }
}