package org.novak.java.controller;

import jakarta.transaction.Transactional;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerController {

    private ReservationService reservationService;

    @Autowired
    public CustomerController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Transactional
    public void makeReservation(Integer workspaceId) {
        reservationService.makeReservation(workspaceId);
    }

    @Transactional
    public void cancelReservation(Integer reservationId) {
        reservationService.cancelReservation(reservationId);
    }

    public List<Workspace> listAvailableWorkspaces() {
        return reservationService.listAvailableWorkspaces();
    }

    public List<Reservation> listMyReservations() {
        return reservationService.listAllReservations();
    }
}