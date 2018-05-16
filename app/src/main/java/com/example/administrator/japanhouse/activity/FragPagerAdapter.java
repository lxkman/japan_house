package com.example.administrator.japanhouse.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by   admin on 2018/5/9.
 */

public class FragPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public FragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
