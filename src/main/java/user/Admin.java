package user;

import service.AdminWorkspaceService;

public class Admin {

    private final AdminWorkspaceService adminWorkspaceService;

    public Admin(AdminWorkspaceService adminWorkspaceService) {
        this.adminWorkspaceService = adminWorkspaceService;
    }

    public void addWorkspace() {
        adminWorkspaceService.addWorkspace();
    }

    public void removeWorkspace() {
        adminWorkspaceService.removeWorkspace();
    }

    public void listWorkspaces() {
        adminWorkspaceService.listWorkspaces();
    }

    public void listAllReservations() {
        adminWorkspaceService.listAllReservations();
    }
}