package com.ricky.newschannel.module.fragment.news;

import dagger.Component;

/**
 * Created by Administrator on 2017/5/21.
 */
@Component(modules = NewsViewpagerModule.class)
public interface NewsViewPagerComponent {
    void inject(NewsMainFragment newsMainFragment);
}
