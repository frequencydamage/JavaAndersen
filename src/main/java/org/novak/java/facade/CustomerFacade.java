package org.novak.java.facade;

import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerFacade {

    private final ReservationService reservationService;

    @Autowired
    public CustomerFacade(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public ResponseEntity<String> makeReservation(Integer workspaceId) {
        if (workspaceId == null || workspaceId <= 0) {
            return new ResponseEntity<>("Invalid workspace ID", HttpStatus.BAD_REQUEST);
        }

        try {
            reservationService.makeReservation(workspaceId);
            return new ResponseEntity<>("Reservation successfully created", HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>("Workspace not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> cancelReservation(Integer reservationId) {
        if (reservationId == null || reservationId <= 0) {
            return new ResponseEntity<>("Invalid reservation ID", HttpStatus.BAD_REQUEST);
        }

        try {
            reservationService.cancelReservation(reservationId);
            return new ResponseEntity<>("Reservation successfully canceled", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Workspace>> getAvailableWorkspaces() {
        List<Workspace> available = reservationService.listAvailableWorkspaces();
        return new ResponseEntity<>(available, HttpStatus.OK);
    }

    public ResponseEntity<List<Reservation>> getMyReservations() {
        List<Reservation> reservations = reservationService.listAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}