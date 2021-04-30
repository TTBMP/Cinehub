package com.ttbmp.cinehub.domain;

import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.List;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
public class User {

    private String id;
    private String name;
    private String surname;
    private String email;
    private CreditCard creditCard;
    private List<Ticket> ownedTicketList;

    public User(String id, String name, String surname, String email, CreditCard creditCard) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.creditCard = creditCard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
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
        var other = (Employee) obj;
        return id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
