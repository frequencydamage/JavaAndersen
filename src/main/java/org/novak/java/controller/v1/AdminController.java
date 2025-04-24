package org.novak.java.controller.v1;

import jakarta.validation.Valid;
import org.novak.java.dto.WorkspaceCreateRequestDTO;
import org.novak.java.facade.AdminFacade;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final AdminFacade adminFacade;

    @Autowired
    public AdminController(AdminFacade adminFacade) {
        this.adminFacade = adminFacade;
    }

    @PostMapping("/workspaces")
    public ResponseEntity<String> addWorkspace(@RequestBody @Valid WorkspaceCreateRequestDTO dto) {
        return adminFacade.addWorkspace(dto);
    }

    @DeleteMapping("/workspaces/{id}")
    public ResponseEntity<String> removeWorkspace(@PathVariable("id") Integer workspaceId) {
        return adminFacade.removeWorkspace(workspaceId);
    }

    @GetMapping("/workspaces")
    public ResponseEntity<List<Workspace>> listWorkspaces() {
        return adminFacade.getAllWorkspaces();
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> listAllReservations() {
        return adminFacade.getAllReservations();
    }
}