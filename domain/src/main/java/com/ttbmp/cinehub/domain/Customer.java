package com.ttbmp.cinehub.domain;

import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.ticket.Ticket;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Customer extends User {

    private List<Ticket> ownedTicketList;

    public Customer(String id, String name, String surname, String email, List<Role> roleList, List<Ticket> ownedTicketList) {
        super(id, name, surname, email, roleList);
        this.ownedTicketList = ownedTicketList;
    }

}
