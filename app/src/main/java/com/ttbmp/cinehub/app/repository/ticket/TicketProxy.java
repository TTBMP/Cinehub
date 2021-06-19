package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.LazyLoadingException;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.customer.CustomerRepository;
import com.ttbmp.cinehub.app.repository.projection.ProjectionRepository;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.ticket.Ticket;
import lombok.EqualsAndHashCode;

/**
 * @author Fabio Buracchi
 */
@EqualsAndHashCode(callSuper = true)
public class TicketProxy extends Ticket {

    private final CustomerRepository customerRepository;
    private final SeatRepository seatRepository;
    private final ProjectionRepository projectionRepository;
    private boolean isUserLoaded = false;
    private boolean isSeatLoaded = false;
    private boolean isProjectionLoaded = false;

    public TicketProxy(ServiceLocator serviceLocator, int id, long price) {
        super(id, price, null, null, null);
        this.seatRepository = serviceLocator.getService(SeatRepository.class);
        this.customerRepository = serviceLocator.getService(CustomerRepository.class);
        this.projectionRepository = serviceLocator.getService(ProjectionRepository.class);
    }

    @Override
    public Customer getOwner() {
        if (!isUserLoaded) {
            try {
                setOwner(customerRepository.getCustomer(this));
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
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
        try {
            if (!isSeatLoaded) {
                setSeat(seatRepository.getSeat(this));
            }
            return super.getSeat();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setSeat(Seat seat) {
        isSeatLoaded = true;
        super.setSeat(seat);
    }

    @Override
    public Projection getProjection() {
        try {
            if (!isProjectionLoaded) {
                setProjection(projectionRepository.getProjection(this));
            }
            return super.getProjection();
        } catch (RepositoryException e) {
            throw new LazyLoadingException(e.getMessage());
        }
    }

    @Override
    public void setProjection(Projection projection) {
        isProjectionLoaded = true;
        super.setProjection(projection);
    }

}
