package service;

import workspace.WorkspaceManager;
import reservation.ReservationManager;

public class CustomerWorkspaceService implements CustomerAccess {

    private final ReservationManager reservationManager;

    public CustomerWorkspaceService(WorkspaceManager workspaceManager) {
        this.reservationManager = new ReservationManager(workspaceManager);
    }

    @Override
    public void makeReservation() {
        reservationManager.makeReservation();
    }

    @Override
    public void cancelReservation() {
        reservationManager.cancelReservation();
    }

    @Override
    public void listAvailableWorkspaces() {
        reservationManager.listAvailableWorkspaces();
    }

    @Override
    public void listMyReservations() {
        reservationManager.listMyReservations();
    }
}