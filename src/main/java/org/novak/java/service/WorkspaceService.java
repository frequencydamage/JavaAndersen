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

    public void addWorkspace(Double price, WorkspaceType workspaceType) {
        workspaceRepository.create(new Workspace(
                generateUniqueWorkspaceId(), price, workspaceType, true));
    }

    public List<Workspace> listAllWorkspaces() {
        return workspaceRepository.getAll();
    }

    public void removeWorkspace(Integer workspaceId) {
        Optional.ofNullable(workspaceRepository.getById(workspaceId))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Workspace with id: " + workspaceId + " was not found!"));

        workspaceRepository.deleteById(workspaceId);
        reservationRepository.deleteByWorkspaceId(workspaceId);
    }

    private int generateUniqueWorkspaceId() {
        int workSpaceId;
        while (true) {
            workSpaceId = random.nextInt(1, 1_000);

            if (workspaceRepository.getById(workSpaceId) == null) {
                return workSpaceId;
            }
        }
    }
}