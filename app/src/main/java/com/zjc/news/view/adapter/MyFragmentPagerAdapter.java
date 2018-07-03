package com.zjc.news.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zjc.news.view.fragment.DomesticFragment;
import com.zjc.news.view.fragment.EntertainFragment;
import com.zjc.news.view.fragment.FashionFragment;
import com.zjc.news.view.fragment.FinacialFragment;
import com.zjc.news.view.fragment.MilitaryFragment;
import com.zjc.news.view.fragment.SocialFragment;
import com.zjc.news.view.fragment.SportsFragment;
import com.zjc.news.view.fragment.TechFragment;
import com.zjc.news.view.fragment.InterFragment;
import com.zjc.news.view.fragment.ToutiaoFragment;

/**
 * Created by ZJC on 2018-06-13.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = {"头条","科技","国内","国际","体育","娱乐","财经","时尚","军事","社会",};

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ToutiaoFragment();
        } else if (position == 1) {
            return new TechFragment();
        } else if (position == 2) {
            return new DomesticFragment();
        } else if (position == 3) {
            return new InterFragment();
        } else if (position == 4) {
            return new SportsFragment();
        } else if (position == 5) {
            return new EntertainFragment();
        } else if (position == 6) {
            return new FinacialFragment();
        } else if (position == 7) {
            return new FashionFragment();
        } else if (position == 8) {
            return new MilitaryFragment();
        } else if (position == 9) {
            return new SocialFragment();
        }

        return new SocialFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
