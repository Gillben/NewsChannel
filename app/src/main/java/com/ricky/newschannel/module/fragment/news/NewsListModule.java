package com.ricky.newschannel.module.fragment.news;

import android.content.Context;

import com.ricky.newschannel.adapter.RecycleViewAdapter;
import com.ricky.newschannel.presenter.NewsListPresenter;
import com.ricky.newschannel.view.INewsListView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */


@Module
public class NewsListModule {

    public String strId;
    public INewsListView iNewsListView;
    public Context mContext;

    public NewsListModule(String id,INewsListView iNewsListView,Context context){
        this.strId = id;
        this.iNewsListView = iNewsListView;
        this.mContext = context;
    }

    @Provides
    public NewsListPresenter provideNewsListPresenter(){
        return new NewsListPresenter(strId,iNewsListView);
    }

    @Provides
    public RecycleViewAdapter provideRecycleViewAdapter(){
        return new RecycleViewAdapter(mContext);
    }

}
