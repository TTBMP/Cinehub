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
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MockHallRepository implements HallRepository {

    private static final List<HallData> HALL_DATA_LIST = new ArrayList<>();

    static {
        HALL_DATA_LIST.add(new HallData(0, 0));
        HALL_DATA_LIST.add(new HallData(1, 0));
        HALL_DATA_LIST.add(new HallData(2, 0));
        HALL_DATA_LIST.add(new HallData(3, 0));
        HALL_DATA_LIST.add(new HallData(4, 1));
        HALL_DATA_LIST.add(new HallData(5, 1));
        HALL_DATA_LIST.add(new HallData(6, 1));
        HALL_DATA_LIST.add(new HallData(7, 1));
        HALL_DATA_LIST.add(new HallData(8, 1));
        HALL_DATA_LIST.add(new HallData(9, 1));
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
                .filter(d -> d.cinemaId == cinema.getId())
                .map(d -> new HallProxy(d.id, serviceLocator.getService(SeatRepository.class)))
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
                .map(d -> new HallProxy(d.id, serviceLocator.getService(SeatRepository.class)))
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
                .map(d -> new HallProxy(d.id, serviceLocator.getService(SeatRepository.class)))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public Hall getHall(int hallId) {
        return HALL_DATA_LIST.stream()
                .filter(d -> d.id == hallId)
                .map(d -> new HallProxy(d.id, serviceLocator.getService(SeatRepository.class)))
                .collect(Collectors.toList())
                .get(0);
    }

    public static class HallData {

        private int id;
        private int cinemaId;

        public HallData(int id, int cinemaId) {
            this.id = id;
            this.cinemaId = cinemaId;
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
