package com.zjc.news.module.news.presenter;

import com.zjc.news.model.NewsBean;
import com.zjc.news.module.news.contract.NewsContract;
import com.zjc.news.module.news.model.NewsModel;

import java.util.List;

public class NewsPresenter implements NewsContract.Presenter {

    private NewsContract.View view;
    private NewsContract.Model model;

    public NewsPresenter(NewsContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataFromNetWork(String url) {
        model = new NewsModel(this);
        model.getDataFromNetWork(url);
    }

    @Override
    public void showData(List<NewsBean.Result.Data> datas) {
        view.showData(datas);
    }
}
