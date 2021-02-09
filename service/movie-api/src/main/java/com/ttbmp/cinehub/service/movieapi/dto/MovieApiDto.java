package com.ttbmp.cinehub.service.movieapi.dto;


/**
 * @author Palmieri Ivan
 */
public class MovieApiDto {
    private int id;
    private String movieName;
    private String movieVote;
    private String movieImageUrl;
    private String movieOverview;
    private String movieReleases;

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
}

