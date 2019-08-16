package com.android.example.moviewcatalogue.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.provider.BaseColumns._ID;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.getColumnDouble;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.getColumnInt;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.getColumnString;

public class Movie implements Parcelable {

    private int id, genreId;
    private Double userScore;
    private String title, description, dateOfRelease, imgPhoto, backdropPhoto;

    public Movie(JSONObject currentMovie) {
        try {
            int id = currentMovie.getInt("id");
            double userScore = currentMovie.getDouble("vote_average");
            String title = currentMovie.getString("title");
            String description = currentMovie.getString("overview");
            String dateOfRelase = currentMovie.getString("release_date");
            String photoUrl = currentMovie.getString("poster_path");
            String backdropUrl = currentMovie.getString("backdrop_path");

            String posterPath = "https://image.tmdb.org/t/p/original/" + photoUrl;
            String backdropPath = "https://image.tmdb.org/t/p/original/" + backdropUrl;

            JSONArray genre_ids = currentMovie.getJSONArray("genre_ids");
            int firstGenre = genre_ids.getInt(0);

            this.id = id;
            this.userScore = userScore;
            this.title = title;
            this.description = description;
            this.dateOfRelease = dateOfRelase;
            this.imgPhoto = posterPath;
            this.backdropPhoto = backdropPath;
            this.genreId = firstGenre;
            Log.d("Movie", String.valueOf(genreId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Movie(int id, int genreId, Double userScore, String title, String description, String dateOfRelease, String imgPhoto, String backdropPhoto) {
        this.id = id;
        this.genreId = genreId;
        this.userScore = userScore;
        this.title = title;
        this.description = description;
        this.dateOfRelease = dateOfRelease;
        this.imgPhoto = imgPhoto;
        this.backdropPhoto = backdropPhoto;
    }

    public Movie(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.userScore = getColumnDouble(cursor, MovieColumns.userScore);
        this.title = getColumnString(cursor, MovieColumns.title);
        this.description = getColumnString(cursor, MovieColumns.description);
        this.dateOfRelease = getColumnString(cursor, MovieColumns.dateOfRelease);
        this.imgPhoto = getColumnString(cursor, MovieColumns.imgPhoto);
        this.backdropPhoto = getColumnString(cursor, MovieColumns.backdropPhoto);
        this.genreId = getColumnInt(cursor, MovieColumns.genreId);
    }

    public Movie() {
    }

    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public void setImgPhoto(String imgPhoto) {
        this.imgPhoto = imgPhoto;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public void setBackdropPhoto(String backdropPhoto) {
        this.backdropPhoto = backdropPhoto;
    }

    public String getBackdropPhoto() {
        return backdropPhoto;
    }


    public int getGenreId() {
        return genreId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgPhoto() {
        return imgPhoto;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getUserScore() {
        return userScore;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.genreId);
        dest.writeValue(this.userScore);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.dateOfRelease);
        dest.writeString(this.imgPhoto);
        dest.writeString(this.backdropPhoto);
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.genreId = in.readInt();
        this.userScore = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.description = in.readString();
        this.dateOfRelease = in.readString();
        this.imgPhoto = in.readString();
        this.backdropPhoto = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
