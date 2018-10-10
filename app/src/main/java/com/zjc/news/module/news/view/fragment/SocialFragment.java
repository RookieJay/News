package com.zjc.news.module.news.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjc.news.R;
import com.zjc.news.utils.FragmentNetUtils;
import com.zjc.news.utils.ToastUtil;

/**
 * Created by ZJC on 2018-06-13.
 */

public class SocialFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social, container, false);
        recyclerView = view.findViewById(R.id.social_recycler_view);
        FragmentNetUtils utils = new FragmentNetUtils(recyclerView,view.getContext(),getActivity());
        if (!FragmentNetUtils.isNetworkAvailable(view.getContext())) {
            ToastUtil.showToast(view.getContext(),"请检查网络");
        }
        utils.asyncHttpRequest("shehui");
        return view;
    }

//    private void initData() {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(">>>>>>>>", "initData: ");
//                final Request request = new Request.Builder()
//                        .get()
//                        .url("http://v.juhe.cn/toutiao/index?type=shehui&key=65d4c89f2460e131bd8b288f3f70bff6")
//                        .build();
//                Log.i(">>>>>>>>>request", "request: ");
//                Log.i(">>>>>Thread", Thread.currentThread().getName());
//                final  Response response;
//                try {
//                    response = client.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        String responseData = response.body().string();
//                        Log.d(">>>>>>>>>responseData", responseData);
//                        parseWithGson(responseData);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//
//    private void parseWithGson(String responseData) {
//        Gson gson = new Gson();
//        NewsBean newsBean = gson.fromJson(responseData, NewsBean.class);
//        NewsBean.Result result = newsBean.result;
//        List<NewsBean.Result.Data> datas = result.data;
//        Log.d(">>>>>>>datas", datas.get(0).getTitle().toString());
//        showResponse(datas);
//    }
//
//    private void showResponse(final List<NewsBean.Result.Data> datas) {
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i("Social>>>>>>>", datas.get(0).getTitle());
//                recyclerView = getActivity().findViewById(R.id.social_recycler_view);
//                LinearLayoutManager manager = new LinearLayoutManager(getContext());
//                recyclerView.setLayoutManager(manager);
//                adapter = new NewsAdapter(datas);
//                recyclerView.setAdapter(adapter);
//            }
//        });
//    }
//
//
//    class myHandler extends  Handler{
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Log.i(">>>>>>>当前线程", Thread.currentThread().getName());
//            dataList = (List<NewsBean.Result.Data>) msg.obj;
//        }
//    }

}
