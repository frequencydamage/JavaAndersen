package org.novak.java.controller;

import jakarta.transaction.Transactional;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.model.workspace.WorkspaceType;
import org.novak.java.service.ReservationService;
import org.novak.java.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdminController {

    private WorkspaceService workspaceService;
    private ReservationService reservationService;

    @Autowired
    public AdminController(WorkspaceService workspaceService, ReservationService reservationService) {
        this.workspaceService = workspaceService;
        this.reservationService = reservationService;
    }

    @Transactional
    public void addWorkspace(Double price, WorkspaceType workspaceType) {
        workspaceService.addWorkspace(price, workspaceType);
    }

    @Transactional
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