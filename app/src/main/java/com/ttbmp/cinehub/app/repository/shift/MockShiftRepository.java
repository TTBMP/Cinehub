package com.ttbmp.cinehub.app.repository.shift;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.employee.MockEmployeeRepository;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.employee.usher.UsherRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.shift.projectionist.ProjectionistShiftProxy;
import com.ttbmp.cinehub.app.repository.shift.usher.UsherShiftProxy;
import com.ttbmp.cinehub.domain.employee.Employee;
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
    public List<Shift> getShiftList() {
        return SHIFT_DATA_LIST.stream()
                .map(d -> new ShiftFactory().createShift(d))
                .collect(Collectors.toList());
    }

    @Override
    public List<Shift> getShiftList(Employee employee) {
        // TODO
        return null;
    }

    @Override
    public void deletedShift(Shift shift) throws ShiftSaveException {
        if (!SHIFT_DATA_LIST.removeIf(d -> d.id == shift.getId())) {
            throw new ShiftSaveException(ShiftSaveException.NOT_EXIST_ERROR);
        }
    }

    @Override
    public synchronized void saveShift(Shift shift) throws ShiftSaveException {
        SHIFT_DATA_LIST.add(new ShiftData(
                shiftIdCounter++,
                shift.getDate(),
                shift.getStart(),
                shift.getEnd(),
                shift.getEmployee().getId()
        ));
    }

    @Override
    public void modifyShift(Shift oldShift, Shift newShift) throws ShiftSaveException {
        if (oldShift.equals(newShift)) {
            throw new ShiftSaveException(ShiftSaveException.ALREADY_EXIST_ERROR);
        }
        SHIFT_DATA_LIST.removeIf(shiftData -> shiftData.id == oldShift.getId());
        for (ShiftData shiftData : SHIFT_DATA_LIST) {
            if (shiftData.date.equals(newShift.getDate())
                    && LocalTime.parse(shiftData.start).isBefore(LocalTime.parse(newShift.getEnd()))
                    && LocalTime.parse(shiftData.end).isAfter(LocalTime.parse(newShift.getStart()))
                    && shiftData.employeeId.equals(newShift.getEmployee().getId())) {
                SHIFT_DATA_LIST.add(new ShiftData(
                        oldShift.getId(),
                        oldShift.getDate(),
                        oldShift.getStart(),
                        oldShift.getEnd(),
                        oldShift.getEmployee().getId())
                );
                throw new ShiftSaveException(ShiftSaveException.ALREADY_EXIST_ERROR);
            }
        }
        SHIFT_DATA_LIST.add(new ShiftData(
                newShift.getId(),
                newShift.getDate(),
                newShift.getStart(),
                newShift.getEnd(),
                newShift.getEmployee().getId()
        ));
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
