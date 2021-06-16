package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MockUserRoleRepository extends MockRepository {

    public static final String ID_USER = "idUser";
    public static final String ID_ROLE = "idRole";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockUserRoleRepository.class);

    static {
        var userDataList = MockUserRepository.getMockDataList();
        userDataList.stream()
                .map(m -> m.get("id"))
                .sorted(Comparator.comparing(String::toLowerCase))
                .limit(1)
                .forEach(id -> mockDataList.addAll(List.of(
                        Map.of(ID_USER, id, ID_ROLE, "1"),
                        Map.of(ID_USER, id, ID_ROLE, "2")
                )));
        userDataList.stream()
                .map(m -> m.get("id"))
                .sorted(Comparator.comparing(String::toLowerCase))
                .skip(1)
                .limit(3)
                .forEach(id -> mockDataList.addAll(List.of(
                        Map.of(ID_USER, id, ID_ROLE, "1")
                )));
        userDataList.stream()
                .map(m -> m.get("id"))
                .sorted(Comparator.comparing(String::toLowerCase))
                .skip(4)
                .limit(4)
                .forEach(id -> mockDataList.addAll(List.of(
                        Map.of(ID_USER, id, ID_ROLE, "1"),
                        Map.of(ID_USER, id, ID_ROLE, "3"),
                        Map.of(ID_USER, id, ID_ROLE, "4")
                )));
        userDataList.stream()
                .map(m -> m.get("id"))
                .sorted(Comparator.comparing(String::toLowerCase))
                .skip(8)
                .forEach(id -> mockDataList.addAll(List.of(
                        Map.of(ID_USER, id, ID_ROLE, "1"),
                        Map.of(ID_USER, id, ID_ROLE, "3"),
                        Map.of(ID_USER, id, ID_ROLE, "5")
                )));
    }

    protected MockUserRoleRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

}
