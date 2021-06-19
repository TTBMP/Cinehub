package com.ttbmp.cinehub.app.dto;


import com.ttbmp.cinehub.domain.Movie;
import lombok.Value;

/**
 * @author Ivan Palmieri
 */
@Value
public class MovieDto {

    int id;
    String name;
    String overview;
    String duration;
    String movieUrl;
    String vote;
    String releases;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.overview = movie.getOverview();
        this.duration = Integer.toString(movie.getDuration());
        this.movieUrl = movie.getImageUrl();
        this.vote = movie.getRating();
        this.releases = movie.getReleaseDate();
    }

}
