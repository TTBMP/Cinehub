package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.utilities.repository.MockRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockRoleRepository extends MockRepository {

    public static final String ID = "id";
    public static final String NAME = "name";

    private static final List<Map<String, String>> mockDataList = getMockDataList(MockRoleRepository.class);

    static {
        mockDataList.addAll(new ArrayList<>(List.of(
                new HashMap<>(Map.of(ID, "1", NAME, "customer")),
                new HashMap<>(Map.of(ID, "2", NAME, "manager")),
                new HashMap<>(Map.of(ID, "3", NAME, "employee")),
                new HashMap<>(Map.of(ID, "4", NAME, "usher")),
                new HashMap<>(Map.of(ID, "5", NAME, "projectionist"))
        )));
    }

    protected MockRoleRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList() {
        return mockDataList;
    }

}
