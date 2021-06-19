package com.ttbmp.cinehub.app.repository.cinema;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.employee.MockEmployeeRepository;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockCinemaRepository extends MockRepository implements CinemaRepository {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CITY = "city";
    public static final String ADDRESS = "address";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockCinemaRepository.class);

    static {
        mockDataList.addAll(new ArrayList<>(List.of(
                new HashMap<>(Map.of(ID, "1", NAME, "Comunale", CITY, "Recanati", ADDRESS, "via recanati 3")),
                new HashMap<>(Map.of(ID, "2", NAME, "MultiPlex", CITY, "Teramo", ADDRESS, "via garibaldi 1"))
        )));
    }

    public MockCinemaRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

    @Override
    public Cinema getCinema(int cinemaId) {
        return mockDataList.stream()
                .filter(m -> m.get(ID).equals(Integer.toString(cinemaId)))
                .findAny()
                .map(m -> new CinemaProxy(
                        getServiceLocator(),
                        Integer.parseInt(m.get(ID)),
                        m.get(NAME),
                        m.get(CITY),
                        m.get(ADDRESS)
                ))
                .orElse(null);
    }

    @Override
    public Cinema getCinema(Employee employee) {
        return MockEmployeeRepository.getMockDataList().stream()
                .filter(m -> m.get(MockEmployeeRepository.USER_ID).equals(employee.getId()))
                .findAny()
                .map(m -> m.get(MockEmployeeRepository.CINEMA_ID))
                .flatMap(cinemaEmployeeId -> mockDataList.stream()
                        .filter(m -> m.get(ID).equals(cinemaEmployeeId))
                        .findAny()
                        .map(m -> new CinemaProxy(
                                getServiceLocator(),
                                Integer.parseInt(m.get(ID)),
                                m.get(NAME),
                                m.get(CITY),
                                m.get(ADDRESS)
                        ))
                )
                .orElse(null);
    }

    @Override
    public Cinema getCinema(Projection projection) {
        return MockProjectionRepository.getMockDataList().stream()
                .filter(m -> m.get(MockProjectionRepository.ID).equals(Integer.toString(projection.getId())))
                .findAny()
                .map(m -> m.get(MockProjectionRepository.HALL_ID))
                .flatMap(projectionHallId -> MockHallRepository.getMockDataList().stream()
                        .filter(m -> m.get(MockHallRepository.ID).equals(projectionHallId))
                        .findAny()
                        .map(m -> m.get(MockHallRepository.CINEMA_ID))
                        .flatMap(projectionCinemaId -> mockDataList.stream()
                                .filter(m -> m.get(ID).equals(projectionCinemaId))
                                .findAny()
                                .map(m -> new CinemaProxy(
                                        getServiceLocator(),
                                        Integer.parseInt(m.get(ID)),
                                        m.get(NAME),
                                        m.get(CITY),
                                        m.get(ADDRESS)
                                ))
                        )
                )
                .orElse(null);
    }

    @Override
    public List<Cinema> getAllCinema() {
        return mockDataList.stream()
                .map(m -> new CinemaProxy(
                        getServiceLocator(),
                        Integer.parseInt(m.get(ID)),
                        m.get(NAME),
                        m.get(CITY),
                        m.get(ADDRESS)
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cinema> getListCinema(Movie movie, String date) {
        var projectionHallIdList = MockProjectionRepository.getMockDataList().stream()
                .filter(m -> m.get(MockProjectionRepository.MOVIE_ID).equals(Integer.toString(movie.getId()))
                        && m.get(MockProjectionRepository.DATE).equals(date))
                .map(m -> m.get(MockProjectionRepository.HALL_ID))
                .distinct()
                .collect(Collectors.toList());
        var hallCinemaIdList = MockHallRepository.getMockDataList().stream()
                .filter(m -> projectionHallIdList.contains(m.get(MockHallRepository.ID)))
                .map(m -> m.get(MockHallRepository.CINEMA_ID))
                .distinct()
                .collect(Collectors.toList());
        return mockDataList.stream()
                .filter(m -> hallCinemaIdList.contains(m.get(ID)))
                .map(m -> new CinemaProxy(
                        getServiceLocator(),
                        Integer.parseInt(m.get(ID)),
                        m.get(NAME),
                        m.get(CITY),
                        m.get(ADDRESS)
                ))
                .collect(Collectors.toList());
    }

}
