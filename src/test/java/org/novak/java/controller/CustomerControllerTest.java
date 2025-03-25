package org.novak.java.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.novak.java.service.ReservationService;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private final int WORKSPACE_ID = 993;
    private final int RESERVATION_ID = 261;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private CustomerController customerController;

    @DisplayName("Make reservation should delegate call to ReservationService")
    @Test
    void givenWorkspaceId_whenMakeReservation_thenDelegateToService() {
        customerController.makeReservation(WORKSPACE_ID);

        verify(reservationService).makeReservation(WORKSPACE_ID);
    }

    @DisplayName("Canceling reservation should delegate call to ReservationService")
    @Test
    void givenReservationId_whenCancelReservation_thenDelegateToService() {
        customerController.cancelReservation(RESERVATION_ID);

        verify(reservationService).cancelReservation(RESERVATION_ID);
    }

    @DisplayName("List available workspaces should delegate call to ReservationService")
    @Test
    void whenListAvailableWorkspaces_thenDelegateToService() {
        customerController.listAvailableWorkspaces();

        verify(reservationService).listAvailableWorkspaces();
    }

    @DisplayName("List all reservations should delegate call to ReservationService")
    @Test
    void whenListAllReservations_thenDelegateToService() {
        customerController.listMyReservations();

        verify(reservationService).listAllReservations();
    }
}
