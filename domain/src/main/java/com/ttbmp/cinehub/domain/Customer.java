package com.ttbmp.cinehub.domain;

import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class Customer extends User {

    private List<Ticket> ownedTicketList;

    public Customer(String id, String name, String surname, String email, Role[] roles, List<Ticket> ownedTicketList) {
        super(id, name, surname, email, roles);
        this.ownedTicketList = ownedTicketList;
    }

    public List<Ticket> getOwnedTicketList() {
        return ownedTicketList;
    }

    public void setOwnedTicketList(List<Ticket> ownedTicketList) {
        this.ownedTicketList = ownedTicketList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        var other = (Customer) obj;
        return getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}
