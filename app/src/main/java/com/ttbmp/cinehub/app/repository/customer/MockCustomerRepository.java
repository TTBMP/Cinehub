package com.ttbmp.cinehub.app.repository.customer;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.creditcard.CreditCardRepository;
import com.ttbmp.cinehub.app.repository.ticket.MockTicketRepository;
import com.ttbmp.cinehub.app.repository.user.MockUserRepository;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockCustomerRepository implements CustomerRepository {

    private static final List<CustomerData> CUSTOMER_DATA_LIST = new ArrayList<>();

    static {
        CUSTOMER_DATA_LIST.add(new CustomerData("0"));
        CUSTOMER_DATA_LIST.add(new CustomerData("1"));
        CUSTOMER_DATA_LIST.add(new CustomerData("2"));
        CUSTOMER_DATA_LIST.add(new CustomerData("3"));
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
                .map(d -> new CustomerProxy(
                        d.getId(),
                        d.getName(),
                        d.getSurname(),
                        d.getEmail(),
                        serviceLocator.getService(CreditCardRepository.class)
                ))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public Customer getCustomer(Ticket ticket) {
        String ticketUserId = MockTicketRepository.getTicketDataList().stream()
                .filter(d -> d.getId() == ticket.getId())
                .map(MockTicketRepository.TicketData::getUserId)
                .collect(Collectors.toList())
                .get(0);
        return MockUserRepository.getUserDataList().stream()
                .filter(d -> d.getId().equals(ticketUserId))
                .map(d -> new CustomerProxy(
                        d.getId(),
                        d.getName(),
                        d.getSurname(),
                        d.getEmail(),
                        serviceLocator.getService(CreditCardRepository.class)
                ))
                .collect(Collectors.toList())
                .get(0);
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
