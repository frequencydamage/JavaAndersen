package org.novak.java.service;

import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.reservation.ReservationRepository;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.model.workspace.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private Random random = new Random();
    private WorkspaceRepository workspaceRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(WorkspaceRepository workspaceRepository, ReservationRepository reservationRepository) {
        this.workspaceRepository = workspaceRepository;
        this.reservationRepository = reservationRepository;
    }

    public void makeReservation(Integer workspaceId) {
        Workspace workspace = Optional.ofNullable(
                workspaceRepository.getById(workspaceId)).orElseThrow(()
                -> new ResourceNotFoundException("Workspace with id: " + workspaceId + " was not found!"));

        Reservation reservation = new Reservation(generateUniqueReservationId(), workspace);
        reservationRepository.create(reservation);

        workspaceRepository.updateAvailabilityById(workspaceId, false);
    }

    public void cancelReservation(Integer reservationId) {
        Reservation reservation = Optional.ofNullable(
                        reservationRepository.getById(reservationId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reservation with id: " + reservationId + " was not found!"));

        reservationRepository.deleteById(reservationId);
        workspaceRepository.updateAvailabilityById(reservation.getWorkspace().getId(), true);
    }

    public List<Workspace> listAvailableWorkspaces() {
        return workspaceRepository.getAllAvailable();
    }

    public List<Reservation> listAllReservations() {
        return reservationRepository.getAll();
    }

    private int generateUniqueReservationId() {
        int reservationId;
        while (true) {
            reservationId = random.nextInt(1, 1_000);

            if (reservationRepository.getById(reservationId) == null) {
                return reservationId;
            }
        }
    }
}