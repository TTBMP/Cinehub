package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

/**
 * @author Fabio Buracchi
 */
public class TicketProxy extends Ticket {

    private final UserRepository userRepository;

    private boolean isUserLoaded = false;

    public TicketProxy(int id, long price, UserRepository userRepository) {
        super(id, price, null);
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

}
