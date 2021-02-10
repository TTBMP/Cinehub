package com.ttbmp.cinehub.service.movieapi.dto;


import com.google.gson.annotations.SerializedName;

/**
 * @author Palmieri Ivan, Fabio Buracchi
 */
public class MovieApiDto {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String movieName;

    @SerializedName("vote_average")
    private String movieVote;

    @SerializedName("poster_path")
    private String movieImageUrl;

    @SerializedName("overview")
    private String movieOverview;

    @SerializedName("release_date")
    private String movieReleases;

    @SerializedName("runtime")
    private String movieDuration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieVote() {
        return movieVote;
    }

    public void setMovieVote(String movieVote) {
        this.movieVote = movieVote;
    }

    public String getMovieImageUrl() {
        return movieImageUrl;
    }

    public void setMovieImageUrl(String movieImageUrl) {
        this.movieImageUrl = movieImageUrl;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieReleases() {
        return movieReleases;
    }

    public void setMovieReleases(String movieReleases) {
        this.movieReleases = movieReleases;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

}

