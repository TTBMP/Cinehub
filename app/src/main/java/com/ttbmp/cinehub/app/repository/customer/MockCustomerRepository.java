package com.ttbmp.cinehub.app.repository.customer;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.ticket.MockTicketRepository;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Fabio Buracchi
 */
public class MockCustomerRepository extends MockUserRepository implements CustomerRepository {

    public static final String ID = "id";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockCustomerRepository.class);

    static {
        MockUserRepository.getMockDataList().stream()
                .map(m -> m.get(MockUserRepository.ID))
                .filter(userId -> MockUserRepository.getMockDataList().stream()
                        .filter(m -> m.get(MockUserRepository.ID).equals(userId))
                        .findAny()
                        .map(m -> MockUserRepository.getRoleList(userId))
                        .orElse(new ArrayList<>())
                        .contains(Role.CUSTOMER_ROLE)
                )
                .forEach(userId -> mockDataList.add(Map.of(ID, userId)));
    }

    public MockCustomerRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

    @Override
    public Customer getCustomer(String customerId) throws RepositoryException {
        return mockDataList.stream()
                .filter(m -> m.get(ID).equals(customerId))
                .findAny()
                .map(m -> new CustomerProxy(getServiceLocator(), m.get(ID)))
                .orElse(null);
    }

    @Override
    public Customer getCustomer(Ticket ticket) throws RepositoryException {
        return MockTicketRepository.getMockDataList().stream()
                .filter(m -> m.get(MockTicketRepository.ID).equals(Integer.toString(ticket.getId())))
                .map(m -> m.get(MockTicketRepository.USER_ID))
                .findAny()
                .flatMap(ticketUserId -> mockDataList.stream()
                        .filter(m -> m.get(ID).equals(ticketUserId))
                        .findAny()
                        .map(m -> new CustomerProxy(getServiceLocator(), m.get(ID)))
                )
                .orElse(null);
    }

}
