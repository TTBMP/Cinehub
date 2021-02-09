package com.ttbmp.cinehub.app.repository;

import com.ttbmp.cinehub.domain.ticket.component.Ticket;

public interface TicketRepository {

    void saveTicket(Ticket ticket);
}
