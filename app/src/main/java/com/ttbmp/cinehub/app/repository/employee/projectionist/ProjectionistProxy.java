package com.ttbmp.cinehub.app.repository.employee.projectionist;

import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.creditcard.CreditCardRepository;
import com.ttbmp.cinehub.app.repository.customer.CustomerRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.CreditCard;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class ProjectionistProxy extends Projectionist {

    private final CustomerRepository customerRepository;
    private final CreditCardRepository creditCardRepository;
    private final CinemaRepository cinemaRepository;
    private final ShiftRepository shiftRepository;

    private boolean isUserLoaded = false;
    private boolean isCreditCardLoaded = false;
    private boolean isCinemaLoaded = false;
    private boolean isShiftListLoaded = false;

    public ProjectionistProxy(
            String id,
            CustomerRepository customerRepository,
            CreditCardRepository creditCardRepository,
            CinemaRepository cinemaRepository,
            ShiftRepository shiftRepository) {
        super(id, null, null, null, null, null);
        this.customerRepository = customerRepository;
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
        Customer customer = customerRepository.getCustomer(getId());
        setName(customer.getName());
        setSurname(customer.getSurname());
        setEmail(customer.getEmail());
        isUserLoaded = true;
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
