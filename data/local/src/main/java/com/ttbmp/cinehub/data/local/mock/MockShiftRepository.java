package com.ttbmp.cinehub.data.local.mock;

import com.ttbmp.cinehub.core.entity.*;
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

    private final List<Shift> shiftList;

    public MockShiftRepository() {
        String s = "Comunale";
        String s1 = "MultiPlex";
        Employee employee1 = new Projectionist("ciao", "bella", "proiezionista", new Cinema(s), 0);
        Employee employee2 = new Usher("fabio", "buracchi", "maschera", new Cinema(s1), 0);
        Employee employee3 = new Projectionist("Massimo", "Mazzetti", "proiezionista", new Cinema(s1), 0);
        Employee employee4 = new Usher("Ivan", "Palmieri", "maschera", new Cinema(s), 0);

        Hall hall = new Hall("1", new Cinema(s));
        Hall hall2 = new Hall("1", new Cinema(s1));
        shiftList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                shiftList.addAll(Arrays.asList(
                        new ShiftProjectionist(
                                employee1,
                                LocalDate.now().plusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().withSecond(0).withNano(0).toString(),
                                LocalTime.now().plusHours(2).withSecond(0).withNano(0).toString(),
                                hall
                        ),
                        new ShiftUsher(
                                employee2,
                                LocalDate.now().minusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().withSecond(0).withNano(0).toString(),
                                LocalTime.now().plusHours(2).withSecond(0).withNano(0).toString()
                        ),
                        new ShiftProjectionist(
                                employee3,
                                LocalDate.now().plusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().withSecond(0).withNano(0).toString(),
                                LocalTime.now().plusHours(2).withSecond(0).withNano(0).toString(),
                                hall2
                        ),
                        new ShiftUsher(
                                employee4,
                                LocalDate.now().minusWeeks(i).plusDays(j).toString(),
                                LocalTime.now().withSecond(0).withNano(0).toString(),
                                LocalTime.now().plusHours(2).withSecond(0).withNano(0).toString()
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
