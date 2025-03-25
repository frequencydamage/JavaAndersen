package org.novak.java.model.workspace;

import java.util.List;

public interface WorkspaceRepository {

    List<Workspace> getAll();

    List<Workspace> getAllAvailable();

    Workspace getById(Integer workspaceId);

    void create(Workspace workspace);

    void updateAvailabilityById(Integer workspaceId, Boolean isAvailable);

    void deleteById(Integer workspaceId);
}
