package com.android.example.moviewcatalogue.interfaces;

import com.android.example.moviewcatalogue.model.TvShow;

import java.util.ArrayList;

public interface LoadTvShowCallback {
    void preExecute();
    void postExecute(ArrayList<TvShow> tvShows);
}
