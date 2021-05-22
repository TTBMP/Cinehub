package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.security.Role;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockUserRepository implements UserRepository {

    private static final List<UserData> USER_DATA_LIST = new ArrayList<>();
    private static final List<RoleData> ROLE_DATA_LIST = new ArrayList<>();
    private static final List<UserRoleData> USER_ROLE_DATA_LIST = new ArrayList<>();

    static {
        USER_DATA_LIST.add(new UserData("T8SP2uHYdHZfBk6uh3fJ356Sji52", "Fabio", "Buracchi", "fb@cinehub.com"));
        USER_DATA_LIST.add(new UserData("gVUYDMojhmeFkErlbF0WWLQWMPn1", "Massimo", "Mazzetti", "mm@cinehub.com"));
        USER_DATA_LIST.add(new UserData("vLmLJCuTwZZap4t4ngUclwUzwZ62", "Ivan", "Palmieri", "ip@cinehub.com"));
        USER_DATA_LIST.add(new UserData("bvMrqf60V8OwETW9aEjeYbM9I0b2", "Mario", "Rossi", "mr@cinehub.com"));
        USER_DATA_LIST.add(new UserData("JFDW7zA7pVh53im1Sl7fSnvaxML2", "Luigi", "Bianchi", "lb@cinehub.com"));
        USER_DATA_LIST.add(new UserData("mg9eBHkPumcssl9dvrotbZqDbk62", "Steve", "Jobs", "sj@cinehub.com"));
        USER_DATA_LIST.add(new UserData("C9o3hGHcfZXEXAjtJtlUEckE9WC2", "Bill", "Gates", "bg@cinehub.com"));
        USER_DATA_LIST.add(new UserData("Tf6NcK4d8feY2TpuJVsqS74pbLf1", "Elon", "Musk", "em@cinehub.com"));
        USER_DATA_LIST.add(new UserData("ikBgUbCQYlevLnLj7SOUb1PvS0h2", "Alan", "Turing", "at@cinehub.com"));
        USER_DATA_LIST.add(new UserData("7jYsjrrXeFSUpu33TsdYwV135mx1", "James", "Gosling", "jg@cinehub.com"));
        USER_DATA_LIST.add(new UserData("L02YH8zWNJXzXcwRIJSDyx3GOqC3", "Dennis", "Ritchie", "dr@cinehub.com"));
        USER_DATA_LIST.add(new UserData("qTNkqPTioQO3cv953AqC3NR5NDf2", "Larry", "Page", "lp@cinehub.com"));
        USER_DATA_LIST.add(new UserData("5KClU7hbNgedJAwLuF9eFVl6Qzz2", "Mark", "Zuckerberg", "mz@cinehub.com"));
        USER_DATA_LIST.add(new UserData("ppgJVL8wS9bdjWxCxs6bll2K0Xs1", "Jeff", "Bezos", "jb@cinehub.com"));
        ROLE_DATA_LIST.add(new RoleData(1, "customer"));
        ROLE_DATA_LIST.add(new RoleData(2, "manager"));
        ROLE_DATA_LIST.add(new RoleData(3, "employee"));
        ROLE_DATA_LIST.add(new RoleData(4, "usher"));
        ROLE_DATA_LIST.add(new RoleData(5, "projectionist"));
        USER_DATA_LIST.stream()
                .sorted(Comparator.comparing(d -> d.id.toLowerCase()))
                .limit(1)
                .forEach(d -> {
                    USER_ROLE_DATA_LIST.add(new UserRoleData(d.id, 1));
                    USER_ROLE_DATA_LIST.add(new UserRoleData(d.id, 2));
                });
        USER_DATA_LIST.stream()
                .sorted(Comparator.comparing(d -> d.id.toLowerCase()))
                .skip(1)
                .limit(3)
                .forEach(d -> USER_ROLE_DATA_LIST.add(new UserRoleData(d.id, 1)));
        USER_DATA_LIST.stream()
                .sorted(Comparator.comparing(d -> d.id.toLowerCase()))
                .skip(4)
                .limit(4)
                .forEach(d -> {
                    USER_ROLE_DATA_LIST.add(new UserRoleData(d.id, 1));
                    USER_ROLE_DATA_LIST.add(new UserRoleData(d.id, 3));
                    USER_ROLE_DATA_LIST.add(new UserRoleData(d.id, 4));
                });
        USER_DATA_LIST.stream()
                .sorted(Comparator.comparing(d -> d.id.toLowerCase()))
                .skip(8)
                .forEach(d -> {
                    USER_ROLE_DATA_LIST.add(new UserRoleData(d.id, 1));
                    USER_ROLE_DATA_LIST.add(new UserRoleData(d.id, 3));
                    USER_ROLE_DATA_LIST.add(new UserRoleData(d.id, 5));
                });
    }

    public static List<UserData> getUserDataList() {
        return USER_DATA_LIST;
    }
    public static List<RoleData> getRoleDataList() {
        return ROLE_DATA_LIST;
    }
    public static List<UserRoleData> getUserRoleDataList() {
        return USER_ROLE_DATA_LIST;
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
                        getRoleList(d.id)
                ))
                .findAny()
                .orElse(null);
    }

    private List<Role> getRoleList(String userId) {
        return ROLE_DATA_LIST.stream()
                .filter(roleData -> USER_ROLE_DATA_LIST.stream()
                        .filter(userRoleData -> userRoleData.idUser.equals(userId))
                        .map(userRoleData -> userRoleData.idRole)
                        .collect(Collectors.toList())
                        .contains(roleData.id)
                )
                .map(roleData -> {
                    switch (roleData.name) {
                        case "customer":
                            return Role.CUSTOMER_ROLE;
                        case "manager":
                            return Role.MANAGER_ROLE;
                        case "employee":
                            return Role.EMPLOYEE_ROLE;
                        case "usher":
                            return Role.USHER_ROLE;
                        case "projectionist":
                            return Role.PROJECTIONIST_ROLE;
                        default:
                            throw new IllegalStateException("Unexpected value: " + roleData.name);
                    }
                })
                .collect(Collectors.toList());
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

    public static class RoleData {

        private int id;
        private String name;

        public RoleData(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public static class UserRoleData {

        private String idUser;
        private int idRole;

        public UserRoleData(String idUser, int idRole) {
            this.idUser = idUser;
            this.idRole = idRole;
        }

        public String getIdUser() {
            return idUser;
        }

        public void setIdUser(String idUser) {
            this.idUser = idUser;
        }

        public int getIdRole() {
            return idRole;
        }

        public void setIdRole(int idRole) {
            this.idRole = idRole;
        }

    }

}
