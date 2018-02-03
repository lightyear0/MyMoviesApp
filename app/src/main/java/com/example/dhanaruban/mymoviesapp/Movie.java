package com.example.dhanaruban.mymoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by dhanaruban on 04/01/18.
 */

public class Movie implements Parcelable {

    private int voteCount;
    private int id;
    private int  video;
    private double voteAverage;
    private String title;
    private double popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    private ArrayList<Integer> genreIds;
    private String backdropPath;
    private boolean adult;
    private String overview;
    private String releaseDate;

    public Movie() { }
    public int getVote_count() {
        return voteCount;
    }

    public void setVote_count(int vote_count) {
        this.voteCount = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public double getVote_average() {
        return voteAverage;
    }

    public void setVote_average(double vote_average) {
        this.voteAverage = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return posterPath;
    }

    public void setPoster_path(String poster_path) {
        this.posterPath = poster_path;
    }

    public String getOriginal_language() {
        return originalLanguage;
    }

    public void setOriginal_language(String original_language) {
        this.originalLanguage = original_language;
    }

    public String getOriginal_title() {
        return originalTitle;
    }

    public void setOriginal_title(String original_title) {
        this.originalTitle = original_title;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genreIds;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genreIds = genre_ids;
    }

    public String getBackdrop_path() {
        return backdropPath;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdropPath = backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return releaseDate;
    }

    public void setRelease_date(String release_date) {
        this.releaseDate = release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(voteCount);
        dest.writeInt(id);
        dest.writeInt(video);
        dest.writeDouble(voteAverage);
        dest.writeString(title);
        dest.writeDouble(popularity);
        dest.writeString(posterPath);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeString(backdropPath);
        dest.writeValue(adult);
        dest.writeString(overview);
        dest.writeString(releaseDate);

    }

    private Movie(Parcel in) {

        voteCount = in.readInt();
        id = in.readInt();
        video = in.readInt();
        voteAverage = in.readDouble();
        title = in.readString();
        popularity = in.readDouble();
        posterPath = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        backdropPath = in.readString();
        adult = (boolean) in.readValue(Boolean.class.getClassLoader());
        overview = in.readString();
        releaseDate = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


}
