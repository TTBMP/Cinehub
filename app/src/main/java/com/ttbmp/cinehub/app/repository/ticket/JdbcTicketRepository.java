package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.service.persistence.dao.TicketDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcTicketRepository extends JdbcRepository implements TicketRepository {

    public JdbcTicketRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public void saveTicket(Ticket ticket) throws RepositoryException {
        try {
            getDao(TicketDao.class).insert(new com.ttbmp.cinehub.service.persistence.entity.Ticket(
                    ticket.getId(),
                    ticket.getSeat().getId(),
                    ticket.getProjection().getId(),
                    ticket.getOwner().getId(),
                    ticket.getPrice()
            ));
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Ticket> getTicketList(Customer customer) throws RepositoryException {
        try {
            var ticketList = getDao(TicketDao.class).getTicketList(customer.getId());
            return ticketList.stream()
                    .map(ticket -> new TicketProxy(getServiceLocator(), ticket.getId(), ticket.getPrice()))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Ticket> getTicketList(Projection projection) throws RepositoryException {
        try {
            var ticketList = getDao(TicketDao.class).getTicketList(projection.getId());
            return ticketList.stream()
                    .map(ticket -> new TicketProxy(getServiceLocator(), ticket.getId(), ticket.getPrice()))
                    .collect(Collectors.toList());
        } catch (DaoMethodException e) {
            return new ArrayList<>();
        }
    }

}
