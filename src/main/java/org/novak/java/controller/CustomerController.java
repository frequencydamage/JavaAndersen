package org.novak.java.controller;

import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final ReservationService reservationService;

    @Autowired
    public CustomerController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/workspaces/{workspaceId}/reservations")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> makeReservation(@PathVariable("workspaceId") Integer workspaceId) {
        if (workspaceId <= 0) {
            return new ResponseEntity<>("Invalid workspace ID", HttpStatus.BAD_REQUEST);
        }

        try {
            reservationService.makeReservation(workspaceId);
            return new ResponseEntity<>("Reservation successfully created", HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>("Workspace not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable("reservationId") Integer reservationId) {
        if (reservationId <= 0) {
            return new ResponseEntity<>("Invalid workspace ID", HttpStatus.BAD_REQUEST);
        }

        try {
            reservationService.cancelReservation(reservationId);
            return new ResponseEntity<>("Reservation successfully canceled", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/workspaces/available")
    public ResponseEntity<List<Workspace>> listAvailableWorkspaces() {
        List<Workspace> availableWorkspaces = reservationService.listAvailableWorkspaces();
        return new ResponseEntity<>(availableWorkspaces, HttpStatus.OK);
    }

    @GetMapping("/myReservations")
    public ResponseEntity<List<Reservation>> listMyReservations() {
        List<Reservation> myReservations = reservationService.listAllReservations();
        return new ResponseEntity<>(myReservations, HttpStatus.OK);
    }
}