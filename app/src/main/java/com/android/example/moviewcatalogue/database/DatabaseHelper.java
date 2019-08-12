package com.android.example.moviewcatalogue.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.example.moviewcatalogue.database.movie.MovieContract;
import com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns;
import com.android.example.moviewcatalogue.database.tv_show.TvShowContract;
import com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TvColumns;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "movie_catalogue";

    private static final int DATABASE_VERSION = 3;

    private static final String SQL_CREATE_TABLE_MOVIE =
            String.format("CREATE TABLE %s"
                            + "(%s INTEGER NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s REAL NOT NULL,"
                            + "%s INTEGER NOT NULL)",
                    MovieContract.TABLE_MOVIE,
                    MovieColumns._ID,
                    MovieColumns.title,
                    MovieColumns.description,
                    MovieColumns.dateOfRelease,
                    MovieColumns.imgPhoto,
                    MovieColumns.backdropPhoto,
                    MovieColumns.userScore,
                    MovieColumns.genreId);

    private static final String SQL_CREATE_TABLE_TV_SHOW =
            String.format("CREATE TABLE %s"
                            + "(%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL,"
                            + "%s REAL NOT NULL,"
                            + "%s INTEGER NOT NULL)",
                    TvShowContract.TABLE_TV_SHOW,
                    TvColumns._ID,
                    TvColumns.title,
                    TvColumns.description,
                    TvColumns.dateOfRelease,
                    TvColumns.imgPhoto,
                    TvColumns.backdropPhoto,
                    TvColumns.userScore,
                    TvColumns.genreId);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_TV_SHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + TvShowContract.TABLE_TV_SHOW);
        onCreate(db);
    }
}
