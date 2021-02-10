package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
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
