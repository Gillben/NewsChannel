package com.ricky.newschannel.module.fragment.news;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ricky.newschannel.R;
import com.ricky.newschannel.adapter.ViewPagerAdapter;
import com.ricky.newschannel.api.bean.NewsConstant;
import com.ricky.newschannel.module.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 新闻Fragment
 */

public class NewsMainFragment extends BaseFragment {

    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;

    @Inject
    ViewPagerAdapter mNewsAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_main;
    }


    @Override
    protected void initInjector() {
        DaggerNewsViewPagerComponent.builder()
                .newsViewpagerModule(new NewsViewpagerModule(this.getChildFragmentManager()))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initFragmentToolBar(mToolbar,true,"新闻");
        setHasOptionsMenu(true);
        mViewPager.setAdapter(mNewsAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void updateViews() {
        List<String> title = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            title.add(NewsConstant.newsTitle[i]);
            fragmentList.add(NewsListFragment.newInstance(NewsConstant.newsId[i]));
        }
        mNewsAdapter.setItems(fragmentList,title);
    }

}
