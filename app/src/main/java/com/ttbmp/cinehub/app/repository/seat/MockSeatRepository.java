package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.ticket.MockTicketRepository;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockSeatRepository extends MockRepository implements SeatRepository {

    public static final String ID = "id";
    public static final String HALL_ID = "hallId";
    public static final String POSITION = "position";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockSeatRepository.class);
    private static int seatIdCounter = 1;

    static {
        var hallIdList = MockHallRepository.getMockDataList().stream()
                .map(m -> m.get(MockHallRepository.ID))
                .collect(Collectors.toList());
        for (var hallId : hallIdList) {
            for (var i = 0; i < 7; i++) {
                mockDataList.add(new HashMap<>(Map.of(ID, Integer.toString(seatIdCounter++), HALL_ID, hallId, POSITION, "A" + (Integer.parseInt(hallId) + i) % 7)));
                mockDataList.add(new HashMap<>(Map.of(ID, Integer.toString(seatIdCounter++), HALL_ID, hallId, POSITION, "B" + (Integer.parseInt(hallId) + i) % 7)));
                mockDataList.add(new HashMap<>(Map.of(ID, Integer.toString(seatIdCounter++), HALL_ID, hallId, POSITION, "C" + (Integer.parseInt(hallId) + i) % 7)));
                mockDataList.add(new HashMap<>(Map.of(ID, Integer.toString(seatIdCounter++), HALL_ID, hallId, POSITION, "D" + (Integer.parseInt(hallId) + i) % 7)));
                mockDataList.add(new HashMap<>(Map.of(ID, Integer.toString(seatIdCounter++), HALL_ID, hallId, POSITION, "E" + (Integer.parseInt(hallId) + i) % 7)));
                mockDataList.add(new HashMap<>(Map.of(ID, Integer.toString(seatIdCounter++), HALL_ID, hallId, POSITION, "F" + (Integer.parseInt(hallId) + i) % 7)));
            }
        }
    }

    public MockSeatRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

    @Override
    public Seat getSeat(int id) throws RepositoryException {
        return mockDataList.stream()
                .filter(m -> m.get(ID).equals(Integer.toString(id)))
                .findAny()
                .map(m -> new SeatProxy(
                        getServiceLocator(),
                        id,
                        m.get(POSITION)
                ))
                .orElse(null);
    }

    @Override
    public Seat getSeat(Ticket ticket) {
        return MockTicketRepository.getMockDataList().stream()
                .filter(m -> m.get(MockTicketRepository.ID).equals(Integer.toString(ticket.getId())))
                .map(m -> m.get(MockTicketRepository.SEAT_ID))
                .findAny()
                .flatMap(ticketSeatId -> mockDataList.stream()
                        .filter(m -> m.get(ID).equals(ticketSeatId))
                        .findAny()
                        .map(m -> new SeatProxy(
                                getServiceLocator(),
                                Integer.parseInt(m.get(ID)),
                                m.get(POSITION)
                        ))
                )
                .orElse(null);
    }

    @Override
    public List<Seat> getSeatList(Hall hall) {
        return mockDataList.stream()
                .filter(m -> m.get(HALL_ID).equals(Integer.toString(hall.getId())))
                .map(m -> new SeatProxy(
                        getServiceLocator(),
                        Integer.parseInt(m.get(ID)),
                        m.get(POSITION)
                ))
                .collect(Collectors.toList());
    }

}
