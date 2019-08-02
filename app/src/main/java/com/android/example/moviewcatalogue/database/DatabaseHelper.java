package com.android.example.moviewcatalogue.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.example.moviewcatalogue.database.movie.MovieContract;
import com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "movie_catalogue";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE =
            String.format("CREATE TABLE %s"
                            + "(%s INTEGER PRIMARY KEY AUTOINCREMENT,"
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

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.TABLE_MOVIE);
        onCreate(db);
    }
}
