package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.entity.ShiftProjectionist;
import com.ttbmp.cinehub.core.entity.ShiftUsher;
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
                        new ShiftProjectionist(
                                new MockEmployeeRepository().getEmployee(1),
                                LocalDate.now().plusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().minusHours(2).withSecond(0).withNano(0).toString(),
                                LocalTime.now().plusHours(0).withSecond(0).withNano(0).toString(),
                                new MockEmployeeRepository().getEmployee(1).getCinema().getHallList().get(1)
                        ),
                        new ShiftUsher(
                                new MockEmployeeRepository().getEmployee(0),
                                LocalDate.now().minusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().minusHours(2).withSecond(0).withNano(0).toString(),
                                LocalTime.now().plusHours(0).withSecond(0).withNano(0).toString()
                        ),
                        new ShiftProjectionist(
                                new MockEmployeeRepository().getEmployee(3),
                                LocalDate.now().plusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().minusHours(2).withSecond(0).withNano(0).toString(),
                                LocalTime.now().plusHours(0).withSecond(0).withNano(0).toString(),
                                new MockEmployeeRepository().getEmployee(3).getCinema().getHallList().get(0)
                        ),
                        new ShiftUsher(
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
    public Result<Shift> deletedShift(Shift shift) {
        shiftList.removeIf(s -> s.equals(shift));
        return new Result<>(shift);
    }

    @Override
    public Result<List<Shift>> getShiftList() {
        return new Result<>(shiftList);
    }

    @Override
    public Result<Boolean> saveShift(Shift shift) {
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
                return new Result<>(false);
            }
        }
        shiftList.add(shift);
        return new Result<>(true);
    }
}