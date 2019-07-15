package com.android.example.moviewcatalogue.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.android.example.moviewcatalogue.model.Movie;
import com.android.example.moviewcatalogue.model.TvShow;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    private static final String API_KEY = "ISI SESUAI API ANDA";
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvShow>> listTvShows = new MutableLiveData<>();

    void setMovie() {
        // request API
    }

    void setTvShow() {
        // request API
    }

    LiveData<ArrayList<Movie>> getMovies() {
        return listMovies;
    }

    LiveData<ArrayList<Movie>> getTvShows() {
        return listMovies;
    }

}
