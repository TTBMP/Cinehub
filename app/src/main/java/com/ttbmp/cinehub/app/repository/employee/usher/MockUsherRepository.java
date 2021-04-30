package com.ttbmp.cinehub.app.repository.employee.usher;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.creditcard.CreditCardRepository;
import com.ttbmp.cinehub.app.repository.employee.MockEmployeeRepository;
import com.ttbmp.cinehub.app.repository.shift.MockShiftRepository;
import com.ttbmp.cinehub.app.repository.shift.ShiftRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.employee.Usher;
import com.ttbmp.cinehub.domain.shift.UsherShift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockUsherRepository implements UsherRepository {

    private static final List<UsherData> USHER_DATA_LIST = new ArrayList<>();

    static {
        MockEmployeeRepository.getEmployeeDataList().stream()
                .filter(d -> Integer.parseInt(d.getUserId()) % 2 == 0)
                .forEach(d -> USHER_DATA_LIST.add(new UsherData(d.getUserId())));
    }

    private final ServiceLocator serviceLocator;

    public MockUsherRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<UsherData> getUsherDataList() {
        return USHER_DATA_LIST;
    }

    @Override
    public Usher getUsher(UsherShift usherShift) {
        var usherShiftUsherId = MockShiftRepository.getShiftDataList().stream()
                .filter(d -> d.getId() == usherShift.getId())
                .map(MockShiftRepository.ShiftData::getEmployeeId)
                .collect(Collectors.toList())
                .get(0);
        return USHER_DATA_LIST.stream()
                .filter(d -> d.id.equals(usherShiftUsherId))
                .map(d -> new UsherProxy(
                        d.id,
                        serviceLocator.getService(UserRepository.class),
                        serviceLocator.getService(CreditCardRepository.class),
                        serviceLocator.getService(CinemaRepository.class),
                        serviceLocator.getService(ShiftRepository.class)
                ))
                .collect(Collectors.toList())
                .get(0);
    }

    public static class UsherData {

        private String id;

        public UsherData(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }


}
