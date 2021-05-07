package com.ttbmp.cinehub.app.repository.employee.projectionist;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.MockEmployeeRepository;
import com.ttbmp.cinehub.app.repository.projection.MockProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockProjectionistRepository implements ProjectionistRepository {

    private static final List<ProjectionistData> PROJECTIONIST_DATA_LIST = new ArrayList<>();

    static {
        MockEmployeeRepository.getEmployeeDataList().stream()
                .filter(d -> Integer.parseInt(d.getUserId()) % 2 == 1)
                .forEach(d -> PROJECTIONIST_DATA_LIST.add(new ProjectionistData(d.getUserId())));
    }

    private final ServiceLocator serviceLocator;

    public MockProjectionistRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<ProjectionistData> getProjectionistDataList() {
        return PROJECTIONIST_DATA_LIST;
    }

    @Override
    public Projectionist getProjectionist(Projection projection) {
        var projectionistId = MockProjectionRepository.getProjectionDataList().stream()
                .filter(d -> d.getId() == projection.getId())
                .map(MockProjectionRepository.ProjectionData::getProjectionistId)
                .collect(Collectors.toList())
                .get(0);
        return PROJECTIONIST_DATA_LIST.stream()
                .filter(d -> d.id.equals(projectionistId))
                .map(d -> new ProjectionistProxy(
                        d.id,
                        serviceLocator.getService(UserRepository.class),
                        serviceLocator.getService(CinemaRepository.class),
                        serviceLocator.getService(ShiftRepository.class)
                ))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public Projectionist getProjectionist(ProjectionistShift projectionistShift) {
        var projectionistShiftProjectionistId = MockShiftRepository.getShiftDataList().stream()
                .filter(d -> d.getId() == projectionistShift.getId())
                .map(MockShiftRepository.ShiftData::getEmployeeId)
                .collect(Collectors.toList())
                .get(0);
        return PROJECTIONIST_DATA_LIST.stream()
                .filter(d -> d.id.equals(projectionistShiftProjectionistId))
                .map(d -> new ProjectionistProxy(
                        d.id,
                        serviceLocator.getService(UserRepository.class),
                        serviceLocator.getService(CinemaRepository.class),
                        serviceLocator.getService(ShiftRepository.class)
                ))
                .collect(Collectors.toList())
                .get(0);
    }

    public static class ProjectionistData {

        private String id;

        public ProjectionistData(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

}
