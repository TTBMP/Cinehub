package com.ttbmp.cinehub.ui.web.domain;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
public class Projection {

    private int id;
    private String date;
    private String startTime;
    private int hallId;
    private int movieId;
    private int cinemaId;   // TODO: remove
    private long basePrice;

    public Projection() {
    }

    public Projection(int id, int hallId, String date, int movieId, int cinemaId, String startTime, long basePrice) {
        this.id = id;
        this.hallId = hallId;
        this.date = date;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.startTime = startTime;
        this.basePrice = basePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(long basePrice) {
        this.basePrice = basePrice;
    }

}
