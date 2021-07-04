package com.ttbmp.cinehub.domain;

import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.domain.ticket.Ticket;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Projection {

    private int id;
    private String date;
    private String startTime;
    private Movie movie;
    private Hall hall;
    private Projectionist projectionist;
    private List<Ticket> ticketList;
    private long basePrice;

    public boolean isBooked(Seat seat) {
        return getTicketList().stream().anyMatch(t -> t.getSeat().getId() == seat.getId());
    }


}
