package com.ttbmp.cinehub.app.repository.employee.projectionist;

import com.ttbmp.cinehub.app.repository.LazyLoadingException;
import com.ttbmp.cinehub.app.repository.RepositoryException;
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
            try {
                setName(userRepository.getUser(getId()).getName());
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
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
            try {
                setSurname(userRepository.getUser(getId()).getSurname());
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
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
            try {
                setEmail(userRepository.getUser(getId()).getEmail());
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
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
            try {
                setRoles(userRepository.getUser(getId()).getRoles());
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
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
            try {
                setRoles(userRepository.getUser(getId()).getRoles());
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
        }
        return super.hasPermission(requiredPermission);

    }

    @Override
    public Cinema getCinema() {
        try {
            if (!isCinemaLoaded) {
                setCinema(cinemaRepository.getCinema(this));
            }
            return super.getCinema();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setCinema(Cinema cinema) {
        isCinemaLoaded = true;
        super.setCinema(cinema);
    }


    @Override
    public List<Shift> getShiftList() {
        if (!isShiftListLoaded) {
            try {
                setShiftList(shiftRepository.getShiftList(this));
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
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
        try {
            return shiftRepository.getAllEmployeeShiftBetweenDate(this, start, end);
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
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
