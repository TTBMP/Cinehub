package com.ttbmp.cinehub.app.repository.movie;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Fabio Buracchi
 */
public class MockMovieRepository extends MockRepository implements MovieRepository {

    public static final String ID = "id";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockMovieRepository.class);

    static {
        IntStream.range(15, 23).forEach(i -> mockDataList.add(new HashMap<>(Map.of(ID, Integer.toString(i)))));
    }

    public MockMovieRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

    @Override
    public Movie getMovie(Integer movieId) {
        return mockDataList.stream()
                .filter(m -> m.get(ID).equals(Integer.toString(movieId)))
                .findAny()
                .map(m -> new MovieProxy(getServiceLocator(), Integer.parseInt(m.get(ID))))
                .orElse(null);
    }

    @Override
    public Movie getMovie(Projection projection) {
        return MockProjectionRepository.getMockDataList().stream()
                .filter(m -> m.get(MockProjectionRepository.ID).equals(Integer.toString(projection.getId())))
                .findAny()
                .map(m -> m.get(MockProjectionRepository.MOVIE_ID))
                .flatMap(projectionMovieId -> mockDataList.stream()
                        .filter(m -> m.get(ID).equals(projectionMovieId))
                        .findAny()
                        .map(m -> new MovieProxy(getServiceLocator(), Integer.parseInt(m.get(ID))))
                )
                .orElse(null);
    }

    @Override
    public List<Movie> getAllMovie() throws RepositoryException {
        return mockDataList.stream()
                .map(m -> new MovieProxy(getServiceLocator(), Integer.parseInt(m.get(ID))))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMovieList(String localDate) {
        var projectionMovieIdList = MockProjectionRepository.getMockDataList().stream()
                .filter(m -> m.get(MockProjectionRepository.DATE).equals(localDate))
                .map(m -> m.get(MockProjectionRepository.MOVIE_ID))
                .distinct()
                .collect(Collectors.toList());
        return mockDataList.stream()
                .filter(m -> projectionMovieIdList.contains(m.get(ID)))
                .map(m -> new MovieProxy(getServiceLocator(), Integer.parseInt(m.get(ID))))
                .collect(Collectors.toList());
    }

}
