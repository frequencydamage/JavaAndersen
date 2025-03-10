package org.novak.java.controller;

import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.service.ReservationService;

import java.util.List;

public class CustomerController {

    private ReservationService reservationService = new ReservationService();

    public void makeReservation(int workspaceId) {
        reservationService.makeReservation(workspaceId);
    }

    public void cancelReservation(int reservationId) {
        reservationService.cancelReservation(reservationId);
    }

    public List<Workspace> listAvailableWorkspaces() {
        return reservationService.listAvailableWorkspaces();
    }

    public List<Reservation> listMyReservations() {
        return reservationService.listAllReservations();
    }
}