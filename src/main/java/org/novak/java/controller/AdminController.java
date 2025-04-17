package org.novak.java.controller;

import jakarta.validation.Valid;
import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.dto.workspaceDto.WorkspaceRequestDTO;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.service.ReservationService;
import org.novak.java.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final WorkspaceService workspaceService;
    private final ReservationService reservationService;

    @Autowired
    public AdminController(WorkspaceService workspaceService, ReservationService reservationService) {
        this.workspaceService = workspaceService;
        this.reservationService = reservationService;
    }

    @PostMapping("/workspaces")
    public ResponseEntity<String> addWorkspace(@RequestBody @Valid WorkspaceRequestDTO dto) {
        workspaceService.addWorkspace(dto.getPrice(), dto.getWorkspaceType());
        return new ResponseEntity<>("Workspace successfully added", HttpStatus.CREATED);
    }

    @DeleteMapping("/workspaces/{id}")
    public ResponseEntity<String> removeWorkspace(@PathVariable("id") Integer workspaceId) {
        if (workspaceId <= 0) {
            return new ResponseEntity<>("Invalid workspace ID", HttpStatus.BAD_REQUEST);
        }

        try {
            workspaceService.removeWorkspace(workspaceId);
            return new ResponseEntity<>("Workspace successfully removed", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>("Workspace with not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/workspaces")
    public ResponseEntity<List<Workspace>> listWorkspaces() {
        List<Workspace> workspaces = workspaceService.listAllWorkspaces();

        return new ResponseEntity<>(workspaces, HttpStatus.OK);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> listAllReservations() {
        List<Reservation> reservations = reservationService.listAllReservations();

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}