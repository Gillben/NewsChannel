package com.ricky.newschannel.presenter;

import com.ricky.newschannel.api.RetrofitService;
import com.ricky.newschannel.api.bean.NewsInfo;
import com.ricky.newschannel.view.INewsListView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/13.
 */

public class NewsListPresenter implements IBasePresenter {

    private INewsListView newsMainView;
    private String typeId;

    public NewsListPresenter(String typeId, INewsListView newsMainView) {
        this.typeId = typeId;
        this.newsMainView = newsMainView;
    }

    @Override
    public void getData() {
        RetrofitService.getNewsList(typeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsInfo>() {
                    @Override
                    public void onCompleted() {
                        newsMainView.stopProgress();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsInfo newsInfo) {
                        newsMainView.addLoadingData(newsInfo);
                    }
                });
    }

    @Override
    public void getMoreData() {
        RetrofitService.getNewsList(typeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsInfo>() {
                    @Override
                    public void onCompleted() {
                        newsMainView.stopProgress();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(NewsInfo newsInfo) {
                        newsMainView.updateMoreData(newsInfo);
                    }
                });
    }
}
