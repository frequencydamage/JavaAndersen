package service;

import reservation.ReservationManager;
import workspace.WorkspaceManager;

public class AdminWorkspaceService implements AdminAccess {

    private final WorkspaceManager workspaceManager;
    private final ReservationManager reservationManager;

    public AdminWorkspaceService(WorkspaceManager workspaceManager) {
        this.workspaceManager = workspaceManager;
        this.reservationManager = new ReservationManager(workspaceManager);
    }

    @Override
    public void addWorkspace() {
        workspaceManager.addWorkspace();
    }

    @Override
    public void removeWorkspace() {
        workspaceManager.removeWorkspace();
    }

    @Override
    public void listWorkspaces() {
        workspaceManager.listAllWorkspaces();
    }

    @Override
    public void listAllReservations() {
        reservationManager.listMyReservations();
    }
}
