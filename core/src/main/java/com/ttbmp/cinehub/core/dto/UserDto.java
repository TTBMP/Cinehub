package com.ttbmp.cinehub.core.dto;

import com.ttbmp.cinehub.core.entity.CreditCard;
import com.ttbmp.cinehub.core.entity.ticket.component.TicketNormal;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Palmieri Ivan
 */
public class UserDto {

    private final List<TicketNormal> ownedTicketNormal = new ArrayList<>();
    private Integer id;
    private String name;
    private String email;
    private CreditCard card;

    public UserDto(String name, String email, CreditCard card) {
        this.name = name;
        this.email = email;
        this.setCard(card);

    }

    public UserDto(int userId) {
        this.id = userId;
    }

    public UserDto() {

    }

    public List<TicketNormal> getOwnedTicket() {
        return ownedTicketNormal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addTicket(TicketNormal ticketNormal) {
        this.ownedTicketNormal.add(ticketNormal);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

}
