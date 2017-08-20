package com.ricky.newschannel.module.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/7.
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    /**
     * 绑定布局文件
     * @return
     */
    protected abstract int attachLayoutId();

    /**
     * 初始化布局文件内容
     */
    protected abstract void initViews();

    /**
     * 更新布局
     */
    protected abstract void updateViews();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutId());
        ButterKnife.bind(this);
        initViews();
        updateViews();
    }

    /**
     * 初始化ToolBar
     * @param toolbar
     * @param homeEnable
     * @param title
     */
    public void initToolBar(Toolbar toolbar, boolean homeEnable, String title){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeEnable);
    }


    /**
     * 添加Fragment
     * @param containerViewId
     * @param fragment
     * @param strTag
     */
    protected void addFragment(int containerViewId,Fragment fragment,String strTag){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId,fragment);
        fragmentTransaction.addToBackStack(strTag);
        fragmentTransaction.commit();
    }


    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(containerViewId, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 这里要设置tag，上面也要设置tag
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            getSupportFragmentManager().popBackStack(tag, 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
