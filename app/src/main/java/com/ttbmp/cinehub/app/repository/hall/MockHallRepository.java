package com.ttbmp.cinehub.app.repository.hall;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
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

    static {
        HALL_DATA_LIST.add(new HallData(0, 0, "A1"));
        HALL_DATA_LIST.add(new HallData(1, 0, "B1"));
        HALL_DATA_LIST.add(new HallData(2, 0, "C1"));
        HALL_DATA_LIST.add(new HallData(3, 0, "A2"));
        HALL_DATA_LIST.add(new HallData(4, 0, "B2"));
        HALL_DATA_LIST.add(new HallData(5, 0, "C2"));
        HALL_DATA_LIST.add(new HallData(6, 1, "A1"));
        HALL_DATA_LIST.add(new HallData(7, 1, "B1"));
        HALL_DATA_LIST.add(new HallData(8, 1, "C1"));
        HALL_DATA_LIST.add(new HallData(9, 1, "A2"));
        HALL_DATA_LIST.add(new HallData(10, 1, "B2"));
        HALL_DATA_LIST.add(new HallData(11, 1, "C2"));
    }

    private final ServiceLocator serviceLocator;

    public MockHallRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<HallData> getHallDataList() {
        return HALL_DATA_LIST;
    }

    @Override
    public List<Hall> getHallList(Cinema cinema) {
        return HALL_DATA_LIST.stream()
                .filter(d -> d.getCinemaId() == cinema.getId())
                .map(d -> new HallProxy(d.id, serviceLocator.getService(SeatRepository.class), d.name))
                .collect(Collectors.toList());
    }

    @Override
    public Hall getHall(Projection projection) {
        int projectionHallId = MockProjectionRepository.getProjectionDataList().stream()
                .filter(d -> d.getId() == projection.getId())
                .map(MockProjectionRepository.ProjectionData::getHallId)
                .collect(Collectors.toList())
                .get(0);
        return HALL_DATA_LIST.stream()
                .filter(d -> d.id == projectionHallId)
                .map(d -> new HallProxy(d.id, serviceLocator.getService(SeatRepository.class), d.name))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public Hall getHall(ProjectionistShift projectionistShift) {
        int projectionistShiftHallId = MockProjectionistShiftRepository.getProjectionistShiftDataList().stream()
                .filter(d -> d.getShiftId() == projectionistShift.getId())
                .map(MockProjectionistShiftRepository.ProjectionistShiftData::getHallId)
                .collect(Collectors.toList())
                .get(0);
        return HALL_DATA_LIST.stream()
                .filter(d -> d.id == projectionistShiftHallId)
                .map(d -> new HallProxy(d.id, serviceLocator.getService(SeatRepository.class), d.name))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public Hall getHall(int hallId) {
        return HALL_DATA_LIST.stream()
                .filter(d -> d.id == hallId)
                .map(d -> new HallProxy(d.id, serviceLocator.getService(SeatRepository.class), d.name))
                .collect(Collectors.toList())
                .get(0);
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
