package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.domain.user.User;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

/**
 * @author Fabio Buracchi
 */
public interface UserRepository {

    User getUser(String userId);

    User getUser(Ticket ticket);

}
