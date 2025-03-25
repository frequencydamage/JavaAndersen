package org.novak.java.controller;

import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.model.workspace.WorkspaceType;
import org.novak.java.service.ReservationService;
import org.novak.java.service.WorkspaceService;

import java.util.List;

public class AdminController {

    private ReservationService reservationService = new ReservationService();
    private WorkspaceService workspaceService = new WorkspaceService();

    public void addWorkspace(Double price, WorkspaceType workspaceType) {
        workspaceService.addWorkspace(price, workspaceType);
    }

    public void removeWorkspace(Integer workspaceId) {
        workspaceService.removeWorkspace(workspaceId);
    }

    public List<Workspace> listWorkspaces() {
        return workspaceService.listAllWorkspaces();
    }

    public List<Reservation> listAllReservations() {
        return reservationService.listAllReservations();
    }
}
