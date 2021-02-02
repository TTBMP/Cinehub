package com.ttbmp.cinehub.core.entity;

/**
 * @author Palmieri Ivan
 */
public class Movie {

    private String name;
    private String vote;
    private String imageUrl;
    private String duration;
    private String overview;
    private String relases;

    public Movie(String name) {
        this.name = name;
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

    public String getRelases() {
        return relases;
    }

    public void setRelases(String relases) {
        this.relases = relases;
    }
}

