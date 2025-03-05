package workspace;

public interface WorkspaceObserver {

    void onAvailableWorkspaceAdded(int workspaceId, Workspace workspace);

    void onWorkspaceRemoved(int workspaceId);
}
