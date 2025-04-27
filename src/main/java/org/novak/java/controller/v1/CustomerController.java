package org.novak.java.controller.v1;

import org.novak.java.facade.CustomerFacade;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerFacade customerFacade;

    @Autowired
    public CustomerController(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @PostMapping("/workspaces/{workspaceId}/reservations")
    public ResponseEntity<String> makeReservation(@PathVariable("workspaceId") Integer workspaceId) {
        return customerFacade.makeReservation(workspaceId);
    }

    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable("reservationId") Integer reservationId) {
        return customerFacade.cancelReservation(reservationId);
    }

    @GetMapping("/workspaces/available")
    public ResponseEntity<List<Workspace>> listAvailableWorkspaces() {
        return customerFacade.getAvailableWorkspaces();
    }

    @GetMapping("/myReservations")
    public ResponseEntity<List<Reservation>> listMyReservations() {
        return customerFacade.getMyReservations();
    }
}