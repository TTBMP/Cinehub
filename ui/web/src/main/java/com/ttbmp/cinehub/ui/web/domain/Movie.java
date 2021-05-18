package com.ttbmp.cinehub.ui.web.domain;

public class Movie {

    private int id;
    private String name;
    private String overview;
    private String movieUrl;
    private String vote;
    private String releases;

    public Movie() {
    }

    public Movie(int id, String name, String overview, String movieUrl, String vote, String releases) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.movieUrl = movieUrl;
        this.vote = vote;
        this.releases = releases;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getReleases() {
        return releases;
    }

    public void setReleases(String releases) {
        this.releases = releases;
    }

}
