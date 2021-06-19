package com.ttbmp.cinehub.domain.ticket;

import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.Seat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Ivan Palmieri
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Ticket {

    private int id;
    private long price;
    private Seat seat;
    private Customer owner;
    private Projection projection;

    public Ticket(int id, long price, Customer owner, Seat seat, Projection projection) {
        this.id = id;
        this.price = price;
        this.owner = owner;
        this.seat = seat;
        this.projection = projection;
    }

}
