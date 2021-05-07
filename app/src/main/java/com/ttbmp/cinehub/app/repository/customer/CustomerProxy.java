package com.ttbmp.cinehub.app.repository.customer;

import com.ttbmp.cinehub.app.repository.creditcard.CreditCardRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.CreditCard;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class CustomerProxy extends Customer {

    private final CreditCardRepository creditCardRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    private boolean isUserLoaded = false;
    private boolean isCreditCardLoaded = false;
    private boolean isTicketListLoaded = false;

    public CustomerProxy(String id, UserRepository userRepository, CreditCardRepository creditCardRepository, TicketRepository ticketRepository) {
        super(id, null, null, null, null, null);
        this.userRepository = userRepository;
        this.creditCardRepository = creditCardRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public CreditCard getCreditCard() {
        if (!isCreditCardLoaded) {
            setCreditCard(creditCardRepository.getCreditCard(this));
        }
        return super.getCreditCard();
    }

    @Override
    public void setCreditCard(CreditCard creditCard) {
        isCreditCardLoaded = true;
        super.setCreditCard(creditCard);
    }

    @Override
    public List<Ticket> getOwnedTicketList() {
        if (!isTicketListLoaded) {
            setOwnedTicketList(ticketRepository.getTicketList(this));
        }
        return super.getOwnedTicketList();
    }

    @Override
    public void setOwnedTicketList(List<Ticket> ownedTicketList) {
        isTicketListLoaded = true;
        super.setOwnedTicketList(ownedTicketList);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
