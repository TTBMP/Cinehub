package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.employee.MockEmployeeRepository;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.MockProjectionistShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftProxy;
import com.ttbmp.cinehub.app.repository.shift.usher.UsherShiftProxy;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.employee.Usher;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockShiftRepository implements ShiftRepository {

    private static final List<ShiftData> SHIFT_DATA_LIST = new ArrayList<>();
    private static int shiftIdCounter = 0;

    static {
        for (MockEmployeeRepository.EmployeeData employeeData : MockEmployeeRepository.getEmployeeDataList()) {
            for (LocalDate date = LocalDate.now().minusMonths(1); date.isBefore(LocalDate.now().plusMonths(1)); date = date.plusDays(1)) {
                if (date.getDayOfWeek() != DayOfWeek.MONDAY && date.getDayOfWeek() != DayOfWeek.TUESDAY) {
                    SHIFT_DATA_LIST.add(new ShiftData(
                            shiftIdCounter++,
                            date.toString(),
                            LocalTime.parse("14:00").toString(),
                            LocalTime.parse("18:00").toString(),
                            employeeData.getUserId()
                    ));
                }
            }
        }
    }

    private final ServiceLocator serviceLocator;

    public MockShiftRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<ShiftData> getShiftDataList() {
        return SHIFT_DATA_LIST;
    }

    @Override
    public List<Shift> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end) {
        return SHIFT_DATA_LIST.stream()
                .filter(d -> d.employeeId.equals(employee.getId()))
                .filter(d -> LocalDate.parse(d.date).isAfter(start) && LocalDate.parse(d.date).isBefore(end))
                .map(d -> new ShiftFactory().createShift(d))
                .collect(Collectors.toList());
    }

    @Override
    public Shift getShift(int shiftId) {
        ShiftData data = SHIFT_DATA_LIST.stream()
                .filter(d -> d.id == shiftId)
                .collect(Collectors.toList())
                .get(0);
        Employee employee = serviceLocator.getService(EmployeeRepository.class).getEmployee(data.employeeId);
        if (employee instanceof Projectionist) {
            return new ProjectionistShiftProxy(
                    data.id,
                    data.date,
                    data.start,
                    data.end,
                    serviceLocator.getService(ProjectionistRepository.class),
                    serviceLocator.getService(HallRepository.class),
                    serviceLocator.getService(ProjectionRepository.class)
            );
        }
        if (employee instanceof Usher) {
            return new UsherShiftProxy(
                    data.id,
                    data.date,
                    data.start,
                    data.end,
                    serviceLocator.getService(UsherRepository.class)
            );
        }
        return null;
    }

    @Override
    public List<Shift> getShiftList() {
        return SHIFT_DATA_LIST.stream()
                .map(d -> new ShiftFactory().createShift(d))
                .collect(Collectors.toList());
    }

    @Override
    public List<Shift> getShiftList(Employee employee) {
        List<ShiftData> employeeShiftData = SHIFT_DATA_LIST.stream()
                .filter(d -> d.employeeId.equals(employee.getId()))
                .collect(Collectors.toList());
        if (employee instanceof Projectionist) {
            return SHIFT_DATA_LIST.stream()
                    .filter(employeeShiftData::contains)
                    .map(d -> new ProjectionistShiftProxy(
                                    d.id,
                                    d.date,
                                    d.start,
                                    d.end,
                                    serviceLocator.getService(ProjectionistRepository.class),
                                    serviceLocator.getService(HallRepository.class),
                                    serviceLocator.getService(ProjectionRepository.class)
                            )
                    )
                    .collect(Collectors.toList());
        }
        if (employee instanceof Usher) {
            return SHIFT_DATA_LIST.stream()
                    .filter(employeeShiftData::contains)
                    .map(d -> new UsherShiftProxy(
                                    d.id,
                                    d.date,
                                    d.start,
                                    d.end,
                                    serviceLocator.getService(UsherRepository.class)
                            )
                    )
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void deletedShift(Shift shift) throws ShiftSaveException {
        if (!SHIFT_DATA_LIST.removeIf(d -> d.id == shift.getId())) {
            throw new ShiftSaveException(ShiftSaveException.NOT_EXIST_ERROR);
        }
    }

    @Override
    public synchronized void saveShift(Shift shift) {
        SHIFT_DATA_LIST.add(new ShiftData(
                shiftIdCounter,
                shift.getDate(),
                shift.getStart(),
                shift.getEnd(),
                shift.getEmployee().getId()
        ));
        if (shift instanceof ProjectionistShift) {
            MockProjectionistShiftRepository.getProjectionistShiftDataList()
                    .add(new MockProjectionistShiftRepository.ProjectionistShiftData(
                            shiftIdCounter,
                            ((ProjectionistShift) shift).getHall().getId()
                    ));
        }
        shiftIdCounter++;
    }

    @Override
    public void modifyShift(Shift shift) throws ShiftSaveException {
        if (SHIFT_DATA_LIST.stream().noneMatch(d -> d.id == shift.getId())) {
            throw new ShiftSaveException(ShiftSaveException.NOT_EXIST_ERROR);
        }
        ShiftData data = SHIFT_DATA_LIST.stream()
                .filter(d -> d.id == shift.getId())
                .collect(Collectors.toList())
                .get(0);
        data.date = shift.getDate();
        data.start = shift.getStart();
        data.end = shift.getEnd();
        data.employeeId = shift.getEmployee().getId();
        if (shift instanceof ProjectionistShift) {
            MockProjectionistShiftRepository.ProjectionistShiftData projectionistShiftData;
            projectionistShiftData = MockProjectionistShiftRepository.getProjectionistShiftDataList().stream()
                    .filter(d -> d.getShiftId() == shift.getId())
                    .collect(Collectors.toList())
                    .get(0);
            projectionistShiftData.setHallId(((ProjectionistShift) shift).getHall().getId());
        }
    }

    public static class ShiftData {

        private int id;
        private String date;
        private String start;
        private String end;
        private String employeeId;

        public ShiftData(int id, String date, String start, String end, String employeeId) {
            this.id = id;
            this.date = date;
            this.start = start;
            this.end = end;
            this.employeeId = employeeId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

    }

    class ShiftFactory {
        Shift createShift(ShiftData shiftData) {
            MockEmployeeRepository.EmployeeData.Role employeeRole = MockEmployeeRepository.getEmployeeDataList().stream()
                    .filter(d -> d.getUserId().equals(shiftData.employeeId))
                    .map(MockEmployeeRepository.EmployeeData::getRole)
                    .collect(Collectors.toList())
                    .get(0);
            switch (employeeRole) {
                case PROJECTIONIST:
                    return new ProjectionistShiftProxy(
                            shiftData.id,
                            shiftData.date,
                            shiftData.start,
                            shiftData.end,
                            serviceLocator.getService(ProjectionistRepository.class),
                            serviceLocator.getService(HallRepository.class),
                            serviceLocator.getService(ProjectionRepository.class)
                    );
                case USHER:
                    return new UsherShiftProxy(
                            shiftData.id,
                            shiftData.date,
                            shiftData.start,
                            shiftData.end,
                            serviceLocator.getService(UsherRepository.class)
                    );
                default:
                    throw new IllegalStateException("Unexpected value: " + employeeRole);
            }
        }
    }

}
