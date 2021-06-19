package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.ticket.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
public class MockTicketRepository extends MockRepository implements TicketRepository {

    public static final String ID = "id";
    public static final String PRICE = "price";
    public static final String USER_ID = "userId";
    public static final String PROJECTION_ID = "projectionId";
    public static final String SEAT_ID = "seatId";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockTicketRepository.class);
    private static int counterTicketId = 1;

    public MockTicketRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

    @Override
    public synchronized void saveTicket(Ticket ticket) {
        mockDataList.add(new HashMap<>(Map.of(
                ID, Integer.toString(counterTicketId++),
                PRICE, Long.toString(ticket.getPrice()),
                USER_ID, ticket.getOwner().getId(),
                PROJECTION_ID, Integer.toString(ticket.getProjection().getId()),
                SEAT_ID, Integer.toString(ticket.getSeat().getId())
        )));
    }

    @Override
    public List<Ticket> getTicketList(Customer customer) throws RepositoryException {
        return mockDataList.stream()
                .filter(m -> m.get(USER_ID).equals(customer.getId()))
                .map(m -> new TicketProxy(
                        getServiceLocator(),
                        Integer.parseInt(m.get(ID)),
                        Long.parseLong(m.get(PRICE))
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getTicketList(Projection projection) {
        return mockDataList.stream()
                .filter(m -> m.get(PROJECTION_ID).equals(Integer.toString(projection.getId())))
                .map(m -> new TicketProxy(
                        getServiceLocator(),
                        Integer.parseInt(m.get(ID)),
                        Long.parseLong(m.get(PRICE))
                ))
                .collect(Collectors.toList());
    }

}
