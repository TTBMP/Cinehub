package com.ttbmp.cinehub.app.repository.movie;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Fabio Buracchi
 */
public class MockMovieRepository implements MovieRepository {

    private static final List<MovieData> MOVIE_DATA_LIST = new ArrayList<>();

    static {
        IntStream.range(15, 23).forEach(i -> MOVIE_DATA_LIST.add(new MovieData(i)));
    }

    private final ServiceLocator serviceLocator;

    public MockMovieRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<MovieData> getMovieDataList() {
        return MOVIE_DATA_LIST;
    }

    @Override
    public Movie getMovie(Integer movieId) {
        return MOVIE_DATA_LIST.stream()
                .filter(d -> d.id == movieId)
                .findAny()
                .map(d -> new MovieProxy(serviceLocator, d.id))
                .orElse(null);
    }

    @Override
    public Movie getMovie(Projection projection) {
        return MockProjectionRepository.getProjectionDataList().stream()
                .filter(d -> d.getId() == projection.getId())
                .findAny()
                .map(MockProjectionRepository.ProjectionData::getMovieId)
                .flatMap(projectionMovieId -> MOVIE_DATA_LIST.stream()
                        .filter(d -> d.getId() == projectionMovieId)
                        .findAny()
                        .map(d -> new MovieProxy(serviceLocator, d.id)))
                .orElse(null);
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
                .map(d -> new MovieProxy(serviceLocator, d.id))
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
