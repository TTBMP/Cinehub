package com.ttbmp.cinehub.app.repository.movie;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.app.service.movieapi.MovieApiService;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockMovieRepository implements MovieRepository {

    private static final List<MovieData> MOVIE_DATA_LIST = new ArrayList<>();

    static {
        MOVIE_DATA_LIST.add(new MovieData(3));
        MOVIE_DATA_LIST.add(new MovieData(5));
        MOVIE_DATA_LIST.add(new MovieData(6));
        MOVIE_DATA_LIST.add(new MovieData(8));
        MOVIE_DATA_LIST.add(new MovieData(11));
        MOVIE_DATA_LIST.add(new MovieData(15));
    }

    private final ServiceLocator serviceLocator;

    public MockMovieRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<MovieData> getMovieDataList() {
        return MOVIE_DATA_LIST;
    }

    @Override
    public Movie getMovie(Projection projection) {
        int projectionMovieId = MockProjectionRepository.getProjectionDataList().stream()
                .filter(d -> d.getId() == projection.getId())
                .map(MockProjectionRepository.ProjectionData::getMovieId)
                .collect(Collectors.toList())
                .get(0);
        return MOVIE_DATA_LIST.stream()
                .filter(d -> d.id == projectionMovieId)
                .map(d -> new MovieProxy(d.id, serviceLocator.getService(MovieApiService.class)))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public Movie getMovieById(Integer movieId) {
        return MOVIE_DATA_LIST.stream()
                .filter(d -> d.id == movieId)
                .map(d -> new MovieProxy(d.id, serviceLocator.getService(MovieApiService.class)))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public List<Movie> getMovieList(String localDate) {
        var projectionMovieIdList = MockProjectionRepository.getProjectionDataList().stream()
                .filter(d -> d.getDate().equals(localDate))
                .map(MockProjectionRepository.ProjectionData::getMovieId)
                .distinct()
                .collect(Collectors.toList());
        return MOVIE_DATA_LIST.stream()
                .filter(d -> projectionMovieIdList.contains(d.id))
                .map(d -> new MovieProxy(d.id, serviceLocator.getService(MovieApiService.class)))
                .collect(Collectors.toList());
    }

    public static class MovieData {

        private int id;

        public MovieData(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }

}
