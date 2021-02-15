package com.ttbmp.cinehub.app.repository.seat;

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
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MockSeatRepository implements SeatRepository {

    private static final List<SeatData> SEAT_DATA_LIST = new ArrayList<>();
    private static int seatIdCounter = 0;

    static {
        List<Integer> hallIdList = MockHallRepository.getHallDataList().stream()
                .map(MockHallRepository.HallData::getId)
                .collect(Collectors.toList());
        for (int hallId : hallIdList) {
            for (char c : new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'L'}) {
                for (int i = 0; i < 7; i++) {
                    SEAT_DATA_LIST.add(new SeatData(seatIdCounter++, 5L + seatIdCounter % 3, seatIdCounter % 2 == 0, hallId, c + String.valueOf(i)));
                }
            }
        }
    }

    public static List<SeatData> getSeatDataList() {
        return SEAT_DATA_LIST;
    }

    @Override
    public List<Seat> getSeatList(Hall hall) {
        return SEAT_DATA_LIST.stream()
                .filter(d -> d.hallId == hall.getId())
                .map(d -> new SeatProxy(d.id, d.price, d.state, d.position))
                .collect(Collectors.toList());
    }

    @Override
    public Seat getSeat(Ticket ticket) {
        int ticketSeatId = MockTicketRepository.getTicketDataList().stream()
                .filter(d -> d.getId() == ticket.getId())
                .map(MockTicketRepository.TicketData::getSeatId)
                .collect(Collectors.toList())
                .get(0);
        return SEAT_DATA_LIST.stream()
                .filter(d -> d.id == ticketSeatId)
                .map(d -> new SeatProxy(d.id, d.price, d.state, d.position))
                .collect(Collectors.toList())
                .get(0);
    }

    public static class SeatData {

        private int id;
        private long price;
        private boolean state;
        private int hallId;
        private String position;

        public SeatData(int id, long price, boolean state, int hallId, String position) {
            this.id = id;
            this.price = price;
            this.state = state;
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

        public long getPrice() {
            return price;
        }

        public void setPrice(long price) {
            this.price = price;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public int getHallId() {
            return hallId;
        }

        public void setHallId(int hallId) {
            this.hallId = hallId;
        }

    }

}
