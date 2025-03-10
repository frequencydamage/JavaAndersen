package org.novak.java.service;

import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.reservation.ReservationInMemoryRepositoryImpl;
import org.novak.java.model.reservation.ReservationRepository;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.model.workspace.WorkspaceInMemoryRepositoryImpl;
import org.novak.java.model.workspace.WorkspaceRepository;

import java.util.*;

public class ReservationService {

    private Random random = new Random();
    private ReservationRepository reservationRepository = ReservationInMemoryRepositoryImpl.getInstance();
    private WorkspaceRepository workspaceRepository = WorkspaceInMemoryRepositoryImpl.getInstance();

    public void makeReservation(int workspaceId) {
        Workspace workspace = workspaceRepository.getWorkspaceById(workspaceId);
        if (workspace == null) {
            throw new ResourceNotFoundException("Workspace with id: " + workspaceId + " was not found!");
        }

        int uniqueReservationId = generateUniqueReservationId();
        reservationRepository.createReservation(
                new Reservation(workspace.getId(),
                        workspace.getWorkspaceType(),
                        uniqueReservationId));

        workspaceRepository.updateWorkspaceAvailabilityById(workspaceId, false);
    }

    public void cancelReservation(int reservationId) {
        Reservation reservation = reservationRepository.getReservationByReservationId(reservationId);
        if (reservation == null) {
            throw new ResourceNotFoundException("Reservation with id: " + reservationId + " was not found!");
        }

        reservationRepository.deleteReservationByReservationId(reservationId);
        workspaceRepository.updateWorkspaceAvailabilityById(reservation.getWorksSpaceId(), true);
    }

    public List<Workspace> listAvailableWorkspaces() {
        List<Workspace> availableWorkspaces = workspaceRepository.getAvailableWorkspaces();
        if (availableWorkspaces.isEmpty()) {
            throw new ResourceNotFoundException("No available workspaces to reserve!");
        }

        return availableWorkspaces;
    }

    public List<Reservation> listAllReservations() {
        List<Reservation> allReservations = reservationRepository.getAllReservations();
        if (allReservations.isEmpty()) {
            throw new ResourceNotFoundException("No reservations!");
        }

        return allReservations;
    }

    private int generateUniqueReservationId() {
        int reservationId;
        while (true) {
            int tempId = random.nextInt(1_000);

            if (listAvailableWorkspaces().stream().noneMatch(w -> w.getId() == tempId)) {
                reservationId = tempId;
                break;
            }
        }
        return reservationId;
    }
}