package org.novak.java.facade;

import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.dto.WorkspaceCreateRequestDTO;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.service.ReservationService;
import org.novak.java.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminFacade {

    private final WorkspaceService workspaceService;
    private final ReservationService reservationService;

    @Autowired
    public AdminFacade(WorkspaceService workspaceService, ReservationService reservationService) {
        this.workspaceService = workspaceService;
        this.reservationService = reservationService;
    }

    public ResponseEntity<String> addWorkspace(WorkspaceCreateRequestDTO dto) {
        workspaceService.addWorkspace(dto.getPrice(), dto.getWorkspaceType());

        return ResponseEntity.status(HttpStatus.CREATED).body("Workspace successfully added");
    }

    public ResponseEntity<String> removeWorkspace(Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid workspace ID");
        }

        try {
            workspaceService.removeWorkspace(id);
            return ResponseEntity.ok("Workspace successfully removed");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workspace not found");
        }
    }

    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        return ResponseEntity.ok(workspaceService.listAllWorkspaces());
    }

    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.listAllReservations());
    }
}