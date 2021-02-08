package com.ttbmp.cinehub.app.dto;


/**
 * @author Palmieri Ivan
 */
public class MovieDto {
    private int id;
    private String name;
    private String vote;
    private String overview;
    private String releases;
    private String movieUrl;
    private String duration;


    public MovieDto(String name) {
        this.name = name;
    }

    public MovieDto(int id, String name, String vote, String overview, String releases, String movieUrl) {
        this.id = id;
        this.name = name;
        this.vote = vote;
        this.overview = overview;
        this.releases = releases;
        this.movieUrl = movieUrl;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
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

}
