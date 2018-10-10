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

public class SportsFragment extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sports, container, false);
        recyclerView = view.findViewById(R.id.sport_recycler_view);
        FragmentNetUtils utils = new FragmentNetUtils(recyclerView, view.getContext(), getActivity());
        if (!FragmentNetUtils.isNetworkAvailable(view.getContext())) {
            ToastUtil.showToast(view.getContext(),"请检查网络连接");
        }
        utils.asyncHttpRequest("tiyu");
        return view;
    }
}
