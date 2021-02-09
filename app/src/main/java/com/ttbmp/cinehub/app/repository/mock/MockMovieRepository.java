package com.ttbmp.cinehub.app.repository.mock;

import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.app.repository.MovieRepository;
import com.ttbmp.cinehub.app.repository.ProjectionRepository;
import com.ttbmp.cinehub.app.service.movie.MovieApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public class MockMovieRepository implements MovieRepository {

    private static final Map<Integer, Movie> movieMap = new HashMap<>();

    static {
        movieMap.put(3, new Movie(3));
        movieMap.put(5, new Movie(5));
        movieMap.put(6, new Movie(6));
        movieMap.put(8, new Movie(8));
        movieMap.put(11, new Movie(11));
        movieMap.put(15, new Movie(15));
    }

    private final MovieApiService movieApiService;


    public MockMovieRepository(MovieApiService movieApiService) {
        this.movieApiService = movieApiService;
    }

    public static List<Movie> getAllMovie() {
        return new ArrayList<>(movieMap.values());
    }

    @Override
    public List<Movie> getMovieList(String localDate) throws IOException {
        ProjectionRepository projectionRepository = new MockProjectionRepository();
        List<Movie> result = new ArrayList<>();
        List<Projection> projectionList = projectionRepository.getProjectionList(localDate);
        for (Projection projection : projectionList) {
            Movie movie = projection.getMovie();
            if (!result.contains(movie)) {
                if (movie.getName() == null) {
                    MovieDto movieDto = movieApiService.getMovie(movie.getId());
                    movie.setName(movieDto.getName());
                    movie.setOverview(movieDto.getOverview());
                    movie.setRelases(movieDto.getReleases());
                    movie.setVote(movieDto.getVote());
                    movie.setImageUrl(movieDto.getMovieUrl());
                    movie.setDuration(movieDto.getDuration());
                }
                result.add(movie);
            }
        }
        return result;
    }
}
