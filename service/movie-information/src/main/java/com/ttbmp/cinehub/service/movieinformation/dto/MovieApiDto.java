package com.ttbmp.cinehub.service.movieinformation.dto;


/**
 * @author Palmieri Ivan
 */
public class MovieApiDto {
    private final String movieName;
    private String movieVote;
    private String movieImageUrl;
    private String movieOverview;
    private String movieReleases;

    public MovieApiDto(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieName() {
        return movieName;
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

