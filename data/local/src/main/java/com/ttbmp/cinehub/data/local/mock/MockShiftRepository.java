package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.ShiftSaveException;
import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.shift.ProjectionistShift;
import com.ttbmp.cinehub.core.entity.shift.Shift;
import com.ttbmp.cinehub.core.entity.shift.UsherShift;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.utilities.result.Result;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public class MockShiftRepository implements ShiftRepository {

    private static final List<Shift> shiftList = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                shiftList.addAll(Arrays.asList(
                        new ProjectionistShift(
                                new MockEmployeeRepository().getEmployee(1),
                                LocalDate.now().plusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().minusHours(2).withSecond(0).withNano(0).toString(),
                                LocalTime.now().plusHours(0).withSecond(0).withNano(0).toString(),
                                new MockEmployeeRepository().getEmployee(1).getCinema().getHallList().get(1)
                        ),
                        new UsherShift(
                                new MockEmployeeRepository().getEmployee(0),
                                LocalDate.now().minusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().minusHours(2).withSecond(0).withNano(0).toString(),
                                LocalTime.now().plusHours(0).withSecond(0).withNano(0).toString()
                        ),
                        new ProjectionistShift(
                                new MockEmployeeRepository().getEmployee(3),
                                LocalDate.now().plusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().minusHours(2).withSecond(0).withNano(0).toString(),
                                LocalTime.now().plusHours(0).withSecond(0).withNano(0).toString(),
                                new MockEmployeeRepository().getEmployee(3).getCinema().getHallList().get(0)
                        ),
                        new UsherShift(
                                new MockEmployeeRepository().getEmployee(2),
                                LocalDate.now().minusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().minusHours(2).withSecond(0).withNano(0).toString(),
                                LocalTime.now().plusHours(0).withSecond(0).withNano(0).toString()
                        )
                ));
            }
        }
    }

    @Override
    public Result<List<Shift>> getAllEmployeeShiftBetweenDate(Employee employee, LocalDate start, LocalDate end) {
        return new Result<>(shiftList.stream()
                .filter(shift -> LocalDate.parse(shift.getDate()).isAfter(start.minusDays(1))
                        && LocalDate.parse(shift.getDate()).isBefore(end.plusDays(1)))
                .collect(Collectors.toList()));
    }

    @Override
    public Result<List<Shift>> getShiftList() {
        return new Result<>(shiftList);
    }

    @Override
    public void deletedShift(Shift shift) throws ShiftSaveException {
        if (!shiftList.removeIf(s -> s.equals(shift))) {
            throw new ShiftSaveException("Shift not Exist");
        }
    }

    @Override
    public void saveShift(Shift shift) throws ShiftSaveException {
        LocalTime newStart = LocalTime.parse(shift.getStart());
        LocalTime newEnd = LocalTime.parse(shift.getEnd());
        LocalTime start;
        LocalTime end;

        for (Shift elem : shiftList) {
            start = LocalTime.parse(elem.getStart());
            end = LocalTime.parse(elem.getEnd());
            if (elem.getDate().equals(shift.getDate())
                    && (newStart.isBefore(end)
                    && newEnd.isAfter(start))
                    && shift.getEmployee().equals(elem.getEmployee())) {
                throw new ShiftSaveException("Shift Already Exist");

            }
        }
        shiftList.add(shift);
    }

    @Override
    public void modifyShift(Shift oldShift, Shift newShift) throws ShiftSaveException {
        LocalTime newStart = LocalTime.parse(newShift.getStart());
        LocalTime newEnd = LocalTime.parse(newShift.getEnd());
        LocalTime start;
        LocalTime end;

        if (oldShift.equals(newShift)) {
            throw new ShiftSaveException("Shift Already Exist");
        }
        shiftList.removeIf(s -> s.equals(oldShift));
        for (Shift elem : shiftList) {
            start = LocalTime.parse(elem.getStart());
            end = LocalTime.parse(elem.getEnd());
            if (elem.getDate().equals(newShift.getDate())
                    && (newStart.isBefore(end)
                    && newEnd.isAfter(start))
                    && newShift.getEmployee().equals(elem.getEmployee())) {
                shiftList.add(oldShift);
                throw new ShiftSaveException("Shift Already Exist");
            }
        }
        shiftList.add(newShift);

    }
}