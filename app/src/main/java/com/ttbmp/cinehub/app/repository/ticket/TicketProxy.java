package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

/**
 * @author Fabio Buracchi
 */
public class TicketProxy extends Ticket {

    private final SeatRepository seatRepository;
    private boolean isSeatLoaded = false;

    public TicketProxy(int id, long price, SeatRepository seatRepository) {
        super(id, price,  null);
        this.seatRepository = seatRepository;
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
