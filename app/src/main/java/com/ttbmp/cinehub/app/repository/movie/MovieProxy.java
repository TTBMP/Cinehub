package com.ttbmp.cinehub.app.repository.movie;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.LazyLoadingException;
import com.ttbmp.cinehub.app.service.movieapi.MovieApiService;
import com.ttbmp.cinehub.app.service.movieapi.MovieApiServiceException;
import com.ttbmp.cinehub.domain.Movie;
import lombok.EqualsAndHashCode;

/**
 * @author Fabio Buracchi
 */
@EqualsAndHashCode(callSuper = true)
public class MovieProxy extends Movie {

    private final MovieApiService movieApiService;

    private boolean isMovieLoaded = false;

    public MovieProxy(ServiceLocator serviceLocator, int id) {
        super(id, null, null, 0, null, null, null);
        this.movieApiService = serviceLocator.getService(MovieApiService.class);
    }

    @Override
    public String getName() {
        if (!isMovieLoaded) {
            loadMovie();
        }
        return super.getName();
    }

    @Override
    public String getRating() {
        if (!isMovieLoaded) {
            loadMovie();
        }
        return super.getRating();
    }

    @Override
    public String getImageUrl() {
        if (!isMovieLoaded) {
            loadMovie();
        }
        return super.getImageUrl();
    }

    @Override
    public int getDuration() {
        if (!isMovieLoaded) {
            loadMovie();
        }
        return super.getDuration();
    }

    @Override
    public String getOverview() {
        if (!isMovieLoaded) {
            loadMovie();
        }
        return super.getOverview();
    }

    @Override
    public String getReleaseDate() {
        if (!isMovieLoaded) {
            loadMovie();
        }
        return super.getReleaseDate();
    }

    private void loadMovie() {
        try {
            var movie = movieApiService.getMovie(getId());
            setName(movie.getName());
            setOverview(movie.getOverview());
            setDuration(movie.getDuration());
            setImageUrl(movie.getImageUrl());
            setRating(movie.getRating());
            setReleaseDate(movie.getReleaseDate());
            isMovieLoaded = true;
        } catch (MovieApiServiceException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

}
