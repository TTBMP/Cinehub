package com.ttbmp.cinehub.app.repository.mock;

import com.ttbmp.cinehub.app.repository.TicketRepository;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.ArrayList;
import java.util.List;

public class MockTicketRepository implements TicketRepository {

    private static final List<Ticket> ticketList = new ArrayList<>();

    @Override
    public void saveTicket(Ticket ticket) {
        ticketList.add(ticket);
    }

}
