package com.ttbmp.cinehub.core.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class User {

    private final List<Ticket> ownedTicket = new ArrayList<>();
    private Integer id;
    private String name;
    private String email;
    private CreditCard card;

    public User(String name, String email, CreditCard card) {
        this.name = name;
        this.email = email;
        this.setCard(card);

    }

    public User(int userId) {
        this.id = userId;
    }

    public List<Ticket> getOwnedTicket() {
        return ownedTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {

    }



    public void addTicket(Ticket ticket) {
        this.ownedTicket.add(ticket);
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

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

}