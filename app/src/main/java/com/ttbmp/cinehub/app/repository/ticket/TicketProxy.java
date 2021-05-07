package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.repository.customer.CustomerRepository;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

/**
 * @author Fabio Buracchi
 */
public class TicketProxy extends Ticket {

    private final CustomerRepository customerRepository;
    private final SeatRepository seatRepository;
    private boolean isUserLoaded = false;
    private boolean isSeatLoaded = false;

    public TicketProxy(int id, long price, CustomerRepository customerRepository, SeatRepository seatRepository) {
        super(id, price, null, null);
        this.seatRepository = seatRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getOwner() {
        if (!isUserLoaded) {
            setOwner(customerRepository.getCustomer(this));
        }
        return super.getOwner();
    }

    @Override
    public void setOwner(Customer owner) {
        isUserLoaded = true;
        super.setOwner(owner);
    }

    @Override
    public Seat getSeat() {
        if (!isSeatLoaded) {
            setSeat(seatRepository.getSeat(this));
        }
        return super.getSeat();
    }

    @Override
    public void setSeat(Seat seat) {
        isSeatLoaded = true;
        super.setSeat(seat);
    }

}
