package com.zjc.news.view.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zjc.news.R;
import com.zjc.news.view.activity.NewsInfoActivity;
import com.zjc.news.model.NewsBean;

import java.util.List;


/**
 * Created by ZJC on 2018-06-13.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsBean.Result.Data> mNewsList;

    public NewsAdapter(List<NewsBean.Result.Data> mNewsList) {
        this.mNewsList = mNewsList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private ImageView newsImage;
        private TextView newsTitle;
        private TextView newsSrc;
        private TextView newsTime;
        public ViewHolder(View view) {
            super(view);
            linearLayout = view.findViewById(R.id.linear_layout);
            newsImage = view.findViewById(R.id.iv_news_list_image);
            newsTitle = view.findViewById(R.id.tv_news_list_title);
            newsSrc = view.findViewById(R.id.tv_news_list_real_type);
            newsTime = view.findViewById(R.id.tv_news_list_time);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NewsBean.Result.Data news = mNewsList.get(position);
        String image = news.getThumbnail_pic_s().toString();
//        Log.i(">>>adapter>>>>>image", mNewsList.get(0).getThumbnail_pic_s());
        Glide.with(holder.itemView.getContext()).load(image).into(holder.newsImage);
        holder.newsTitle.setText(news.getTitle());
        holder.newsSrc.setText(news.getAuthor_name());
        holder.newsTime.setText(news.getDate());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),NewsInfoActivity.class);
                intent.putExtra("url",news.getUrl());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }


}
