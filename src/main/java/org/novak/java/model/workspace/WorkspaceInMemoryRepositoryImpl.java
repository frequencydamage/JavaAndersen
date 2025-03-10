package org.novak.java.model.workspace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkspaceInMemoryRepositoryImpl implements WorkspaceRepository {

    private static WorkspaceInMemoryRepositoryImpl instance;
    private final Map<Integer, Workspace> workspaces = new HashMap<>();

    private WorkspaceInMemoryRepositoryImpl() {
    }

    public void createWorkspace(Workspace workspace) {
        workspaces.put(workspace.getId(), workspace);
    }

    @Override
    public void updateWorkspaceAvailabilityById(int workspaceId, boolean isAvailable) {
        workspaces.values().stream()
                .filter(workspace -> workspace.getId() == workspaceId)
                .findFirst()
                .ifPresent(workspace -> workspace.setAvailable(isAvailable));
    }

    @Override
    public List<Workspace> getAllWorkspaces() {
        return workspaces.values().stream()
                .toList();
    }

    @Override
    public List<Workspace> getAvailableWorkspaces() {
        return workspaces.values().stream()
                .filter(Workspace::getAvailable)
                .toList();
    }

    @Override
    public Workspace getWorkspaceById(int workspaceId) {
        return workspaces.get(workspaceId);
    }

    @Override
    public void deleteWorkspaceByWorkspaceId(int workspaceId) {
        workspaces.remove(workspaceId);
    }

    public static WorkspaceRepository getInstance() {
        if (instance == null) {
            instance = new WorkspaceInMemoryRepositoryImpl();
        }

        return instance;
    }
}
