package com.ttbmp.cinehub.domain;

/**
 * @author Ivan Palmieri, Fabio Buracchi
 */
public class Movie {

    private int id;
    private String name;
    private String overview;
    private int duration;
    private String imageUrl;
    private String rating;
    private String releaseDate;

    public Movie(int id, String name, String overview, int duration, String imageUrl, String rating, String releaseDate) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.duration = duration;
        this.overview = overview;
        this.releaseDate = releaseDate;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}

