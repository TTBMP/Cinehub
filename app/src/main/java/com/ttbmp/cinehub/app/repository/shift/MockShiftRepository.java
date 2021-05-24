package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.employee.MockEmployeeRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.MockProjectionistShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftProxy;
import com.ttbmp.cinehub.app.repository.shift.usher.UsherShiftProxy;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.security.Role;
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
    private static int shiftIdCounter = 1;

    static {
        var start = LocalTime.parse("14:30").toString();
        var end = LocalTime.parse("23:30").toString();
        for (var date = LocalDate.now().minusDays(15); date.isBefore(LocalDate.now().plusDays(46)); date = date.plusDays(1)) {
            if (!date.getDayOfWeek().equals(DayOfWeek.MONDAY) && !date.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
                var finalDate = date.toString();
                MockEmployeeRepository.getEmployeeDataList().stream()
                        .map(MockEmployeeRepository.EmployeeData::getUserId)
                        .filter(userId -> MockUserRepository.getUserDataList().stream()
                                .filter(d -> d.getId().equals(userId))
                                .findAny()
                                .map(MockUserRepository.UserData::getRoleList)
                                .orElse(new ArrayList<>())
                                .contains(Role.USHER_ROLE)
                        )
                        .forEach(usherId -> SHIFT_DATA_LIST.add(new ShiftData(shiftIdCounter++, finalDate, start, end, usherId)));
                MockEmployeeRepository.getEmployeeDataList().stream()
                        .map(MockEmployeeRepository.EmployeeData::getUserId)
                        .filter(userId -> MockUserRepository.getUserDataList().stream()
                                .filter(d -> d.getId().equals(userId))
                                .findAny()
                                .map(MockUserRepository.UserData::getRoleList)
                                .orElse(new ArrayList<>())
                                .contains(Role.PROJECTIONIST_ROLE)
                        )
                        .forEach(usherId -> SHIFT_DATA_LIST.add(new ShiftData(shiftIdCounter++, finalDate, start, end, usherId)));
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
    public Shift getShift(int shiftId) {
        return SHIFT_DATA_LIST.stream()
                .filter(d -> d.id == shiftId)
                .findAny()
                .map(d -> new ShiftFactory().createShift(d))
                .orElse(null);
    }

    @Override
    public Shift getShift(Employee employee, String date, String start, String end) {
        return SHIFT_DATA_LIST.stream()
                .filter(d -> d.employeeId.equals(employee.getId()))
                .filter(d -> d.date.equals(date))
                .filter(d -> d.start.equals(start))
                .filter(d -> d.end.equals(end))
                .findAny()
                .map(d -> new ShiftFactory().createShift(d))
                .orElse(null);
    }

    @Override
    public List<Shift> getShiftList(Employee employee) {
        return SHIFT_DATA_LIST.stream()
                .filter(d -> d.employeeId.equals(employee.getId()))
                .map(d -> new ShiftFactory().createShift(d))
                .collect(Collectors.toList());
    }

    @Override
    public List<Shift> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end) {
        return SHIFT_DATA_LIST.stream()
                .filter(d -> d.employeeId.equals(employee.getId()))
                .filter(d -> LocalDate.parse(d.date).isAfter(start))
                .filter(d -> LocalDate.parse(d.date).isBefore(end))
                .map(d -> new ShiftFactory().createShift(d))
                .collect(Collectors.toList());
    }

    @Override
    public List<Shift> getCinemaShiftListBetween(int cinemaId, LocalDate start, LocalDate end) throws RepositoryException {
        var employeeRepository = serviceLocator.getService(EmployeeRepository.class);
        List<Shift> list = new ArrayList<>();
        for (var d : SHIFT_DATA_LIST) {
            if (employeeRepository.getEmployee(d.employeeId).getCinema().getId() == cinemaId &&
                    LocalDate.parse(d.date).isAfter(start) &&
                    LocalDate.parse(d.date).isBefore(end.plusDays(1))) {
                var shift = new ShiftFactory().createShift(d);
                list.add(shift);
            }
        }
        return list;
    }

    @Override
    public void deletedShift(Shift shift) {
        SHIFT_DATA_LIST.removeIf(d -> d.id == shift.getId());
    }

    @Override
    public synchronized void saveShift(Shift shift) {
        var data = new ShiftData(
                shiftIdCounter++,
                shift.getDate(),
                shift.getStart(),
                shift.getEnd(),
                shift.getEmployee().getId()
        );
        SHIFT_DATA_LIST.add(data);
        if (shift instanceof ProjectionistShift) {
            var hall = ((ProjectionistShift) shift).getHall().getId();
            MockProjectionistShiftRepository.getProjectionistShiftDataList().add(
                    new MockProjectionistShiftRepository.ProjectionistShiftData(data.getId(), hall)
            );
        }
    }

    @Override
    public void modifyShift(Shift shift) throws RepositoryException {
        var shiftData = SHIFT_DATA_LIST.stream()
                .filter(d -> d.id == shift.getId())
                .findAny()
                .orElseThrow(() -> new RepositoryException("Shift doesn't exists."));
        shiftData.date = shift.getDate();
        shiftData.start = shift.getStart();
        shiftData.end = shift.getEnd();
        shiftData.employeeId = shift.getEmployee().getId();
        if (shift instanceof ProjectionistShift) {
            MockProjectionistShiftRepository.getProjectionistShiftDataList().stream()
                    .filter(projectionistShiftData -> projectionistShiftData.getShiftId() == shift.getId())
                    .findFirst()
                    .ifPresent(projectionistShiftData -> projectionistShiftData.setHallId(((ProjectionistShift) shift).getHall().getId()));
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
            var roleList = MockUserRepository.getUserDataList().stream()
                    .filter(d -> d.getId().equals(shiftData.employeeId))
                    .findAny()
                    .map(MockUserRepository.UserData::getRoleList)
                    .orElse(new ArrayList<>());
            if (roleList.contains(Role.PROJECTIONIST_ROLE)) {
                return new ProjectionistShiftProxy(serviceLocator, shiftData.id, shiftData.date, shiftData.start, shiftData.end);
            } else if (roleList.contains(Role.USHER_ROLE)) {
                return new UsherShiftProxy(serviceLocator, shiftData.id, shiftData.date, shiftData.start, shiftData.end);
            } else {
                throw new IllegalStateException("Unexpected user: " + shiftData.employeeId);
            }
        }
    }

}
