package com.zjc.news.module.news.contract;

import com.zjc.news.model.NewsBean;

import java.util.List;

public interface NewsContract {

    interface Model {

        void getDataFromNetWork(String url);
    }

    interface View {

        void showData(List<NewsBean.Result.Data> datas);
    }

    interface Presenter {

        void getDataFromNetWork(String url);

        void showData(List<NewsBean.Result.Data> datas);
    }
}
