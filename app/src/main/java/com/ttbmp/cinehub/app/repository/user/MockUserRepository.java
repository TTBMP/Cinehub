package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.creditcard.CreditCardRepository;
import com.ttbmp.cinehub.app.repository.ticket.MockTicketRepository;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MockUserRepository implements UserRepository {

    private static final List<UserData> USER_DATA_LIST = new ArrayList<>();

    static {
        USER_DATA_LIST.add(new UserData("0", "Fabio", "Buracchi", "fb@cinehub.com"));
        USER_DATA_LIST.add(new UserData("1", "Massimo", "Mazzetti", "mm@cinehub.com"));
        USER_DATA_LIST.add(new UserData("2", "Ivan", "Palmieri", "ip@cinehub.com"));
        USER_DATA_LIST.add(new UserData("3", "Mario", "Rossi", "mr@cinehub.com"));
    }

    private final ServiceLocator serviceLocator;

    public MockUserRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public User getUser(String userId) {
        return USER_DATA_LIST.stream()
                .filter(d -> d.id.equals(userId))
                .map(d -> new UserProxy(
                        d.id,
                        d.name,
                        d.surname,
                        d.email,
                        serviceLocator.getService(CreditCardRepository.class)
                ))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public User getUser(Ticket ticket) {
        String ticketUserId = MockTicketRepository.getTicketDataList().stream()
                .filter(d -> d.getId() == ticket.getId())
                .map(MockTicketRepository.TicketData::getUserId)
                .collect(Collectors.toList())
                .get(0);
        return USER_DATA_LIST.stream()
                .filter(d -> d.id.equals(ticketUserId))
                .map(d -> new UserProxy(
                        d.id,
                        d.name,
                        d.surname,
                        d.email,
                        serviceLocator.getService(CreditCardRepository.class)
                ))
                .collect(Collectors.toList())
                .get(0);
    }

    public static class UserData {

        private String id;
        private String name;
        private String surname;
        private String email;

        public UserData(String id, String name, String surname, String email) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }

}
