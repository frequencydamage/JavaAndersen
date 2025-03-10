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
        workspaceRepository.createWorkspace(new Workspace(generateUniqueWorkspaceId(), price, workspaceType, true));
    }

    public List<Workspace> listAllWorkspaces() {
        List<Workspace> allWorkspaces = workspaceRepository.getAllWorkspaces();
        if (allWorkspaces.isEmpty()) {
            throw new ResourceNotFoundException("No workspaces!");
        }
        return allWorkspaces;
    }

    public void removeWorkspace(int workspaceId) {
        if (workspaceRepository.getWorkspaceById(workspaceId) == null) {
            throw new ResourceNotFoundException("Workspace with id: " + workspaceId + " was not found!");
        }

        workspaceRepository.deleteWorkspaceByWorkspaceId(workspaceId);
        reservationRepository.deleteReservationByWorkspaceId(workspaceId);
    }

    private int generateUniqueWorkspaceId() {
        int workSpaceId;
        while (true) {
            int tempId = random.nextInt(1_000);

            if (workspaceRepository.getAllWorkspaces().stream().noneMatch(w -> w.getId() == tempId)) {
                workSpaceId = tempId;
                break;
            }
        }
        return workSpaceId;
    }
}