package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.employee.EmployeeRepository;
import com.ttbmp.cinehub.app.repository.employee.MockEmployeeRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.MockProjectionistShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftProxy;
import com.ttbmp.cinehub.app.repository.shift.usher.UsherShiftProxy;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockShiftRepository extends MockRepository implements ShiftRepository {

    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String START = "start";
    public static final String END = "end";
    public static final String EMPLOYEE_ID = "employeeId";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockShiftRepository.class);
    private static int shiftIdCounter = 1;

    static {
        var start = LocalTime.parse("14:30").toString();
        var end = LocalTime.parse("23:30").toString();
        for (var date = LocalDate.now().minusDays(15); date.isBefore(LocalDate.now().plusDays(46)); date = date.plusDays(1)) {
            if (!date.getDayOfWeek().equals(DayOfWeek.MONDAY) && !date.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
                var finalDate = date.toString();
                MockEmployeeRepository.getMockDataList().stream()
                        .map(m -> m.get(MockEmployeeRepository.USER_ID))
                        .filter(userId -> MockUserRepository.getMockDataList().stream()
                                .filter(m -> m.get(MockUserRepository.ID).equals(userId))
                                .findAny()
                                .map(m -> MockUserRepository.getRoleList(userId))
                                .orElse(new ArrayList<>())
                                .contains(Role.USHER_ROLE)
                        )
                        .forEach(usherId -> mockDataList.add(new HashMap<>(Map.of(
                                ID, Integer.toString(shiftIdCounter++),
                                DATE, finalDate,
                                START, start,
                                END, end,
                                EMPLOYEE_ID, usherId
                        ))));
                MockEmployeeRepository.getMockDataList().stream()
                        .map(m -> m.get(MockEmployeeRepository.USER_ID))
                        .filter(userId -> MockUserRepository.getMockDataList().stream()
                                .filter(m -> m.get(MockUserRepository.ID).equals(userId))
                                .findAny()
                                .map(m -> MockUserRepository.getRoleList(userId))
                                .orElse(new ArrayList<>())
                                .contains(Role.PROJECTIONIST_ROLE)
                        )
                        .forEach(usherId -> mockDataList.add(new HashMap<>(Map.of(
                                ID, Integer.toString(shiftIdCounter++),
                                DATE, finalDate,
                                START, start,
                                END, end,
                                EMPLOYEE_ID, usherId
                        ))));
            }
        }
    }

    public MockShiftRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

    @Override
    public Shift getShift(int shiftId) {
        return mockDataList.stream()
                .filter(m -> m.get(ID).equals(Integer.toString(shiftId)))
                .findAny()
                .map(m -> new ShiftFactory().createShift(m))
                .orElse(null);
    }

    @Override
    public Shift getShift(Employee employee, String date, String start, String end) {
        return mockDataList.stream()
                .filter(m -> m.get(EMPLOYEE_ID).equals(employee.getId()))
                .filter(m -> m.get(DATE).equals(date))
                .filter(m -> m.get(START).equals(start))
                .filter(m -> m.get(END).equals(end))
                .findAny()
                .map(d -> new ShiftFactory().createShift(d))
                .orElse(null);
    }

    @Override
    public List<Shift> getShiftList(Employee employee) {
        return mockDataList.stream()
                .filter(m -> m.get(EMPLOYEE_ID).equals(employee.getId()))
                .map(m -> new ShiftFactory().createShift(m))
                .collect(Collectors.toList());
    }

    @Override
    public List<Shift> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end) {
        return mockDataList.stream()
                .filter(m -> m.get(EMPLOYEE_ID).equals(employee.getId()))
                .filter(m -> LocalDate.parse(m.get(DATE)).isAfter(start))
                .filter(m -> LocalDate.parse(m.get(DATE)).isBefore(end))
                .map(m -> new ShiftFactory().createShift(m))
                .collect(Collectors.toList());
    }

    @Override
    public List<Shift> getCinemaShiftListBetween(Cinema cinema, LocalDate start, LocalDate end) throws RepositoryException {
        var employeeRepository = getServiceLocator().getService(EmployeeRepository.class);
        List<Shift> list = new ArrayList<>();
        for (var m : mockDataList) {
            if (employeeRepository.getEmployee(m.get(EMPLOYEE_ID)).getCinema().getId() == cinema.getId() &&
                    LocalDate.parse(m.get(DATE)).isAfter(start) &&
                    LocalDate.parse(m.get(DATE)).isBefore(end.plusDays(1))) {
                var shift = new ShiftFactory().createShift(m);
                list.add(shift);
            }
        }
        return list;
    }

    @Override
    public void deletedShift(Shift shift) {
        mockDataList.removeIf(m -> m.get(ID).equals(Integer.toString(shift.getId())));
    }

    @Override
    public synchronized void saveShift(Shift shift) {
        var shiftData = new HashMap<>(Map.of(
                ID, Integer.toString(shiftIdCounter++),
                DATE, shift.getDate(),
                START, shift.getStart(),
                END, shift.getEnd(),
                EMPLOYEE_ID, shift.getEmployee().getId()
        ));
        mockDataList.add(shiftData);
        if (shift instanceof ProjectionistShift) {
            var hallId = ((ProjectionistShift) shift).getHall().getId();
            MockProjectionistShiftRepository.getMockDataList().add(new HashMap<>(Map.of(
                    MockProjectionistShiftRepository.SHIFT_ID, shiftData.get(ID),
                    MockProjectionistShiftRepository.HALL_ID, Integer.toString(hallId)
            )));
        }
    }

    @Override
    public void modifyShift(Shift shift) throws RepositoryException {
        var shiftData = mockDataList.stream()
                .filter(m -> m.get(ID).equals(Integer.toString(shift.getId())))
                .findAny()
                .orElseThrow(() -> new RepositoryException("Shift doesn't exists."));
        shiftData.put(DATE, shift.getDate());
        shiftData.put(START, shift.getStart());
        shiftData.put(END, shift.getEnd());
        shiftData.put(EMPLOYEE_ID, shift.getEmployee().getId());
        if (shift instanceof ProjectionistShift) {
            MockProjectionistShiftRepository.getMockDataList().stream()
                    .filter(m -> m.get(MockProjectionistShiftRepository.SHIFT_ID).equals(Integer.toString(shift.getId())))
                    .findFirst()
                    .ifPresent(m -> m.put(MockProjectionistShiftRepository.HALL_ID, Integer.toString(((ProjectionistShift) shift).getHall().getId())));
        }
    }

    class ShiftFactory {
        Shift createShift(Map<String, String> shiftDataMap) {
            var roleList = MockUserRepository.getMockDataList().stream()
                    .filter(m -> m.get(MockUserRepository.ID).equals(shiftDataMap.get(EMPLOYEE_ID)))
                    .findAny()
                    .map(m -> MockUserRepository.getRoleList(m.get(MockUserRepository.ID)))
                    .orElse(new ArrayList<>());
            if (roleList.contains(Role.PROJECTIONIST_ROLE)) {
                return new ProjectionistShiftProxy(
                        getServiceLocator(),
                        Integer.parseInt(shiftDataMap.get(ID)),
                        shiftDataMap.get(DATE),
                        shiftDataMap.get(START),
                        shiftDataMap.get(END)
                );
            } else if (roleList.contains(Role.USHER_ROLE)) {
                return new UsherShiftProxy(
                        getServiceLocator(),
                        Integer.parseInt(shiftDataMap.get(ID)),
                        shiftDataMap.get(DATE),
                        shiftDataMap.get(START),
                        shiftDataMap.get(END)
                );
            } else {
                throw new IllegalStateException("Unexpected user: " + shiftDataMap.get(EMPLOYEE_ID));
            }
        }
    }

}
