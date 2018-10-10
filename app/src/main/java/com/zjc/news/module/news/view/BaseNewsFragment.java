package com.zjc.news.module.news.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseNewsFragment extends Fragment{

    private Context mContext;
    private RecyclerView mRecyclerView;
    private boolean isAttachToRoot;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        initVarible();
    }

    private void initVarible() {
        isAttachToRoot = false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            View view = inflater.inflate(layoutId, container, isAttachToRoot);
            return view;
        } else {
            throw new IllegalArgumentException(
                    "getLayoutId() returned 0, which is not allowed. \" + \"If you don't want to use getLayoutId() but implement your own view \" + \"for this fragment manually, then you have to override onCreateView();"
            );
        }
    }

    protected final View findViewById(int id) {
        return (getView() != null && id >= 0) ? getView().findViewById(id) : null;
    }

    protected abstract int getLayoutId();
}
