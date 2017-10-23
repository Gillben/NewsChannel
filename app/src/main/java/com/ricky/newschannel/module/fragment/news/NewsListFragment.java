package com.ricky.newschannel.module.fragment.news;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.ricky.newschannel.R;
import com.ricky.newschannel.adapter.RecycleViewAdapter;
import com.ricky.newschannel.api.bean.NewsInfo;
import com.ricky.newschannel.module.fragment.BaseFragment;
import com.ricky.newschannel.presenter.NewsListPresenter;
import com.ricky.newschannel.decoration.CustomRecycleViewDecoration;
import com.ricky.newschannel.view.INewsListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.ricky.newschannel.decoration.CustomRecycleViewDecoration.VERTICAL_LIST;

/**
 * 新闻类型详情
 * Created by Administrator on 2017/5/4.
 */

public class NewsListFragment extends BaseFragment
        implements INewsListView, OnRefreshListener, OnLoadMoreListener {

    private static final String NEWS_TYPE_KEY = "NewsTypeKey";
    @BindView(R.id.news_refresh_layout)
    SwipeToLoadLayout mSwipeLayout;
    @BindView(R.id.swipe_target)
    RecyclerView mRecycleView;

    private String newsId;

    @Inject
    NewsListPresenter newsListPresenter;
    @Inject
    RecycleViewAdapter recycleViewAdapter;


    public static NewsListFragment newInstance(String newsId) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_TYPE_KEY, newsId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_list;
    }


    @Override
    protected void initInjector() {
        DaggerNewsListComponent.builder()
                .newsListModule(new NewsListModule(newsId, this, getActivity()))
                .build()
                .inJect(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            newsId = getArguments().getString(NEWS_TYPE_KEY);
        }
    }

    @Override
    protected void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.addItemDecoration(new CustomRecycleViewDecoration(getActivity(), VERTICAL_LIST));
        mRecycleView.setAdapter(recycleViewAdapter);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadMoreListener(this);
        mRecycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(mRecycleView, 1)) {
                        mSwipeLayout.setLoadingMore(true);
                    }
                }
            }
        });
        recycleViewAdapter.setOnRecycleViewItemClickListener(new RecycleViewAdapter.OnRecycleViewItemClickListener() {
            @Override
            public void onClickRecycleViewItem(View view, int position) {
                recycleViewAdapter.launchNewsDetail(position);
            }
        });
    }

    @Override
    protected void updateViews() {
        newsListPresenter.getData();
    }

    @Override
    public void addLoadingData(NewsInfo newsInfo) {
        List<NewsInfo.NewsResult.NewsData> NewsDataList = newsInfo.getResult().getData();
        recycleViewAdapter.updateRecycleViewItem(NewsDataList);
    }

    @Override
    public void stopProgress() {
        mSwipeLayout.setRefreshing(false);
        mSwipeLayout.setLoadingMore(false);
    }

    @Override
    public void updateMoreData(NewsInfo newsInfo) {
        recycleViewAdapter.loadMoreRecycleItem(newsInfo.getResult().getData());
    }

    @Override
    public void onRefresh() {
        newsListPresenter.getData();
    }

    @Override
    public void onLoadMore() {
        newsListPresenter.getMoreData();
    }
}
