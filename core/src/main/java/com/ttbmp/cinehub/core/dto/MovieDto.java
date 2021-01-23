package com.ttbmp.cinehub.core.dto;



/**
 * @author Palmieri Ivan
 */
public class MovieDto {
    private  String name;
    private  String vote;
    private  String duration;
    private  String overview;
    private  String releases;
    private String movieUrl;


    public MovieDto(String name) {
        this.name= name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleases(String releases) {
        this.releases = releases;
    }

    public String getName() {
        return name;
    }

    public String getVote() {
        return vote;
    }

    public String getDuration() {
        return duration;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleases() {
        return releases;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }
}
