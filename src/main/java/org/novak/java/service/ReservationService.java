package org.novak.java.service;

import org.novak.java.customException.NoReservationsException;
import org.novak.java.customException.NoWorkspacesException;
import org.novak.java.customException.ReservationNotFoundException;
import org.novak.java.customException.WorkspaceNotFoundException;
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
            throw new WorkspaceNotFoundException(workspaceId);
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
            throw new ReservationNotFoundException(reservationId);
        }

        reservationRepository.deleteReservationByReservationId(reservationId);
        workspaceRepository.updateWorkspaceAvailabilityById(reservation.getWorksSpaceId(), true);
    }

    public List<Workspace> listAvailableWorkspaces() {
        List<Workspace> availableWorkspaces = workspaceRepository.getAvailableWorkspaces();
        if (availableWorkspaces.isEmpty()) {
            throw new NoWorkspacesException();
        }

        return availableWorkspaces;
    }

    public List<Reservation> listAllReservations() {
        List<Reservation> allReservations = reservationRepository.getAllReservations();
        if (allReservations.isEmpty()) {
            throw new NoReservationsException();
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