package com.ricky.newschannel.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/30.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> titleList;
    private List<Fragment> fragmentList;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        if (fragmentList != null) {
            return fragmentList.get(position);
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        if (fragmentList != null) {
            return fragmentList.size();
        } else {
            return 0;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    /**
     * 更新NewsViewPager的item内容
     * @param fragments
     * @param titles
     */
    public void setItems(List<Fragment> fragments,List<String> titles){
        fragmentList = fragments;
        titleList = titles;
        notifyDataSetChanged();
    }

}
