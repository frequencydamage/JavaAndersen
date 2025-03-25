package org.novak.java.model.workspace;

import org.novak.java.util.DataLoaderUtil;

import java.io.Serializable;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WorkspaceInMemoryRepositoryImpl implements WorkspaceRepository, Serializable {

    private static WorkspaceInMemoryRepositoryImpl instance;
    private final Map<Integer, Workspace> workspaces = new HashMap<>();
    private static final String FILE_NAME = System.getProperty("workspaces.file",
            Paths.get(System.getProperty("user.home"), ".workspace-manager", "workspaces.dat").toString());

    private WorkspaceInMemoryRepositoryImpl() {
    }

    public void create(Workspace workspace) {
        workspaces.put(workspace.getId(), workspace);
        saveData();
    }

    @Override
    public void updateAvailabilityById(Integer workspaceId, Boolean isAvailable) {
        workspaces.get(workspaceId).setIsAvailable(isAvailable);
        saveData();
    }

    @Override
    public List<Workspace> getAll() {
        return workspaces.values().stream()
                .toList();
    }

    @Override
    public List<Workspace> getAllAvailable() {
        return workspaces.values().stream()
                .filter(Workspace::getIsAvailable)
                .toList();
    }

    @Override
    public Workspace getById(Integer workspaceId) {
        return workspaces.get(workspaceId);
    }

    @Override
    public void deleteById(Integer workspaceId) {
        workspaces.remove(workspaceId);
        saveData();
    }

    public static WorkspaceRepository getInstance() {
        if (instance == null) {
            if (FILE_NAME != null && !FILE_NAME.isEmpty()) {
                instance = DataLoaderUtil.loadData(FILE_NAME);
            }
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
