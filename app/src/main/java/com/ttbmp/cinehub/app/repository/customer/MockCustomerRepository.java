package com.ttbmp.cinehub.app.repository.customer;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.ticket.MockTicketRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class MockCustomerRepository implements CustomerRepository {

    private static final List<CustomerData> CUSTOMER_DATA_LIST = new ArrayList<>();

    static {
        MockUserRepository.getUserDataList().forEach(d -> CUSTOMER_DATA_LIST.add(new CustomerData(d.getId())));
    }

    private final ServiceLocator serviceLocator;

    public MockCustomerRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<CustomerData> getUserDataList() {
        return CUSTOMER_DATA_LIST;
    }

    @Override
    public Customer getCustomer(String userId) {
        return MockUserRepository.getUserDataList().stream()
                .filter(d -> d.getId().equals(userId))
                .findAny()
                .map(d -> new CustomerProxy(
                        d.getId(),
                        serviceLocator.getService(UserRepository.class),
                        serviceLocator.getService(TicketRepository.class)
                ))
                .orElse(null);
    }

    @Override
    public Customer getCustomer(Ticket ticket) {
        return MockTicketRepository.getTicketDataList().stream()
                .filter(d -> d.getId() == ticket.getId())
                .map(MockTicketRepository.TicketData::getUserId)
                .findAny()
                .flatMap(ticketUserId -> MockUserRepository.getUserDataList().stream()
                        .filter(d -> d.getId().equals(ticketUserId))
                        .findAny()
                        .map(d -> new CustomerProxy(
                                d.getId(),
                                serviceLocator.getService(UserRepository.class),
                                serviceLocator.getService(TicketRepository.class)
                        )))
                .orElse(null);
    }

    public static class CustomerData {

        private String userId;

        public CustomerData(String userId) {
            this.userId = userId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

    }

}
