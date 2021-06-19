package com.ttbmp.cinehub.app.repository.hall;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.cinema.MockCinemaRepository;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.MockProjectionistShiftRepository;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockHallRepository extends MockRepository implements HallRepository {

    public static final String ID = "id";
    public static final String CINEMA_ID = "cinemaId";
    public static final String NAME = "name";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockHallRepository.class);
    private static int hallIdCounter = 1;

    static {
        var cinemaNumber = MockCinemaRepository.getMockDataList().size();
        for (var cinemaId = 1; cinemaId < cinemaNumber + 1; cinemaId++) {
            for (var i = 1; i <= cinemaId; i++) {
                mockDataList.add(new HashMap<>(Map.of(ID, Integer.toString(hallIdCounter++), CINEMA_ID, Integer.toString(cinemaId), NAME, "A" + i)));
                mockDataList.add(new HashMap<>(Map.of(ID, Integer.toString(hallIdCounter++), CINEMA_ID, Integer.toString(cinemaId), NAME, "B" + i)));
            }
        }
    }

    public MockHallRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

    @Override
    public Hall getHall(int hallId) {
        return mockDataList.stream()
                .filter(m -> m.get(ID).equals(Integer.toString(hallId)))
                .findAny()
                .map(m -> new HallProxy(getServiceLocator(), Integer.parseInt(m.get(ID)), m.get(NAME)))
                .orElse(null);
    }

    @Override
    public Hall getHall(Projection projection) {
        return MockProjectionRepository.getMockDataList().stream()
                .filter(m -> m.get(MockProjectionRepository.ID).equals(Integer.toString(projection.getId())))
                .findAny()
                .map(m -> m.get(MockProjectionRepository.HALL_ID))
                .flatMap(projectionHallId -> mockDataList.stream()
                        .filter(m -> m.get(ID).equals(projectionHallId))
                        .findAny()
                        .map(m -> new HallProxy(getServiceLocator(), Integer.parseInt(m.get(ID)), m.get(NAME)))
                )
                .orElse(null);
    }

    @Override
    public Hall getHall(ProjectionistShift projectionistShift) {
        return MockProjectionistShiftRepository.getMockDataList().stream()
                .filter(m -> m.get(MockProjectionistShiftRepository.SHIFT_ID).equals(Integer.toString(projectionistShift.getId())))
                .findAny()
                .map(m -> m.get(MockProjectionistShiftRepository.HALL_ID))
                .flatMap(projectionistShiftHallId -> mockDataList.stream()
                        .filter(m -> m.get(ID).equals(projectionistShiftHallId))
                        .findAny()
                        .map(m -> new HallProxy(getServiceLocator(), Integer.parseInt(m.get(ID)), m.get(NAME)))
                )
                .orElse(null);
    }

    @Override
    public List<Hall> getHallList(Cinema cinema) {
        return mockDataList.stream()
                .filter(m -> m.get(CINEMA_ID).equals(Integer.toString(cinema.getId())))
                .map(m -> new HallProxy(getServiceLocator(), Integer.parseInt(m.get(ID)), m.get(NAME)))
                .collect(Collectors.toList());
    }

}
