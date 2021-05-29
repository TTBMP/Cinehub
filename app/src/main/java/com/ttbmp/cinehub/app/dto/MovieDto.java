package com.ttbmp.cinehub.app.dto;


import com.ttbmp.cinehub.domain.Movie;

/**
 * @author Ivan Palmieri
 */
public class MovieDto {

    private int id;
    private String name;
    private String overview;
    private String duration;
    private String movieUrl;
    private String vote;
    private String releases;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.overview = movie.getOverview();
        this.duration = Integer.toString(movie.getDuration());
        this.movieUrl = movie.getImageUrl();
        this.vote = movie.getRating();
        this.releases = movie.getReleaseDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleases() {
        return releases;
    }

    public void setReleases(String releases) {
        this.releases = releases;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
