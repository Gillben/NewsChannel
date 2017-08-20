package com.ricky.newschannel.api;

import com.ricky.newschannel.api.bean.NewsInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/9.
 */

public interface INewsApi {

    @GET("index?type=&key=27a056f566ad220e025445f5a36f4515")
    Observable<NewsInfo> getNewsList(@Query("type") String type);


}
