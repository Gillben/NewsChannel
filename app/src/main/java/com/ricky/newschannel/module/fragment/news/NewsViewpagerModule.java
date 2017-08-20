package com.ricky.newschannel.module.fragment.news;

import android.support.v4.app.FragmentManager;

import com.ricky.newschannel.adapter.ViewPagerAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */
@Module
public class NewsViewpagerModule {

    public FragmentManager fragmentManager;

    public NewsViewpagerModule(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    @Provides
    public ViewPagerAdapter provideViewPagerAdapter(){
        return new ViewPagerAdapter(fragmentManager);
    }
}
