package com.android.example.moviewcatalogue.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.android.example.moviewcatalogue.model.TvShow;

import java.util.ArrayList;

public class TvShowViewModel {

    private MutableLiveData<ArrayList<TvShow>> listTvShows = new MutableLiveData<>();

    public void setTvShow() {
        // request API
    }

    public LiveData<ArrayList<TvShow>> getTvShows() {
        return listTvShows;
    }
}
