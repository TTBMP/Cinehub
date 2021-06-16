package com.ttbmp.cinehub.app.utilities.repository;

import com.ttbmp.cinehub.app.di.ServiceLocator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MockRepository extends Repository {

    private static final Map<Class<? extends MockRepository>, List<Map<String, String>>> mockDataMap = new HashMap<>();

    protected MockRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public static List<Map<String, String>> getMockDataList(Class<? extends MockRepository> repositoryClass) {
        mockDataMap.putIfAbsent(repositoryClass, new ArrayList<>());
        return mockDataMap.get(repositoryClass);
    }

}
