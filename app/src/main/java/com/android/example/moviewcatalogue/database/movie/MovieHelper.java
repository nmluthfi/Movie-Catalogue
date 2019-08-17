package com.android.example.moviewcatalogue.database.movie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.example.moviewcatalogue.database.DatabaseHelper;
import com.android.example.moviewcatalogue.model.Movie;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.backdropPhoto;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.dateOfRelease;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.description;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.genreId;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.imgPhoto;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.title;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.userScore;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.TABLE_MOVIE;

public class MovieHelper {

    private static final String DATABASE_TABLE = TABLE_MOVIE;
    private static DatabaseHelper databaseHelper;
    private static MovieHelper INSTANCE;

    private static SQLiteDatabase database;

    private MovieHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<Movie> getAllFavoriteMovie() {
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();

        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));

                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(title)));
                movie.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(description)));
                movie.setDateOfRelease(cursor.getString(cursor.getColumnIndexOrThrow(dateOfRelease)));
                movie.setImgPhoto(cursor.getString(cursor.getColumnIndexOrThrow(imgPhoto)));
                movie.setBackdropPhoto(cursor.getString(cursor.getColumnIndexOrThrow(backdropPhoto)));

                movie.setUserScore(cursor.getDouble(cursor.getColumnIndexOrThrow(userScore)));
                movie.setGenreId(cursor.getInt(cursor.getColumnIndexOrThrow(genreId)));
                arrayList.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean isAlreadyLoved(int movieId) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        boolean isFavorite = false;

        try {
            Cursor cursor;
            String sql = "SELECT * FROM " + DATABASE_TABLE + " WHERE " + _ID + " = '" + movieId + "'"; // you can check it by comparing any unique value
            cursor = db.rawQuery(sql, null);
            isFavorite = cursor.getCount() > 0;
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isFavorite;
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , _ID + " ASC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }
}
