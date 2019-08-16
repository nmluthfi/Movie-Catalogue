package com.android.example.moviewcatalogue.interfaces;

import android.database.Cursor;

public interface LoadMovieCallback {
    void preExecute();

    void postExecute(Cursor cursor);
}

