package com.ttbmp.cinehub.app.dto;

/**
 * @author Ivan Palmieri
 */
public class ProjectionDto {

    private int id;
    private String date;
    private String startTime;
    private long basePrice;
    private MovieDto movieDto;
    private HallDto hallDto;

    public ProjectionDto(int id, String date, String startTime, long basePrice, MovieDto movie, HallDto hall) {
        this.id = id;
        this.movieDto = movie;
        this.hallDto = hall;
        this.startTime = startTime;
        this.date = date;
        this.basePrice = basePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(long basePrice) {
        this.basePrice = basePrice;
    }

    public MovieDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(MovieDto movieDto) {
        this.movieDto = movieDto;
    }

    public HallDto getHallDto() {
        return hallDto;
    }

    public void setHallDto(HallDto hallDto) {
        this.hallDto = hallDto;
    }

}
