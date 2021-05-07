package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockUserRepository implements UserRepository {

    private static final List<UserData> USER_DATA_LIST = new ArrayList<>();

    static {
        USER_DATA_LIST.add(new UserData("", "Guest", "", "guest@cinehub.com", "guest"));
        USER_DATA_LIST.add(new UserData("0", "Fabio", "Buracchi", "fb@cinehub.com", "employee;customer"));
        USER_DATA_LIST.add(new UserData("1", "Massimo", "Mazzetti", "mm@cinehub.com", "projectionist;employee;customer"));
        USER_DATA_LIST.add(new UserData("2", "Ivan", "Palmieri", "ip@cinehub.com", "usher;employee;customer"));
        USER_DATA_LIST.add(new UserData("3", "Mario", "Rossi", "mr@cinehub.com", "projectionist;employee;customer"));
        USER_DATA_LIST.add(new UserData("4", "Luigi", "Bianchi", "lb@cinehub.com", "customer"));
        USER_DATA_LIST.add(new UserData("5", "Giovanni", "Giorgio", "gg@cinehub.com", "manager;customer"));
    }

    private final ServiceLocator serviceLocator;

    public MockUserRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public static List<UserData> getUserDataList() {
        return USER_DATA_LIST;
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
                        d.role)
                )
                .collect(Collectors.toList())
                .get(0);
    }

    public static class UserData {

        private String id;
        private String name;
        private String surname;
        private String email;
        private String role;

        public UserData(String id, String name, String surname, String email, String role) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.role = role;
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

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

    }

}
