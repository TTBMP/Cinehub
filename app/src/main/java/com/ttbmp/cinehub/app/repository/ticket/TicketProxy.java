package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.user.User;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

/**
 * @author Fabio Buracchi
 */
public class TicketProxy extends Ticket {

    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private boolean isUserLoaded = false;
    private boolean isSeatLoaded = false;

    public TicketProxy(int id, long price, UserRepository userRepository, SeatRepository seatRepository) {
        super(id, price, null, null);
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
    }


    @Override
    public User getOwner() {
        if (!isUserLoaded) {
            setOwner(userRepository.getUser(this));
        }
        return super.getOwner();
    }

    @Override
    public void setOwner(User owner) {
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
