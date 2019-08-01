package com.android.example.moviewcatalogue.adapter.tab_layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.ui.main_menu.main_menu_fragment.favorite_fragment.FavoriteMovieFragment;
import com.android.example.moviewcatalogue.ui.main_menu.main_menu_fragment.favorite_fragment.FavoriteTvShowFragment;

public class FavoriteFragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public FavoriteFragmentAdapter(Context mContext, FragmentManager fm) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FavoriteTvShowFragment();
        } else {
            return new FavoriteMovieFragment();
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
            return mContext.getString(R.string.category_tv_show);
        } else if (position == 1) {
            return mContext.getString(R.string.category_movie);
        }
        return super.getPageTitle(position);
    }
}
