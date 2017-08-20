package com.ricky.newschannel.api;

import com.ricky.newschannel.api.bean.NewsInfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * 整个APP的网络控制中心
 * Created by Administrator on 2017/4/11.
 */

public class RetrofitService {

    public static final String APIKEY = "key=27a056f566ad220e025445f5a36f4515";
    public static final String NEWS_URL = "http://v.juhe.cn/toutiao/";
    private static INewsApi sNewsService;

    private RetrofitService() {
        throw new AssertionError();
    }

    /**
     * 初始化网络
     */
    public static void init(){
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(NEWS_URL)
                .build();
        sNewsService = mRetrofit.create(INewsApi.class);
    }

    /**
     * 获取新闻列表
     * @param type
     * @return
     */
    public static Observable<NewsInfo> getNewsList(final String type){
        return sNewsService.getNewsList(type);
    }

    /**
     * 获取新闻的详情
     * @param url
     * @return
     */
    public static String getNewsDetail(String url) throws IOException{
        OkHttpClient okHttpClient = new OkHttpClient();
        Request mRequest = new Request.Builder()
                .url(url)
                .build();
        Response mResponse = okHttpClient.newCall(mRequest).execute();
        return mResponse.body().string();
    }


    /**
     * 类型转换
     * @param typeStr
     * @return
     */
    private static Func1<Map<String,List<NewsInfo>>, Observable<NewsInfo>> _flatMapNews(final String typeStr) {
        return new Func1<Map<String,List<NewsInfo>>, Observable<NewsInfo>>() {
            @Override
            public Observable<NewsInfo> call(Map<String,List<NewsInfo>> newsInfos) {
                return Observable.from(newsInfos.get(typeStr));
            }
        };
    }
}
