package org.novak.java.model.workspace;

import org.novak.java.util.DataLoaderUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkspaceInMemoryRepositoryImpl implements WorkspaceRepository, Serializable {

    private static WorkspaceInMemoryRepositoryImpl instance;
    private final Map<Integer, Workspace> workspaces = new HashMap<>();
    private static final String FILE_NAME = System.getProperty("workspaces.file");

    private WorkspaceInMemoryRepositoryImpl() {
    }

    public void createWorkspace(Workspace workspace) {
        workspaces.put(workspace.getId(), workspace);
        saveData();
    }

    @Override
    public void updateWorkspaceAvailabilityById(int workspaceId, boolean isAvailable) {
        workspaces.values().stream()
                .filter(workspace -> workspace.getId() == workspaceId)
                .findFirst()
                .ifPresent(workspace -> workspace.setAvailable(isAvailable));
        saveData();
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
        saveData();
    }

    public static WorkspaceRepository getInstance() {
        if (instance == null) {
            instance = DataLoaderUtil.loadData(FILE_NAME);

            if (instance == null) {
                instance = new WorkspaceInMemoryRepositoryImpl();
            }
        }

        return instance;
    }

    private void saveData() {
        DataLoaderUtil.saveData(this, FILE_NAME);
    }
}
