package com.zjc.news.module.news.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zjc.news.module.news.view.fragment.DynamicFragment;
import com.zjc.news.utils.ToastUtil;

/**
 * Created by ZJC on 2018-06-13.
 */

//据说使用FragmentStatePagerAdapter能防止之前用FragmentPagerAdapter的错位
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private String[] mTitles = {"头条","科技","国内","国际","体育","娱乐","财经","时尚","军事","社会",};
    public static final String POSITION = "position";

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        DynamicFragment fragment = new DynamicFragment();
        Log.d("选中了位置", String.valueOf(position));
        ToastUtil.showToast(context, "click"+position);
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, position);
        bundle.putString("title", mTitles[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((Fragment) object).getView();

    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment)object;
        fragment.getActivity().getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }
}
