package com.ricky.newschannel.module.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.ricky.newschannel.R;
import com.ricky.newschannel.api.RetrofitService;
import com.zzhoujay.richtext.CacheType;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;

import java.io.IOException;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 新闻详情处理
 * Created by Administrator on 2017/6/10.
 */

public class NewsDetailActivity extends BaseActivity {

    private static final String TAG = "NewsDetailActivity";
    private static final String news_detail_url = "NEWS_DETAIL_URL";
    private String htmlURL;

    @BindView(R.id.news_detail_text)
    TextView news_detail_text;

    public static void newsDetailInstance(Context context, String newsId) {
        Intent mIntent = new Intent(context, NewsDetailActivity.class);
        mIntent.putExtra(news_detail_url, newsId);
        context.startActivity(mIntent);
    }


    @Override
    protected int attachLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initViews() {
        RichText.initCacheDir(this);
        htmlURL = getIntent().getStringExtra(news_detail_url);
    }

    @Override
    protected void updateViews() {
        Observable.just(htmlURL)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        String urlString ;
                        try {
                            urlString = RetrofitService.getNewsDetail(s);
                        } catch (IOException e) {
                            return null;
                        }
                        return urlString;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: 错误");
                    }

                    @Override
                    public void onNext(String s) {
                        if (s != null) {
                            downLoadHtml(s);
                        }
                    }
                });
    }

    private void downLoadHtml(String text) {
        RichText.fromHtml(text)
                .autoPlay(true)
                .borderSize(10)
                .borderRadius(50)
                .scaleType(ImageHolder.ScaleType.FIT_CENTER)
                .cache(CacheType.ALL)
                .bind(this)
                .into(news_detail_text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RichText.clear(this);
        RichText.recycle(); //释放资源
    }
}
