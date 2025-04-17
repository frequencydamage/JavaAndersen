package org.novak.java.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.novak.java.BaseTest;
import org.novak.java.dto.workspaceDto.WorkspaceRequestDTO;
import org.novak.java.service.ReservationService;
import org.novak.java.service.WorkspaceService;

import static org.mockito.Mockito.verify;
import static org.novak.java.model.workspace.WorkspaceType.CABIN;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest extends BaseTest {

    private static final Double WORKSPACE_PRICE = 787.33;
    private static final Integer WORKSPACE_ID = 164;

    @Mock
    private WorkspaceService workspaceService;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private AdminController adminController;

    @DisplayName("Adding workspace should delegate call to WorkspaceService")
    @Test
    void givenValidValues_whenAddWorkspace_thenDelegateToService() {
        WorkspaceRequestDTO expectedWorkspaceDTO = new WorkspaceRequestDTO(WORKSPACE_PRICE, CABIN);
        adminController.addWorkspace(expectedWorkspaceDTO);

        verify(workspaceService).addWorkspace(expectedWorkspaceDTO.getPrice(), expectedWorkspaceDTO.getWorkspaceType());
    }

    @DisplayName("Removing workspace should delegate call to WorkspaceService")
    @Test
    void givenWorkspaceId_whenRemove_thenDelegateToService() {
        adminController.removeWorkspace(WORKSPACE_ID);

        verify(workspaceService).removeWorkspace(WORKSPACE_ID);
    }

    @DisplayName("Listing all workspaces should delegate call to WorkspaceService")
    @Test
    void givenListAllWorkspaces_whenCalled_thenDelegateToService() {
        adminController.listWorkspaces();

        verify(workspaceService).listAllWorkspaces();
    }

    @DisplayName("Listing all reservations should delegate call to ReservationService")
    @Test
    void givenListAllReservations_whenCalled_thenDelegateToService() {
        adminController.listAllReservations();

        verify(reservationService).listAllReservations();
    }
}