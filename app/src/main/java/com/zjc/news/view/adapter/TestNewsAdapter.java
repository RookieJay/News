package com.zjc.news.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zjc.news.R;
import com.zjc.news.model.Result;


import java.util.List;

/**
 * Created by ZJC on 2018-05-24.
 */

public class TestNewsAdapter extends RecyclerView.Adapter<TestNewsAdapter.ViewHolder> {

    private List<Result> mNewsList;

    public TestNewsAdapter(List<Result> newsList) {
        this.mNewsList = newsList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;

        ImageView newsImg;

        TextView newsTitle;

        TextView newsDescription;

        public ViewHolder(View view) {
            super(view);
            linearLayout = view.findViewById(R.id.news_item_linearlayout);
            newsImg = view.findViewById(R.id.news_img);
            newsTitle = view.findViewById(R.id.news_title);
            newsDescription = view.findViewById(R.id.news_desc);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pyq_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Result news = mNewsList.get(position);
        //用Html的fromHtml()方法可以有效地消除html标签
        String img = news.getImg().toString();
        Log.d("+++img", img);
        //Glide.with(Contex contex)此处的contex一定要注意是否有值,全局声明的是空的
//        Glide.with(mcontext).load(img).into(holder.newsImg);
        Glide.with(holder.itemView.getContext()).load(img).into(holder.newsImg);
        holder.newsTitle.setText(Html.fromHtml("<html><head><title><body>"+news.getTitle()+"</body></title></head></html>"));
        holder.newsDescription.setText(Html.fromHtml("<html><head><title><body>"+news.getContent()+"</body></title></head></html>"));
    }

    @Override
    public int getItemCount() {
        Log.d("adapter的数据长度", String.valueOf(mNewsList.size()));
        return mNewsList.size();
    }




}
