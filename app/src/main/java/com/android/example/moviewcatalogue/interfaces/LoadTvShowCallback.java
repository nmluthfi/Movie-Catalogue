package com.android.example.moviewcatalogue.interfaces;

import android.database.Cursor;

public interface LoadTvShowCallback {
    void preExecute();
    void postExecute(Cursor cursor);
}
