package com.ttbmp.cinehub.app.usecase.buyticket;

import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketMagicBox;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketOpenBar;
import com.ttbmp.cinehub.domain.ticket.decorator.TicketSkipLine;

public class TicketOperation {

    public Ticket calculatePrice(Ticket ticket, Boolean openBarOption, Boolean magicBoxOption, Boolean skipLineOption){
        if (Boolean.TRUE.equals(openBarOption)) {
            ticket = new TicketOpenBar(ticket);
        }
        if (Boolean.TRUE.equals(magicBoxOption)) {
            ticket = new TicketMagicBox(ticket);
        }
        if (Boolean.TRUE.equals(skipLineOption)) {
            ticket = new TicketSkipLine(ticket);
        }
        return ticket;
    }
}
