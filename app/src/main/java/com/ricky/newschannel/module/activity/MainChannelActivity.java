package com.ricky.newschannel.module.activity;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ricky.newschannel.R;
import com.ricky.newschannel.api.RetrofitService;
import com.ricky.newschannel.module.fragment.news.NewsMainFragment;

import butterknife.BindView;

/**
 * 主页的Activity
 */
public class MainChannelActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.channel_framelayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private int mNavItemId = -1;


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case R.id.nav_news_channel:
                    replaceFragment(R.id.channel_framelayout, new NewsMainFragment(), "News");
                    break;
            }
            mNavItemId = -1;
            return true;
        }
    });


    @Override
    protected int attachLayoutId() {
        return R.layout.activity_channel_main;
    }

    @Override
    protected void initViews() {
        initDrawerLayout(mDrawerLayout, mNavigationView);
        RetrofitService.init();
    }


    @Override
    protected void updateViews() {
        mNavigationView.setCheckedItem(R.id.nav_news_channel);
        addFragment(R.id.channel_framelayout, new NewsMainFragment(), "News");
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        if (item.isChecked()) {
            return true;
        }
        mNavItemId = item.getItemId();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 初始化DrawerLayout
     *
     * @param mDrawerLayout
     * @param mNavigationView
     */
    private void initDrawerLayout(DrawerLayout mDrawerLayout, NavigationView mNavigationView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //主页延时至状态栏
            mDrawerLayout.setFitsSystemWindows(true);
            //同时把侧边栏延伸至顶部状态栏
            mDrawerLayout.setClipToPadding(false);
            Log.e("123", "initDrawerLayout: 初始化");
            mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                @Override
                public void onDrawerClosed(View drawerView) {
                    //Handler发送消息控制当前页面的更新
                    mHandler.sendEmptyMessage(mNavItemId);
                }
            });
            mNavigationView.setNavigationItemSelectedListener(this);
        }
    }


    @Override
    public void onBackPressed() {
       finish();
    }
}
