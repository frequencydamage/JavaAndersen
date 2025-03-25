package org.novak.java.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.reservation.ReservationRepository;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.model.workspace.WorkspaceRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.novak.java.model.workspace.WorkspaceType.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    private final Workspace testWorkspace = new Workspace(567, 987.65, PRIVATE, true);
    private final Integer RESERVATION_ID = 163;

    @Mock
    private WorkspaceRepository workspaceRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @DisplayName("Creating a reservation")
    @Test
    void givenValidReservation_whenAdded_thenCreatedInRepository() {
        given(workspaceRepository.getById(any(Integer.class))).willReturn(testWorkspace);
        ArgumentCaptor<Reservation> reservationCaptor = ArgumentCaptor.forClass(Reservation.class);

        reservationService.makeReservation(testWorkspace.getId());

        verify(reservationRepository).create(reservationCaptor.capture());
        verify(workspaceRepository).updateAvailabilityById(testWorkspace.getId(), false);

        Reservation actualReservation = reservationCaptor.getValue();
        Reservation expectedReservation = new Reservation(testWorkspace.getId(), 0, testWorkspace.getWorkspaceType());

        assertThat(actualReservation.getReservationId()).isGreaterThan(0);
        assertThat(actualReservation)
                .usingRecursiveComparison()
                .ignoringFields("reservationId")
                .isEqualTo(expectedReservation);
    }

    @DisplayName("Successfully cancels an existing reservation and updates workspace availability status")
    @Test
    void givenExistingReservation_whenCanceled_thenReservationRemoved_andAvailabilityUpdated() {
        Reservation reservation = new Reservation(testWorkspace.getId(), RESERVATION_ID,
                testWorkspace.getWorkspaceType());
        given(reservationRepository.getById(any(Integer.class))).willReturn(reservation);

        reservationService.cancelReservation(RESERVATION_ID);

        verify(reservationRepository).deleteById(reservation.getReservationId());
        verify(workspaceRepository).updateAvailabilityById(reservation.getWorksSpaceId(), true);
    }

    @DisplayName("Throws exception when canceling non-existing reservation by ID")
    @Test
    void givenNonExistingReservation_whenCanceled_thenResourceNotFoundExceptionThrown() {
        given(reservationRepository.getById(any(Integer.class))).willReturn(null);

        assertThrowsExactly(ResourceNotFoundException.class,
                () -> reservationService.cancelReservation(RESERVATION_ID));
        verify(reservationRepository, never()).deleteById(anyInt());
        verify(workspaceRepository, never()).updateAvailabilityById(anyInt(), anyBoolean());
    }

    @DisplayName("Listing available workspaces")
    @Test
    void givenAvailableWorkspaces_whenListAvailable_thenReturnsAvailableWorkspaces() {
        List<Workspace> expectedWorkspaces = List.of(
                new Workspace(1, 1499.99, OPEN_SPACE, true),
                new Workspace(999, 1.0, CABIN, true),
                new Workspace(657, 125.55, PRIVATE, true)
        );
        given(workspaceRepository.getAllAvailable()).willReturn(expectedWorkspaces);

        List<Workspace> actualWorkspaces = reservationService.listAvailableWorkspaces();

        verify(workspaceRepository).getAllAvailable();
        assertThat(actualWorkspaces).containsExactlyInAnyOrderElementsOf(expectedWorkspaces);
    }

    @DisplayName("Listing no available workspaces")
    @Test
    void givenNoAvailableWorkspaces_whenListAvailable_thenReturnsEmptyList() {
        given(workspaceRepository.getAllAvailable()).willReturn(Collections.emptyList());

        List<Workspace> actualWorkspaces = reservationService.listAvailableWorkspaces();

        verify(workspaceRepository).getAllAvailable();
        assertThat(actualWorkspaces).isEmpty();
    }

    @DisplayName("Listing made reservations")
    @Test
    void givenExistingReservations_whenListAll_thenReturnsAllReservations() {
        List<Reservation> expectedReservations = List.of(
                new Reservation(123, 555, OPEN_SPACE),
                new Reservation(321, 932, CABIN),
                new Reservation(369, 963, PRIVATE)
        );
        given(reservationRepository.getAll()).willReturn(expectedReservations);

        List<Reservation> actualReservations = reservationService.listAllReservations();

        verify(reservationRepository).getAll();
        assertThat(actualReservations).containsExactlyInAnyOrderElementsOf(expectedReservations);
    }

    @DisplayName("Listing no reservations")
    @Test
    void givenNoReservations_whenListAll_thenReturnsEmptyList() {
        given(reservationRepository.getAll()).willReturn(Collections.emptyList());

        List<Reservation> actualReservations = reservationService.listAllReservations();

        verify(reservationRepository).getAll();
        assertThat(actualReservations).isEmpty();
    }
}

