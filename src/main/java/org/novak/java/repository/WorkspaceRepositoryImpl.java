package org.novak.java.repository;

import org.novak.java.model.workspace.Workspace;
import org.novak.java.model.workspace.WorkspaceRepository;
import org.novak.java.model.workspace.WorkspaceType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class WorkspaceRepositoryImpl extends Repository implements WorkspaceRepository {

    private static WorkspaceRepositoryImpl instance;

    private final String QUERY_CREATE = "INSERT INTO workspace (id, price, workspace_type, is_available) VALUES (?, ?, ?, ?)";
    private final String QUERY_UPDATE_AVAILABILITY_BY_ID = "UPDATE workspace SET is_available = ? WHERE id = ?";
    private final String QUERY_GET_ALL = "SELECT id, price, workspace_type, is_available FROM workspace";
    private final String QUERY_GET_ALL_AVAILABLE = "SELECT id, price, workspace_type, is_available FROM workspace WHERE is_available = true";
    private final String QUERY_GET_BY_ID = "SELECT id, price, workspace_type, is_available FROM workspace WHERE id = ?";
    private final String QUERY_DELETE_BY_ID = "DELETE FROM workspace WHERE id = ?";

    private WorkspaceRepositoryImpl() {
    }

    public void create(Workspace workspace) {
        executeUpdate(QUERY_CREATE, statement -> {
            statement.setInt(1, workspace.getId());
            statement.setDouble(2, workspace.getPrice());
            statement.setString(3, workspace.getWorkspaceType().toString());
            statement.setBoolean(4, workspace.getIsAvailable());
        });
    }

    @Override
    public void updateAvailabilityById(Integer workspaceId, Boolean isAvailable) {
        executeUpdate(QUERY_UPDATE_AVAILABILITY_BY_ID, statement -> {
            statement.setBoolean(1, isAvailable);
            statement.setInt(2, workspaceId);
        });
    }

    @Override
    public List<Workspace> getAll() {
        List<Workspace> workspaceList = new ArrayList<>();

        executeSelect(QUERY_GET_ALL, statement -> {
        }, results -> {
            while (results.next()) {
                workspaceList.add(mapToWorkspace(results));
            }
        });

        return workspaceList;
    }

    @Override
    public List<Workspace> getAllAvailable() {
        List<Workspace> availableWorkspaceList = new ArrayList<>();

        executeSelect(QUERY_GET_ALL_AVAILABLE, statement -> {
                }, results -> {
                    while (results.next()) {
                        availableWorkspaceList.add(mapToWorkspace(results));
                    }
                }
        );

        return availableWorkspaceList;
    }

    @Override
    public Workspace getById(Integer workspaceId) {
        AtomicReference<Workspace> workspace = new AtomicReference<>(null);

        executeSelect(QUERY_GET_BY_ID,
                statement -> statement.setInt(1, workspaceId),
                results -> {
                    if (results.next()) {
                        workspace.set(mapToWorkspace(results));
                    }
                });

        return workspace.get();
    }

    @Override
    public void deleteById(Integer workspaceId) {
        executeUpdate(QUERY_DELETE_BY_ID, statement -> statement.setInt(1, workspaceId));
    }

    private Workspace mapToWorkspace(ResultSet result) throws SQLException {
        return new Workspace(
                result.getInt("id"),
                result.getDouble("price"),
                WorkspaceType.valueOf(result.getString("workspace_type")),
                result.getBoolean("is_available"));
    }

    public static WorkspaceRepository getInstance() {
        if (instance == null) {
            instance = new WorkspaceRepositoryImpl();
        }

        return instance;
    }
}