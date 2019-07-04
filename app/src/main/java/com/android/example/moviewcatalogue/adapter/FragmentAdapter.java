package com.android.example.moviewcatalogue.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.ui.MovieFragment;
import com.android.example.moviewcatalogue.ui.TvShowFragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public FragmentAdapter(Context mContext, FragmentManager fm) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MovieFragment();
        } else {
            return new TvShowFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_movie);
        } else if (position == 1) {
            return mContext.getString(R.string.category_tv_show);
        }
        return super.getPageTitle(position);
    }
}
