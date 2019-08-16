package com.android.example.moviewcatalogue.database.tv_show;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.example.moviewcatalogue.database.DatabaseHelper;
import com.android.example.moviewcatalogue.model.TvShow;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TABLE_TV_SHOW;
import static com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TvColumns.backdropPhoto;
import static com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TvColumns.dateOfRelease;
import static com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TvColumns.description;
import static com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TvColumns.genreId;
import static com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TvColumns.imgPhoto;
import static com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TvColumns.title;
import static com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TvColumns.userScore;

public class TvShowHelper {
    private static final String DATABASE_TABLE = TABLE_TV_SHOW;
    private static DatabaseHelper databaseHelper;
    private static TvShowHelper INSTANCE;

    private static SQLiteDatabase database;

    private TvShowHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static TvShowHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TvShowHelper(context);
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

    public ArrayList<TvShow> getAllFavoriteTvShow() {
        ArrayList<TvShow> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();

        TvShow tvShow;
        if (cursor.getCount() > 0) {
            do {
                tvShow = new TvShow();
                tvShow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));

                tvShow.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(title)));
                tvShow.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(description)));
                tvShow.setDateOfFirstAir(cursor.getString(cursor.getColumnIndexOrThrow(dateOfRelease)));
                tvShow.setImgPhoto(cursor.getString(cursor.getColumnIndexOrThrow(imgPhoto)));
                tvShow.setBackdropPhoto(cursor.getString(cursor.getColumnIndexOrThrow(backdropPhoto)));

                tvShow.setUserScore(cursor.getDouble(cursor.getColumnIndexOrThrow(userScore)));
                tvShow.setGenreId(cursor.getInt(cursor.getColumnIndexOrThrow(genreId)));
                arrayList.add(tvShow);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void insertFavoriteTvShow(TvShow tvShow) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID, tvShow.getId());
        contentValues.put(title, tvShow.getTitle());
        contentValues.put(description, tvShow.getDescription());
        contentValues.put(dateOfRelease, tvShow.getDateOfFirstAir());
        contentValues.put(imgPhoto, tvShow.getImgPhoto());
        contentValues.put(backdropPhoto, tvShow.getBackdropPhoto());
        contentValues.put(userScore, tvShow.getUserScore());
        contentValues.put(genreId, tvShow.getGenreId());

        database.insert(DATABASE_TABLE, null, contentValues);
    }

    public boolean isAlreadyLoved(int tvShowId) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        boolean isFavorite = false;

        try {
            Cursor cursor;
            String sql = "SELECT * FROM " + DATABASE_TABLE + " WHERE " + _ID + " = '" + tvShowId + "'"; // you can check it by comparing any unique value
            cursor = db.rawQuery(sql, null);
            isFavorite = cursor.getCount() > 0;
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isFavorite;
    }

    public void deleteFavoriteTvShow(int tvShowId) {
        database.delete(DATABASE_TABLE, _ID + " = '" + tvShowId + "'", null);
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
