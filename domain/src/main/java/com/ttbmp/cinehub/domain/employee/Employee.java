package com.ttbmp.cinehub.domain.employee;

import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.shift.Shift;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public abstract class Employee extends User {

    private Cinema cinema;
    private List<Shift> shiftList;

    protected Employee(String id, String name, String surname, String email, List<Role> roleList, Cinema cinema) {
        super(id, name, surname, email, roleList);
        this.cinema = cinema;
    }

    public List<Shift> getShiftListBetween(LocalDate start, LocalDate end) {
        return shiftList.stream()
                .filter(shift -> LocalDate.parse(shift.getDate()).isAfter(start))
                .filter(shift -> LocalDate.parse(shift.getEnd()).isBefore(end))
                .collect(Collectors.toList());
    }

}
