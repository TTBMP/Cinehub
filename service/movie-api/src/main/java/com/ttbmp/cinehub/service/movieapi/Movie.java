package com.ttbmp.cinehub.service.movieapi;

import com.google.gson.annotations.SerializedName;

/**
 * @author Palmieri Ivan, Fabio Buracchi
 */
public class Movie {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String name;

    @SerializedName("vote_average")
    private String vote;

    @SerializedName("poster_path")
    private String imageUrl;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releases;

    @SerializedName("runtime")
    private String duration;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}

