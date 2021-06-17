package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.security.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class MockUserRepository extends MockRepository implements UserRepository {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String EMAIL = "email";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockUserRepository.class);

    static {
        mockDataList.addAll(new ArrayList<>(List.of(
                new HashMap<>(Map.of(ID, "T8SP2uHYdHZfBk6uh3fJ356Sji52", NAME, "Fabio", SURNAME, "Buracchi", EMAIL, "fb@cinehub.com")),
                new HashMap<>(Map.of(ID, "gVUYDMojhmeFkErlbF0WWLQWMPn1", NAME, "Massimo", SURNAME, "Mazzetti", EMAIL, "mm@cinehub.com")),
                new HashMap<>(Map.of(ID, "vLmLJCuTwZZap4t4ngUclwUzwZ62", NAME, "Ivan", SURNAME, "Palmieri", EMAIL, "ip@cinehub.com")),
                new HashMap<>(Map.of(ID, "bvMrqf60V8OwETW9aEjeYbM9I0b2", NAME, "Mario", SURNAME, "Rossi", EMAIL, "mr@cinehub.com")),
                new HashMap<>(Map.of(ID, "JFDW7zA7pVh53im1Sl7fSnvaxML2", NAME, "Luigi", SURNAME, "Bianchi", EMAIL, "lb@cinehub.com")),
                new HashMap<>(Map.of(ID, "mg9eBHkPumcssl9dvrotbZqDbk62", NAME, "Steve", SURNAME, "Jobs", EMAIL, "sj@cinehub.com")),
                new HashMap<>(Map.of(ID, "C9o3hGHcfZXEXAjtJtlUEckE9WC2", NAME, "Bill", SURNAME, "Gates", EMAIL, "bg@cinehub.com")),
                new HashMap<>(Map.of(ID, "Tf6NcK4d8feY2TpuJVsqS74pbLf1", NAME, "Elon", SURNAME, "Musk", EMAIL, "em@cinehub.com")),
                new HashMap<>(Map.of(ID, "ikBgUbCQYlevLnLj7SOUb1PvS0h2", NAME, "Alan", SURNAME, "Turing", EMAIL, "at@cinehub.com")),
                new HashMap<>(Map.of(ID, "7jYsjrrXeFSUpu33TsdYwV135mx1", NAME, "James", SURNAME, "Gosling", EMAIL, "jg@cinehub.com")),
                new HashMap<>(Map.of(ID, "L02YH8zWNJXzXcwRIJSDyx3GOqC3", NAME, "Dennis", SURNAME, "Ritchie", EMAIL, "dr@cinehub.com")),
                new HashMap<>(Map.of(ID, "qTNkqPTioQO3cv953AqC3NR5NDf2", NAME, "Larry", SURNAME, "Page", EMAIL, "lp@cinehub.com")),
                new HashMap<>(Map.of(ID, "5KClU7hbNgedJAwLuF9eFVl6Qzz2", NAME, "Mark", SURNAME, "Zuckerberg", EMAIL, "mz@cinehub.com")),
                new HashMap<>(Map.of(ID, "ppgJVL8wS9bdjWxCxs6bll2K0Xs1", NAME, "Jeff", SURNAME, "Bezos", EMAIL, "jb@cinehub.com"))
        )));
    }

    public MockUserRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

    public static List<Role> getRoleList(String id) {
        return MockRoleRepository.getMockDataList().stream()
                .map(m -> m.get(MockRoleRepository.ID))
                .filter(idRole -> MockUserRoleRepository.getMockDataList().stream()
                        .filter(m -> m.get(MockUserRoleRepository.ID_USER).equals(id))
                        .map(m -> m.get(MockUserRoleRepository.ID_ROLE))
                        .collect(Collectors.toList())
                        .contains(idRole)
                )
                .map(MockUserRepository::getRole)
                .collect(Collectors.toList());
    }

    private static Role getRole(String roleId) {
        var roleName = MockRoleRepository.getMockDataList().stream()
                .filter(m -> m.get(MockRoleRepository.ID).equals(roleId))
                .map(m -> m.get(MockRoleRepository.NAME))
                .findAny()
                .orElse("Role not found.");
        switch (roleName) {
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
                throw new IllegalStateException("Unexpected value: " + roleName);
        }
    }

    @Override
    public User getUser(String userId) {
        return mockDataList.stream()
                .filter(m -> m.get(ID).equals(userId))
                .map(m -> new UserProxy(
                        getServiceLocator(),
                        m.get(ID),
                        m.get(NAME),
                        m.get(SURNAME),
                        m.get(EMAIL),
                        getRoleList(m.get(ID))
                ))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<User> getAllUser() throws RepositoryException {
        return mockDataList.stream()
                .map(m -> new UserProxy(
                        getServiceLocator(),
                        m.get(ID),
                        m.get(NAME),
                        m.get(SURNAME),
                        m.get(EMAIL),
                        getRoleList(m.get(ID))
                ))
                .collect(Collectors.toList());
    }

}
