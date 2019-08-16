package com.android.example.moviewcatalogue.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import com.android.example.moviewcatalogue.database.movie.MovieHelper;
import com.android.example.moviewcatalogue.database.tv_show.TvShowContract;
import com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TvColumns;
import com.android.example.moviewcatalogue.database.tv_show.TvShowHelper;
import com.android.example.moviewcatalogue.ui.main_menu.main_menu_fragment.favorite_fragment.FavoriteMovieFragment;

import static com.android.example.moviewcatalogue.database.movie.MovieContract.AUTHORITY;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.CONTENT_URI;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.TABLE_MOVIE;
import static com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TABLE_TV_SHOW;

public class MovieTvShowProvider extends ContentProvider {

    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;
    private static final int TV_SHOW = 3;
    private static final int TV_SHOW_ID = 4;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private MovieHelper movieHelper;
    private TvShowHelper tvShowHelper;

    static {
        sUriMatcher.addURI(AUTHORITY, TABLE_MOVIE, MOVIE);

        sUriMatcher.addURI(AUTHORITY, TABLE_MOVIE + "/#", MOVIE_ID);

        sUriMatcher.addURI(TvShowContract.AUTHORITY, TABLE_TV_SHOW, TV_SHOW);

        sUriMatcher.addURI(TvShowContract.AUTHORITY, TABLE_TV_SHOW + "/#", TV_SHOW_ID);
    }

    @Override
    public boolean onCreate() {
        movieHelper = MovieHelper.getInstance(getContext());
        tvShowHelper = TvShowHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        movieHelper.open();
        tvShowHelper.open();
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                cursor = movieHelper.queryProvider();
                break;
            case MOVIE_ID:
                cursor = movieHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            case TV_SHOW:
                cursor = tvShowHelper.queryProvider();
                break;
            case TV_SHOW_ID:
                cursor = tvShowHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        movieHelper.open();
        tvShowHelper.open();
        long added;
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                added = movieHelper.insertProvider(values);
                getContext().getContentResolver().notifyChange(CONTENT_URI,
                        new FavoriteMovieFragment.DataObserver(new Handler(), getContext()));
                break;
            case TV_SHOW:
                added = tvShowHelper.insertProvider(values);
                getContext().getContentResolver().notifyChange(TvColumns.CONTENT_URI,
                        new FavoriteMovieFragment.DataObserver(new Handler(), getContext()));
                break;
            default:
                added = 0;
                break;
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        movieHelper.open();
        tvShowHelper.open();
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
                deleted = movieHelper.deleteProvider(uri.getLastPathSegment());
                getContext().getContentResolver().notifyChange(CONTENT_URI,
                        new FavoriteMovieFragment.DataObserver(new Handler(), getContext()));
                break;
            case TV_SHOW_ID:
                deleted = tvShowHelper.deleteProvider(uri.getLastPathSegment());
                getContext().getContentResolver().notifyChange(TvColumns.CONTENT_URI,
                        new FavoriteMovieFragment.DataObserver(new Handler(), getContext()));
                break;
            default:
                deleted = 0;
                break;
        }
        return deleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}
