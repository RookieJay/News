package com.zjc.news.module.news.view.fragment;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjc.news.R;
import com.zjc.news.model.NewsBean;
import com.zjc.news.module.news.contract.NewsContract;
import com.zjc.news.module.news.presenter.NewsPresenter;
import com.zjc.news.module.news.view.BaseNewsFragment;
import com.zjc.news.module.news.view.adapter.NewsAdapter;
import com.zjc.news.utils.FragmentNetUtils;
import com.zjc.news.utils.ToastUtil;

import java.util.List;

import static com.zjc.news.module.news.view.adapter.MyFragmentPagerAdapter.POSITION;

public class DynamicFragment extends Fragment implements NewsContract.View{

    private static final String TAG = DynamicFragment.class.getSimpleName();
    private NewsPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private NewsAdapter adapter;
    private List<NewsBean.Result.Data> mData;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_news, container, false);
        String type = null;
        Bundle bundle = getArguments();
        if (null != bundle) {
            Log.d(TAG, String.valueOf(bundle.getInt(POSITION)));
        }
        int position = 0;
        switch (position) {
            case 0:
                type = "toutiao";
                break;
            case 1:
                type = "keji";
                break;
            case 2:
                type = "guonei";
                break;
            case 3:
                type = "guoji";
                break;
            case 4:
                type = "tiyu";
                break;
            case 5:
                type = "yule";
                break;
            case 6:
                type = "caijing";
                break;
            case 7:
                type = "shishang";
                break;
            case 8:
                type = "junshi";
                break;
            case 9:
                type = "shehui";
                break;
        }
        String url = "http://v.juhe.cn/toutiao/index?type="+type+"&key=e0852545993710eda928803afc4b9c1b";
//        initData(url);
        mRecyclerView = view.findViewById(R.id.base_recycler_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    private void initData(String url) {
        mPresenter = new NewsPresenter(this);
        mPresenter.getDataFromNetWork(url);
    }

    @Override
    public void showData(final List<NewsBean.Result.Data> datas) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(getActivity(),datas.get(0).getCategory());
                Log.d(TAG+"当前线程", Thread.currentThread().getName());
                mData = datas;
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(manager);
                adapter = new NewsAdapter(mData);
                mRecyclerView.setAdapter(adapter);
            }
        });

    }
}
