package com.ttbmp.cinehub.app.repository.cinema;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.employee.MockEmployeeRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockCinemaRepository implements CinemaRepository {

    private static final List<CinemaData> CINEMA_DATA_LIST = new ArrayList<>();

    static {
        CINEMA_DATA_LIST.add(new CinemaData(0, "Comunale", "Recanati", "via recanati 3"));
        CINEMA_DATA_LIST.add(new CinemaData(1, "MultiPlex", "Teramo", "via garibaldi 1"));
    }

    private final ServiceLocator serviceLocator;

    public MockCinemaRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<CinemaData> getCinemaDataList() {
        return CINEMA_DATA_LIST;
    }

    @Override
    public Cinema getCinema(int cinemaId) {
        return CINEMA_DATA_LIST.stream()
                .filter(d -> d.id == cinemaId)
                .map(d -> new CinemaProxy(
                        d.id,
                        d.name,
                        d.city,
                        d.address,
                        serviceLocator.getService(HallRepository.class)
                ))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public Cinema getCinema(Employee employee) {
        int cinemaEmployeeId = MockEmployeeRepository.getEmployeeDataList().stream()
                .filter(d -> d.getUserId().equals(employee.getId()))
                .map(MockEmployeeRepository.EmployeeData::getCinemaId)
                .collect(Collectors.toList())
                .get(0);
        return CINEMA_DATA_LIST.stream()
                .filter(d -> d.id == cinemaEmployeeId)
                .map(d -> new CinemaProxy(
                        d.id,
                        d.name,
                        d.city,
                        d.address,
                        serviceLocator.getService(HallRepository.class)
                ))
                .collect(Collectors.toList())
                .get(0);
    }


    @Override
    public Cinema getCinema(Projection projection) {
        int projectionHallId = MockProjectionRepository.getProjectionDataList().stream()
                .filter(d -> d.getId() == projection.getId())
                .map(MockProjectionRepository.ProjectionData::getHallId)
                .collect(Collectors.toList())
                .get(0);
        int projectionCinemaId = MockHallRepository.getHallDataList().stream()
                .filter(d -> d.getId() == projectionHallId)
                .map(MockHallRepository.HallData::getCinemaId)
                .collect(Collectors.toList())
                .get(0);
        return CINEMA_DATA_LIST.stream()
                .filter(d -> d.id == projectionCinemaId)
                .map(d -> new CinemaProxy(
                        d.id,
                        d.name,
                        d.city,
                        d.address,
                        serviceLocator.getService(HallRepository.class)
                ))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public List<Cinema> getAllCinema() {
        return CINEMA_DATA_LIST.stream()
                .map(d -> new CinemaProxy(
                        d.id,
                        d.name,
                        d.city,
                        d.address,
                        serviceLocator.getService(HallRepository.class)
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cinema> getListCinema(Movie movie, String date) {
        var projectionHallIdList = MockProjectionRepository.getProjectionDataList().stream()
                .filter(d -> d.getMovieId() == movie.getId() && d.getDate().equals(date))
                .map(MockProjectionRepository.ProjectionData::getHallId)
                .distinct()
                .collect(Collectors.toList());
        var hallCinemaIdList = MockHallRepository.getHallDataList().stream()
                .filter(d -> projectionHallIdList.contains(d.getCinemaId()))
                .map(MockHallRepository.HallData::getCinemaId)
                .distinct()
                .collect(Collectors.toList());
        return CINEMA_DATA_LIST.stream()
                .filter(d -> hallCinemaIdList.contains(d.id))
                .map(d -> new CinemaProxy(
                        d.id,
                        d.name,
                        d.city,
                        d.address,
                        serviceLocator.getService(HallRepository.class)
                ))
                .collect(Collectors.toList());
    }

    public static class CinemaData {

        private int id;
        private String name;
        private String city;
        private String address;

        public CinemaData(int id, String name, String city, String address) {
            this.id = id;
            this.name = name;
            this.city = city;
            this.address = address;
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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

    }

}
