package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.customer.CustomerRepository;
import com.ttbmp.cinehub.app.repository.seat.SeatRepository;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
public class MockTicketRepository implements TicketRepository {

    private static final List<TicketData> TICKET_DATA_LIST = new ArrayList<>();
    private static int counterTicketId = 1;

    private final ServiceLocator serviceLocator;

    public MockTicketRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<TicketData> getTicketDataList() {
        return TICKET_DATA_LIST;
    }

    @Override
    public synchronized void saveTicket(Ticket ticket, Projection projection) {
        TICKET_DATA_LIST.add(new TicketData(
                counterTicketId++,
                ticket.getPrice(),
                ticket.getOwner().getId(),
                projection.getId(),
                ticket.getSeat().getId()

        ));
    }

    @Override
    public List<Ticket> getTicketList(Customer customer) {
        return null;
    }

    @Override
    public List<Ticket> getTicketList(Projection projection) {
        return TICKET_DATA_LIST.stream()
                .filter(d -> d.projectionId == projection.getId())
                .map(d -> new TicketProxy(
                        d.id,
                        d.price,
                        serviceLocator.getService(CustomerRepository.class),
                        serviceLocator.getService(SeatRepository.class)
                ))
                .collect(Collectors.toList());
    }

    public static class TicketData {

        private int id;
        private long price;
        private String userId;
        private int projectionId;
        private int seatId;

        public TicketData(int id, long price, String userId, int projectionId, int seatId) {
            this.id = id;
            this.price = price;
            this.userId = userId;
            this.projectionId = projectionId;
            this.seatId = seatId;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getPrice() {
            return price;
        }

        public void setPrice(long price) {
            this.price = price;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getProjectionId() {
            return projectionId;
        }

        public void setProjectionId(int projectionId) {
            this.projectionId = projectionId;
        }

        public int getSeatId() {
            return seatId;
        }

        public void setSeatId(int seatId) {
            this.seatId = seatId;
        }
    }

}
