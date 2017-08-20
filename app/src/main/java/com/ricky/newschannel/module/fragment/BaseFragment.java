package com.ricky.newschannel.module.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ricky.newschannel.module.activity.BaseActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * 频道各个类型的碎片
 * Created by Administrator on 2017/4/9.
 */

public abstract class BaseFragment extends RxFragment {

    //缓存Fragment View
    private View mRootView;
    public WindowManager wm; //通过WindowManager获取屏幕的宽度
    private boolean mIsMulti = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wm = getActivity().getWindowManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            ButterKnife.bind(this, mRootView);
            initInjector();
            initViews();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    protected abstract int attachLayoutRes();


    /**
     * 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews();


    /**
     * 初始化Fragment中的ToolBar
     *
     * @param toolbar
     * @param homeEnable
     * @param title
     */
    protected void initFragmentToolBar(Toolbar toolbar, boolean homeEnable, String title) {
        ((BaseActivity) getActivity()).initToolBar(toolbar, homeEnable, title);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
