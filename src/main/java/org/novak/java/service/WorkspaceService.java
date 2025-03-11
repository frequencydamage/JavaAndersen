package org.novak.java.service;

import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.model.reservation.ReservationInMemoryRepositoryImpl;
import org.novak.java.model.reservation.ReservationRepository;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.model.workspace.WorkspaceInMemoryRepositoryImpl;
import org.novak.java.model.workspace.WorkspaceRepository;
import org.novak.java.model.workspace.WorkspaceType;

import java.util.*;

public class WorkspaceService {

    private Random random = new Random();
    private WorkspaceRepository workspaceRepository = WorkspaceInMemoryRepositoryImpl.getInstance();
    private ReservationRepository reservationRepository = ReservationInMemoryRepositoryImpl.getInstance();

    public void addWorkspace(double price, WorkspaceType workspaceType) {
        workspaceRepository.createWorkspace(new Workspace(
                generateUniqueWorkspaceId(), price, workspaceType, true));
    }

    public List<Workspace> listAllWorkspaces() {
        return workspaceRepository.getAllWorkspaces();
    }

    public void removeWorkspace(int workspaceId) {
        Optional.ofNullable(workspaceRepository.getWorkspaceById(workspaceId))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Workspace with id: " + workspaceId + " was not found!"));

        workspaceRepository.deleteWorkspaceByWorkspaceId(workspaceId);
        reservationRepository.deleteReservationByWorkspaceId(workspaceId);
    }

    private int generateUniqueWorkspaceId() {
        int workSpaceId;
        while (true) {
            workSpaceId = random.nextInt(1_000);

            if (workspaceRepository.getWorkspaceById(workSpaceId) == null) {
                return workSpaceId;
            }
        }
    }
}