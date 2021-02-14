package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.app.repository.hall.MockHallRepository;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockSeatRepository implements SeatRepository {

    private static final List<SeatEntity> SEAT_ENTITY_LIST = new ArrayList<>();
    private static int seatIdCounter = 0;

    static {
        List<Integer> hallIdList = MockHallRepository.getHallDataList().stream()
                .map(MockHallRepository.HallData::getId)
                .collect(Collectors.toList());
        for (int hallId : hallIdList) {
            for (int i = 0; i < 50 + 10 * (seatIdCounter % 3); i++, seatIdCounter++) {
                SEAT_ENTITY_LIST.add(new SeatEntity(seatIdCounter, 5L + seatIdCounter % 3, seatIdCounter % 2 == 0, hallId));
            }
        }
    }

    public static List<SeatEntity> getSeatEntityList() {
        return SEAT_ENTITY_LIST;
    }

    @Override
    public List<Seat> getSeatList(Hall hall) {
        return SEAT_ENTITY_LIST.stream()
                .filter(e -> e.hallId == hall.getId())
                .map(e -> new SeatProxy(e.id, e.price, e.state))
                .collect(Collectors.toList());
    }

    public static class SeatEntity {

        private int id;
        private long price;
        private boolean state;
        private int hallId;

        public SeatEntity(int id, long price, boolean state, int hallId) {
            this.id = id;
            this.price = price;
            this.state = state;
            this.hallId = hallId;
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
