package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

/**
 * @author Fabio Buracchi
 */
public interface UserRepository {

    User getUser(String userId) throws  RepositoryException;

    User getUser(Ticket ticket) throws  RepositoryException;

}
