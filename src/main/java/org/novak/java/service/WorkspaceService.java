package org.novak.java.service;

import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.model.workspace.WorkspaceRepository;
import org.novak.java.model.workspace.WorkspaceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WorkspaceService {

    private Random random = new Random();
    private WorkspaceRepository workspaceRepository;

    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @Transactional
    public void addWorkspace(Double price, WorkspaceType workspaceType) {
        workspaceRepository.create(new Workspace(generateUniqueWorkspaceId(), price, workspaceType, true, null));
    }

    public List<Workspace> listAllWorkspaces() {
        return workspaceRepository.getAll();
    }

    @Transactional
    public void removeWorkspace(Integer workspaceId) {
        Optional.ofNullable(workspaceRepository.getById(workspaceId))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Workspace with id: " + workspaceId + " was not found!"));

        workspaceRepository.deleteById(workspaceId);
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