package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.app.repository.ticket.MockTicketRepository;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockSeatRepository implements SeatRepository {

    private static final List<SeatData> SEAT_DATA_LIST = new ArrayList<>();
    private static int seatIdCounter = 1;

    static {
        var hallIdList = MockHallRepository.getHallDataList().stream()
                .map(MockHallRepository.HallData::getId)
                .collect(Collectors.toList());
        for (var hallId : hallIdList) {
            for (var i = 0; i < 7; i++) {
                SEAT_DATA_LIST.add(new SeatData(seatIdCounter++, hallId, "A" + (hallId + i) % 7));
                SEAT_DATA_LIST.add(new SeatData(seatIdCounter++, hallId, "B" + (hallId + i) % 7));
                SEAT_DATA_LIST.add(new SeatData(seatIdCounter++, hallId, "C" + (hallId + i) % 7));
                SEAT_DATA_LIST.add(new SeatData(seatIdCounter++, hallId, "D" + (hallId + i) % 7));
                SEAT_DATA_LIST.add(new SeatData(seatIdCounter++, hallId, "E" + (hallId + i) % 7));
                SEAT_DATA_LIST.add(new SeatData(seatIdCounter++, hallId, "F" + (hallId + i) % 7));
            }
        }
    }

    private final ServiceLocator serviceLocator;

    public MockSeatRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<SeatData> getSeatDataList() {
        return SEAT_DATA_LIST;
    }

    @Override
    public Seat getSeat(int id) throws RepositoryException {
        return SEAT_DATA_LIST.stream()
                .filter(d -> d.getId() == id)
                .findAny()
                .map(d -> new SeatProxy(serviceLocator, d.id, d.position))
                .orElse(null);
    }

    @Override
    public Seat getSeat(Ticket ticket) {
        return MockTicketRepository.getTicketDataList().stream()
                .filter(d -> d.getId() == ticket.getId())
                .map(MockTicketRepository.TicketData::getSeatId)
                .findAny()
                .flatMap(ticketSeatId -> SEAT_DATA_LIST.stream()
                        .filter(d -> d.id == ticketSeatId)
                        .findAny()
                        .map(d -> new SeatProxy(serviceLocator, d.id, d.position))
                )
                .orElse(null);
    }

    @Override
    public List<Seat> getSeatList(Hall hall) {
        return SEAT_DATA_LIST.stream()
                .filter(d -> d.hallId == hall.getId())
                .map(d -> new SeatProxy(serviceLocator, d.id, d.position))
                .collect(Collectors.toList());
    }

    public static class SeatData {

        private int id;
        private int hallId;
        private String position;

        public SeatData(int id, int hallId, String position) {
            this.id = id;
            this.hallId = hallId;
            this.position = position;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHallId() {
            return hallId;
        }

        public void setHallId(int hallId) {
            this.hallId = hallId;
        }

    }

}
