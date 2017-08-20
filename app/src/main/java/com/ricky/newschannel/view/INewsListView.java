package com.ricky.newschannel.view;

import com.ricky.newschannel.api.bean.NewsInfo;

/**
 * Created by Administrator on 2017/4/13.
 */

public interface INewsListView {

    /**
     *刷新数据
     * @param newsInfo
     */
    void addLoadingData(NewsInfo newsInfo);

    /**
     * 加载完成
     */
    void stopProgress();


    /**
     * 加载更多数据
     */
    void updateMoreData(NewsInfo newsInfo);


}
