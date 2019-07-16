package com.android.example.moviewcatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie implements Parcelable {

    private int id;
    private Double userScore;
    private String title, description, dateOfRelease, imgPhoto;
    private ArrayList<Integer> genreId = new ArrayList<>();

    public Movie(JSONObject currentMovie) {
        try {
            int id = currentMovie.getInt("id");
            double userScore = currentMovie.getDouble("vote_average");
            String title = currentMovie.getString("title");
            String description = currentMovie.getString("overview");
            String dateOfRelase = currentMovie.getString("release_date");
            String photoUrl = currentMovie.getString("poster_path");
            String posterPath = "https://image.tmdb.org/t/p/w342/" + photoUrl;

            JSONArray genre_ids = currentMovie.getJSONArray("genre_ids");
            for (int i = 0; i < genre_ids.length(); i++) {
                this.genreId.add(genre_ids.getInt(i));
            }

            this.id = id;
            this.userScore = userScore;
            this.title = title;
            this.description = description;
            this.dateOfRelease = dateOfRelase;
            this.imgPhoto = posterPath;
            Log.d("Movie", String.valueOf(genreId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getGenreId() {
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
        dest.writeValue(this.userScore);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.dateOfRelease);
        dest.writeString(this.imgPhoto);
        dest.writeList(this.genreId);
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.userScore = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.description = in.readString();
        this.dateOfRelease = in.readString();
        this.imgPhoto = in.readString();
        this.genreId = new ArrayList<Integer>();
        in.readList(this.genreId, Integer.class.getClassLoader());
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
