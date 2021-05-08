package com.ttbmp.cinehub.app.repository.employee.projectionist;

import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.security.Permission;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class ProjectionistProxy extends Projectionist {

    private final UserRepository userRepository;
    private final CinemaRepository cinemaRepository;
    private final ShiftRepository shiftRepository;

    private boolean isNameLoaded = false;
    private boolean isSurnameLoaded = false;
    private boolean isEmailLoaded = false;
    private boolean isRoleArrayLoaded = false;
    private boolean isCinemaLoaded = false;
    private boolean isShiftListLoaded = false;

    public ProjectionistProxy(
            String id,
            UserRepository userRepository,
            CinemaRepository cinemaRepository,
            ShiftRepository shiftRepository) {
        super(id, null, null, null, null, null);
        this.userRepository = userRepository;
        this.cinemaRepository = cinemaRepository;
        this.shiftRepository = shiftRepository;
    }

    @Override
    public String getName() {
        if (!isNameLoaded) {
            setName(userRepository.getUser(getId()).getName());
        }
        return super.getName();
    }

    @Override
    public void setName(String name) {
        isNameLoaded = true;
        super.setName(name);
    }

    @Override
    public String getSurname() {
        if (!isSurnameLoaded) {
            setSurname(userRepository.getUser(getId()).getSurname());
        }
        return super.getSurname();
    }

    @Override
    public void setSurname(String surname) {
        isSurnameLoaded = true;
        super.setSurname(surname);
    }

    @Override
    public String getEmail() {
        if (!isEmailLoaded) {
            setEmail(userRepository.getUser(getId()).getEmail());
        }
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        isEmailLoaded = true;
        super.setEmail(email);
    }

    @Override
    public Role[] getRoles() {
        if (!isRoleArrayLoaded) {
            setRoles(userRepository.getUser(getId()).getRoles());
        }
        return super.getRoles();
    }

    @Override
    public void setRoles(Role[] roles) {
        isRoleArrayLoaded = true;
        super.setRoles(roles);
    }

    @Override
    public boolean hasPermission(Permission requiredPermission) {
        if (!isRoleArrayLoaded) {
            setRoles(userRepository.getUser(getId()).getRoles());
        }
        return super.hasPermission(requiredPermission);
    }

    @Override
    public Cinema getCinema() {
        if (!isCinemaLoaded) {
            setCinema(cinemaRepository.getCinema(this));
        }
        return super.getCinema();
    }

    @Override
    public void setCinema(Cinema cinema) {
        isCinemaLoaded = true;
        super.setCinema(cinema);
    }


    @Override
    public List<Shift> getShiftList() {
        if (!isShiftListLoaded) {
            setShiftList(shiftRepository.getShiftList(this));
        }
        return super.getShiftList();
    }

    @Override
    public void setShiftList(List<Shift> shiftList) {
        isShiftListLoaded = true;
        super.setShiftList(shiftList);
    }

    @Override
    public List<Shift> getShiftListBetween(LocalDate start, LocalDate end) {
        return shiftRepository.getAllEmployeeShiftBetweenDate(this, start, end);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
