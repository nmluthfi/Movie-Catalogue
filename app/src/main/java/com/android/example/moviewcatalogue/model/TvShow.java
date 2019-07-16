package com.android.example.moviewcatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class TvShow implements Parcelable {

    private int id;
    private Double userScore;
    private String title, description, dateOfRelease, imgPhoto;
    private ArrayList<Integer> genreId = new ArrayList<>();

    public TvShow() {
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

    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public String getImgPhoto() {
        return imgPhoto;
    }

    public void setImgPhoto(String imgPhoto) {
        this.imgPhoto = imgPhoto;
    }

    public ArrayList<Integer> getGenreId() {
        return genreId;
    }

    public void setGenreId(ArrayList<Integer> genreId) {
        this.genreId = genreId;
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

    protected TvShow(Parcel in) {
        this.id = in.readInt();
        this.userScore = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.description = in.readString();
        this.dateOfRelease = in.readString();
        this.imgPhoto = in.readString();
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
