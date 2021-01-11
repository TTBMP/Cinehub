package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.utilities.result.Result;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockShiftRepository implements ShiftRepository {

    private final List<Shift> shiftList;

    public MockShiftRepository() {
        shiftList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 5; j++) {
                shiftList.addAll(Arrays.asList(
                        new Shift(
                                new Employee(),
                                LocalDate.now().plusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().toString(),
                                LocalTime.now().plusHours(2).toString()
                        ),
                        new Shift(
                                new Employee(),
                                LocalDate.now().minusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().toString(),
                                LocalTime.now().plusHours(2).toString()
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
}
