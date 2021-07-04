package com.ttbmp.cinehub.app.repository.employee.projectionist;

import com.ttbmp.cinehub.app.di.ServiceLocator;
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
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
@EqualsAndHashCode(callSuper = true)
public class ProjectionistProxy extends Projectionist {

    private final UserRepository userRepository;
    private final CinemaRepository cinemaRepository;
    private final ShiftRepository shiftRepository;

    private boolean isProjectionistNameLoaded = false;
    private boolean isProjectionistSurnameLoaded = false;
    private boolean isProjectionistEmailLoaded = false;
    private boolean isProjectionistRoleListLoaded = false;
    private boolean isProjectionistCinemaLoaded = false;
    private boolean isProjectionistShiftListLoaded = false;

    public ProjectionistProxy(ServiceLocator serviceLocator, String id) {
        super(id, null, null, null, null, null);
        this.userRepository = serviceLocator.getService(UserRepository.class);
        this.cinemaRepository = serviceLocator.getService(CinemaRepository.class);
        this.shiftRepository = serviceLocator.getService(ShiftRepository.class);
    }

    @Override
    public String getName() {
        if (!isProjectionistNameLoaded) {
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
        isProjectionistNameLoaded = true;
        super.setName(name);
    }

    @Override
    public String getSurname() {
        if (!isProjectionistSurnameLoaded) {
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
        isProjectionistSurnameLoaded = true;
        super.setSurname(surname);
    }

    @Override
    public String getEmail() {
        if (!isProjectionistEmailLoaded) {
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
        isProjectionistEmailLoaded = true;
        super.setEmail(email);
    }

    @Override
    public List<Role> getRoleList() {
        if (!isProjectionistRoleListLoaded) {
            try {
                setRoleList(userRepository.getUser(getId()).getRoleList());
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
        }
        return super.getRoleList();
    }

    @Override
    public void setRoleList(List<Role> roleList) {
        isProjectionistRoleListLoaded = true;
        super.setRoleList(roleList);
    }

    @Override
    public boolean hasPermission(Permission requiredPermission) {
        if (!isProjectionistRoleListLoaded) {
            try {
                setRoleList(userRepository.getUser(getId()).getRoleList());
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
        }
        return super.hasPermission(requiredPermission);
    }

    @Override
    public Cinema getCinema() {
        try {
            if (!isProjectionistCinemaLoaded) {
                setCinema(cinemaRepository.getCinema(this));
            }
            return super.getCinema();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setCinema(Cinema cinema) {
        isProjectionistCinemaLoaded = true;
        super.setCinema(cinema);
    }

    @Override
    public List<Shift> getShiftList() {
        if (!isProjectionistShiftListLoaded) {
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
        isProjectionistShiftListLoaded = true;
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

}
