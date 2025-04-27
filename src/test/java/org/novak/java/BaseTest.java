package org.novak.java;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;


import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public abstract class BaseTest {

    private MockedStatic<Persistence> persistenceMock;

    @BeforeEach
    void setUp() {
        persistenceMock = mockStatic(Persistence.class);
        EntityManagerFactory entityManagerFactoryMock = mock(EntityManagerFactory.class);

        given(Persistence.createEntityManagerFactory(anyString(), anyMap())).willReturn(entityManagerFactoryMock);
        given(Persistence.createEntityManagerFactory(anyString())).willReturn(entityManagerFactoryMock);
    }

    @AfterEach
    void tearDown() {
        persistenceMock.close();
    }
}
