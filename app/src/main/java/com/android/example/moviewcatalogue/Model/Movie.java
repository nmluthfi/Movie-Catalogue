package com.android.example.moviewcatalogue.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private int imgPhoto;
    private String title, description, userScore, dateOfRelease;

    public int getImgPhoto() {
        return imgPhoto;
    }

    public void setImgPhoto(int imgPhoto) {
        this.imgPhoto = imgPhoto;
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

    public String getUserScore() {
        return userScore;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imgPhoto);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.userScore);
        dest.writeString(this.dateOfRelease);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.imgPhoto = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.userScore = in.readString();
        this.dateOfRelease = in.readString();
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
