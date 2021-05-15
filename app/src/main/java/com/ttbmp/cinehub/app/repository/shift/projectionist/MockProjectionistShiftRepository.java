package com.ttbmp.cinehub.app.repository.shift.projectionist;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.projectionist.MockProjectionistRepository;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockProjectionistShiftRepository implements ProjectionistShiftRepository {

    private static final List<ProjectionistShiftData> PROJECTIONIST_SHIFT_DATA_LIST = new ArrayList<>();

    static {
        var hallNumber = MockHallRepository.getHallDataList().size();
        var projectionistIdList = MockProjectionistRepository.getProjectionistDataList().stream()
                .map(MockProjectionistRepository.ProjectionistData::getId)
                .collect(Collectors.toList());
        MockShiftRepository.getShiftDataList().stream()
                .filter(d -> projectionistIdList.contains(d.getEmployeeId()))
                .forEach(d -> PROJECTIONIST_SHIFT_DATA_LIST.add(new ProjectionistShiftData(
                        d.getId(),
                        (int) (d.getId() - 3 - Math.floor((d.getId() - 3) / 14.0) + 6 * Math.floor((d.getId() - 3) / 84.0)) % hallNumber + 1
                )));
    }

    private final ServiceLocator serviceLocator;

    public MockProjectionistShiftRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<ProjectionistShiftData> getProjectionistShiftDataList() {
        return PROJECTIONIST_SHIFT_DATA_LIST;
    }

    @Override
    public ProjectionistShift getProjectionistShift(int shiftId) {
        return MockShiftRepository.getShiftDataList().stream()
                .filter(d -> d.getId() == shiftId)
                .findAny()
                .map(shiftData -> new ProjectionistShiftProxy(
                        shiftData.getId(),
                        shiftData.getDate(),
                        shiftData.getStart(),
                        shiftData.getEnd(),
                        serviceLocator.getService(ProjectionistRepository.class),
                        serviceLocator.getService(HallRepository.class),
                        serviceLocator.getService(ProjectionRepository.class)
                ))
                .orElse(null);
    }

    @Override
    public void saveShift(ProjectionistShift shift) {
        PROJECTIONIST_SHIFT_DATA_LIST.add(new ProjectionistShiftData(shift.getId(), shift.getHall().getId()));
    }

    @Override
    public void modifyShift(ProjectionistShift shift) throws RepositoryException {
        var data = PROJECTIONIST_SHIFT_DATA_LIST.stream()
                .filter(d -> d.shiftId == shift.getId())
                .findAny()
                .orElseThrow(() -> new RepositoryException("Shift does not exists."));
        data.shiftId = shift.getId();
        data.hallId = shift.getHall().getId();
    }

    public static class ProjectionistShiftData {

        private int shiftId;
        private int hallId;

        public ProjectionistShiftData(int shiftId, int hallId) {
            this.shiftId = shiftId;
            this.hallId = hallId;
        }

        public int getShiftId() {
            return shiftId;
        }

        public void setShiftId(int shiftId) {
            this.shiftId = shiftId;
        }

        public int getHallId() {
            return hallId;
        }

        public void setHallId(int hallId) {
            this.hallId = hallId;
        }

    }

}
