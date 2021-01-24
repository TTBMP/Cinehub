package com.ttbmp.cinehub.service.movieinformation.dto;


/**
 * @author Palmieri Ivan
 */
public class MovieApiDto {
    private String name;
    private String vote;
    private String duration;
    private String imageUrl;
    private String overview;
    private String relases;

    public MovieApiDto(String name) {
        this.name = name;

    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String owerview) {
        this.overview = owerview;
    }

    public String getRelases() {
        return relases;
    }

    public void setRelases(String relases) {
        this.relases = relases;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

