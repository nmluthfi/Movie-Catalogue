package com.android.example.moviewcatalogue.interfaces;

import com.android.example.moviewcatalogue.model.Movie;

import java.util.ArrayList;

public interface LoadMovieCallback {
    void preExecute();
    void postExecute(ArrayList<Movie> movies);
}

