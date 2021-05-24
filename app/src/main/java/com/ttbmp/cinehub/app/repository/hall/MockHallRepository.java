package com.ttbmp.cinehub.app.repository.hall;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.cinema.MockCinemaRepository;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.MockProjectionistShiftRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockHallRepository implements HallRepository {

    private static final List<HallData> HALL_DATA_LIST = new ArrayList<>();

    private static int hallIdCounter = 1;

    static {
        var cinemaNumber = MockCinemaRepository.getCinemaDataList().size();
        for (var cinemaId = 1; cinemaId < cinemaNumber + 1; cinemaId++) {
            for (var i = 1; i <= cinemaId; i++) {
                HALL_DATA_LIST.add(new HallData(hallIdCounter++, cinemaId, "A" + i));
                HALL_DATA_LIST.add(new HallData(hallIdCounter++, cinemaId, "B" + i));
            }
        }
    }

    private final ServiceLocator serviceLocator;

    public MockHallRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<HallData> getHallDataList() {
        return HALL_DATA_LIST;
    }

    @Override
    public Hall getHall(int hallId) {
        return HALL_DATA_LIST.stream()
                .filter(d -> d.id == hallId)
                .findAny()
                .map(d -> new HallProxy(serviceLocator, d.id, d.name))
                .orElse(null);
    }

    @Override
    public Hall getHall(Projection projection) {
        return MockProjectionRepository.getProjectionDataList().stream()
                .filter(d -> d.getId() == projection.getId())
                .findAny()
                .map(MockProjectionRepository.ProjectionData::getHallId)
                .flatMap(projectionHallId -> HALL_DATA_LIST.stream()
                        .filter(d -> d.id == projectionHallId)
                        .findAny()
                        .map(d -> new HallProxy(serviceLocator, d.id, d.name)))
                .orElse(null);
    }

    @Override
    public Hall getHall(ProjectionistShift projectionistShift) {
        return MockProjectionistShiftRepository.getProjectionistShiftDataList().stream()
                .filter(d -> d.getShiftId() == projectionistShift.getId())
                .findAny()
                .map(MockProjectionistShiftRepository.ProjectionistShiftData::getHallId)
                .flatMap(projectionistShiftHallId -> HALL_DATA_LIST.stream()
                        .filter(d -> d.id == projectionistShiftHallId)
                        .findAny()
                        .map(d -> new HallProxy(serviceLocator, d.id, d.name)
                        ))
                .orElse(null);
    }

    @Override
    public List<Hall> getHallList(Cinema cinema) {
        return HALL_DATA_LIST.stream()
                .filter(d -> d.getCinemaId() == cinema.getId())
                .map(d -> new HallProxy(serviceLocator, d.id, d.name))
                .collect(Collectors.toList());
    }

    public static class HallData {

        private int id;
        private int cinemaId;
        private String name;

        public HallData(int id, int cinemaId, String name) {
            this.id = id;
            this.cinemaId = cinemaId;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(int cinemaId) {
            this.cinemaId = cinemaId;
        }

    }

}
