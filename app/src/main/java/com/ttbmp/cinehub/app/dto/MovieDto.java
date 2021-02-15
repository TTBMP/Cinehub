package com.ttbmp.cinehub.app.dto;


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

    public MovieDto(int id, String name, String overview, String duration, String movieUrl, String vote, String releases) {
        this.id = id;
        this.name = name;
        this.vote = vote;
        this.overview = overview;
        this.releases = releases;
        this.movieUrl = movieUrl;
        this.duration = duration;
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
