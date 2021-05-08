package com.ttbmp.cinehub.app.repository.customer;

import com.ttbmp.cinehub.app.repository.creditcard.CreditCardRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.CreditCard;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.security.Permission;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class CustomerProxy extends Customer {

    private final CreditCardRepository creditCardRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    private boolean isNameLoaded = false;
    private boolean isSurnameLoaded = false;
    private boolean isEmailLoaded = false;
    private boolean isRoleArrayLoaded = false;
    private boolean isCreditCardLoaded = false;
    private boolean isTicketListLoaded = false;

    public CustomerProxy(String id, UserRepository userRepository, CreditCardRepository creditCardRepository, TicketRepository ticketRepository) {
        super(id, null, null, null, null, null, null);
        this.userRepository = userRepository;
        this.creditCardRepository = creditCardRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public String getName() {
        if (!isNameLoaded) {
            setName(userRepository.getUser(getId()).getName());
        }
        return super.getName();
    }

    @Override
    public void setName(String name) {
        isNameLoaded = true;
        super.setName(name);
    }

    @Override
    public String getSurname() {
        if (!isSurnameLoaded) {
            setSurname(userRepository.getUser(getId()).getSurname());
        }
        return super.getSurname();
    }

    @Override
    public void setSurname(String surname) {
        isSurnameLoaded = true;
        super.setSurname(surname);
    }

    @Override
    public String getEmail() {
        if (!isEmailLoaded) {
            setEmail(userRepository.getUser(getId()).getEmail());
        }
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        isEmailLoaded = true;
        super.setEmail(email);
    }

    @Override
    public Role[] getRoles() {
        if (!isRoleArrayLoaded) {
            setRoles(userRepository.getUser(getId()).getRoles());
        }
        return super.getRoles();
    }

    @Override
    public void setRoles(Role[] roles) {
        isRoleArrayLoaded = true;
        super.setRoles(roles);
    }

    @Override
    public boolean hasPermission(Permission requiredPermission) {
        if (!isRoleArrayLoaded) {
            setRoles(userRepository.getUser(getId()).getRoles());
        }
        return super.hasPermission(requiredPermission);
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
