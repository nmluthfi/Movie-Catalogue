package com.android.example.moviewcatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TvShow implements Parcelable {

    private int id;
    private Double userScore;
    private String title, description, dateOfFirstAir, imgPhoto, backdropPhoto;
    private ArrayList<Integer> genreId = new ArrayList<>();

    public TvShow(JSONObject currentTvShow) {
        try {
            int id = currentTvShow.getInt("id");
            double userScore = currentTvShow.getDouble("vote_average");
            String title = currentTvShow.getString("name");
            String description = currentTvShow.getString("overview");
            String dateOfRelase = currentTvShow.getString("first_air_date");
            String photoUrl = currentTvShow.getString("poster_path");
            String backdropUrl = currentTvShow.getString("backdrop_path");

            String posterPath = "https://image.tmdb.org/t/p/original/" + photoUrl;
            String backdropPath = "https://image.tmdb.org/t/p/original/" + backdropUrl;

            JSONArray genre_ids = currentTvShow.getJSONArray("genre_ids");
            for (int i = 0; i < genre_ids.length(); i++) {
                this.genreId.add(genre_ids.getInt(i));
            }

            this.id = id;
            this.userScore = userScore;
            this.title = title;
            this.description = description;
            this.dateOfFirstAir = dateOfRelase;
            this.imgPhoto = posterPath;
            this.backdropPhoto = backdropPath;
            Log.d("TV show", title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TvShow() {
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

    public void setDateOfFirstAir(String dateOfFirstAir) {
        this.dateOfFirstAir = dateOfFirstAir;
    }

    public void setImgPhoto(String imgPhoto) {
        this.imgPhoto = imgPhoto;
    }

    public void setBackdropPhoto(String backdropPhoto) {
        this.backdropPhoto = backdropPhoto;
    }

    public void setGenreId(ArrayList<Integer> genreId) {
        this.genreId = genreId;
    }

    public String getBackdropPhoto() {
        return backdropPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getUserScore() {
        return userScore;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDateOfFirstAir() {
        return dateOfFirstAir;
    }

    public String getImgPhoto() {
        return imgPhoto;
    }

    public ArrayList<Integer> getGenreId() {
        return genreId;
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
        dest.writeString(this.dateOfFirstAir);
        dest.writeString(this.imgPhoto);
        dest.writeString(this.backdropPhoto);
        dest.writeList(this.genreId);
    }

    protected TvShow(Parcel in) {
        this.id = in.readInt();
        this.userScore = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.description = in.readString();
        this.dateOfFirstAir = in.readString();
        this.imgPhoto = in.readString();
        this.backdropPhoto = in.readString();
        this.genreId = new ArrayList<Integer>();
        in.readList(this.genreId, Integer.class.getClassLoader());
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
