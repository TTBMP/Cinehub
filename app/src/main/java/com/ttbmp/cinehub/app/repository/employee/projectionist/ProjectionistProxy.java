package com.ttbmp.cinehub.app.repository.employee.projectionist;

import com.ttbmp.cinehub.app.repository.LazyLoadingException;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.creditcard.CreditCardRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.CreditCard;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class ProjectionistProxy extends Projectionist {

    private final UserRepository userRepository;
    private final CreditCardRepository creditCardRepository;
    private final CinemaRepository cinemaRepository;
    private final ShiftRepository shiftRepository;

    private boolean isUserLoaded = false;
    private boolean isCreditCardLoaded = false;
    private boolean isCinemaLoaded = false;
    private boolean isShiftListLoaded = false;

    public ProjectionistProxy(
            String id,
            UserRepository userRepository,
            CreditCardRepository creditCardRepository,
            CinemaRepository cinemaRepository,
            ShiftRepository shiftRepository) {
        super(id, null, null, null, null, null);
        this.userRepository = userRepository;
        this.creditCardRepository = creditCardRepository;
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

    private void loadUser() {
        try {
            var user = userRepository.getUser(getId());
            setName(user.getName());
            setSurname(user.getSurname());
            setEmail(user.getEmail());
            isUserLoaded = true;
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public CreditCard getCreditCard() {
        if (!isCreditCardLoaded) {
            setCreditCard(creditCardRepository.getCreditCard(this));
        }
        return super.getCreditCard();
    }

    @Override
    public void setCreditCard(CreditCard creditCard) {
        isCreditCardLoaded = true;
        super.setCreditCard(creditCard);
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
            e.printStackTrace();
            return null;
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
