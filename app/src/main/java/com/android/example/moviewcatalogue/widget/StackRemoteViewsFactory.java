package com.android.example.moviewcatalogue.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.database.DatabaseHelper;
import com.android.example.moviewcatalogue.database.movie.MovieContract;
import com.android.example.moviewcatalogue.database.tv_show.TvShowContract;
import com.android.example.moviewcatalogue.model.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final List<Movie> mWidgetFavorite = new ArrayList<>();
    private final Context mContext;
    private Cursor cursor;


    StackRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        loadWidgetData();
    }

    @Override
    public void onDataSetChanged() {
        loadWidgetData();
    }

    private void loadWidgetData() {
        mWidgetFavorite.clear();

        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase database = helper.getWritableDatabase();

        cursor =
                database.query(MovieContract.TABLE_MOVIE, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Movie fav = new Movie(cursor);
                mWidgetFavorite.add(fav);
                Log.i("WIDGET_DATA", "loadWidgetData: " + fav.getTitle());
                Log.i("WIDGET_DATA", "loadWidgetData: " + fav.getBackdropPhoto());
                Log.i("WIDGET_DATA", "loadWidgetData: " + mWidgetFavorite.size());
            } while (cursor.moveToNext());
        }

        cursor =
                database.query(TvShowContract.TABLE_TV_SHOW, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Movie fav = new Movie(cursor);
                mWidgetFavorite.add(fav);

                Log.i("WIDGET_DATA", "loadWidgetData: " + fav.getTitle());
                Log.i("WIDGET_DATA", "loadWidgetData: " + fav.getBackdropPhoto());
                Log.i("WIDGET_DATA", "loadWidgetData: " + mWidgetFavorite.size());
            } while (cursor.moveToNext());
            cursor.close();
        }

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetFavorite.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

        Bundle extras = new Bundle();
        extras.putInt(ImageFavoriteWidget.EXTRA_ITEM, position);

        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setImageViewResource(R.id.imageView, R.drawable.backdrop_spiderman);
        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);

        String backdropPhoto = mWidgetFavorite.get(position).getBackdropPhoto();
        Log.i("WIDGET_DATA", "loadWidgetData: " + backdropPhoto);
        try {
            Bitmap preview = Glide.with(mContext)
                    .asBitmap()
                    .load(backdropPhoto)
                    .apply(new RequestOptions().fitCenter())
                    .submit()
                    .get();
            rv.setImageViewBitmap(R.id.imageView, preview);
        } catch (InterruptedException | ExecutionException e) {
            Log.d("Widget Load Error", "error");
        }

        return rv;
}

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
