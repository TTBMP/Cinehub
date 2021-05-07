package com.ttbmp.cinehub.app.repository.employee.usher;

import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.employee.Usher;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class UsherProxy extends Usher {

    private final UserRepository userRepository;
    private final CinemaRepository cinemaRepository;
    private final ShiftRepository shiftRepository;

    private boolean isUserLoaded = false;
    private boolean isRoleArrayLoaded = false;
    private boolean isCinemaLoaded = false;
    private boolean isShiftListLoaded = false;

    public UsherProxy(
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
        if (!isUserLoaded) {
            loadUser();
        }
        return super.getName();
    }

    @Override
    public String getSurname() {
        if (!isUserLoaded) {
            loadUser();
        }
        return super.getSurname();
    }

    @Override
    public String getEmail() {
        if (!isUserLoaded) {
            loadUser();
        }
        return super.getEmail();
    }

    @Override
    public Role[] getRoles() {
        if (!isRoleArrayLoaded) {
            loadUser();
        }
        return super.getRoles();
    }

    private void loadUser() {
        var user = userRepository.getUser(getId());
        setName(user.getName());
        setSurname(user.getSurname());
        setEmail(user.getEmail());
        setRoles(user.getRoles());
        isUserLoaded = true;
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
