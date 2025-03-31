package org.novak.java.repository;

import org.novak.java.model.workspace.Workspace;
import org.novak.java.model.workspace.WorkspaceRepository;

import java.util.List;

public class WorkspaceRepositoryImpl extends Repository implements WorkspaceRepository {

    private static WorkspaceRepository instance;

    private WorkspaceRepositoryImpl() {
    }

    @Override
    public void create(Workspace workspace) {
        super.create(workspace);
    }

    @Override
    public void updateAvailabilityById(Integer workspaceId, Boolean isAvailable) {
        Workspace workspace = getById(Workspace.class, workspaceId);
        if (workspace != null) {
            workspace.setIsAvailable(isAvailable);
            update(workspace);
        }
    }

    @Override
    public List<Workspace> getAll() {
        return getAll(Workspace.class);
    }

    @Override
    public List<Workspace> getAllAvailable() {
        return createQuery("SELECT workspace FROM Workspace workspace WHERE workspace.isAvailable = true",
                Workspace.class)
                .getResultList();
    }

    @Override
    public Workspace getById(Integer workspaceId) {
        return getById(Workspace.class, workspaceId);
    }

    @Override
    public void deleteById(Integer workspaceId) {
        Workspace workspace = getById(Workspace.class, workspaceId);
        if (workspace != null) {
            delete(workspace);
        }
    }

    public static WorkspaceRepository getInstance() {
        if (instance == null) {
            instance = new WorkspaceRepositoryImpl();
        }
        return instance;
    }
}