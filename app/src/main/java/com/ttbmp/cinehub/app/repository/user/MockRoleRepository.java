package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;

import java.util.List;
import java.util.Map;

public class MockRoleRepository extends MockRepository {

    public static final String ID = "id";
    public static final String NAME = "name";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockRoleRepository.class);

    static {
        mockDataList.addAll(List.of(
                Map.of(ID, "1", NAME, "customer"),
                Map.of(ID, "2", NAME, "manager"),
                Map.of(ID, "3", NAME, "employee"),
                Map.of(ID, "4", NAME, "usher"),
                Map.of(ID, "5", NAME, "projectionist")
        ));
    }

    protected MockRoleRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

}
