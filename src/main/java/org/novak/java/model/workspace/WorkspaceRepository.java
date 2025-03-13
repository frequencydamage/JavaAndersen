package org.novak.java.model.workspace;

import java.util.List;

public interface WorkspaceRepository {

    List<Workspace> getAllWorkspaces();

    List<Workspace> getAvailableWorkspaces();

    Workspace getWorkspaceById(int workspaceId);

    void createWorkspace(Workspace workspace);

    void updateWorkspaceAvailabilityById(int workspaceId, boolean isAvailable);

    void deleteWorkspaceByWorkspaceId(int workspaceId);
}
