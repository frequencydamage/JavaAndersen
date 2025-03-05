package menu;

import service.AdminWorkspaceService;
import user.Admin;
import workspace.WorkspaceManager;

class AdminMenu extends Menu {

    private final Admin admin;

    public AdminMenu(WorkspaceManager workspaceManager) {
        AdminWorkspaceService adminWorkspaceService = new AdminWorkspaceService(workspaceManager);
        this.admin = new Admin(adminWorkspaceService);
    }

    @Override
    public void start() {
        while (true) {
            printMenu();
            String input = scanner.nextLine();
            if (input.equals("4")) {
                return;
            }
            handleInput(input);
        }
    }

    @Override
    protected void printMenu() {
        System.out.println("""
                ==================================
                Admin Menu:
                1 - Add Workspace
                2 - Remove Workspace
                3 - View All Workspaces
                4 - List All Reservations
                4 - Exit
                ==================================
                """);
    }

    @Override
    public void handleInput(String input) {
        switch (input) {
            case "1" -> admin.addWorkspace();
            case "2" -> admin.removeWorkspace();
            case "3" -> admin.listWorkspaces();
            case "4" -> admin.listAllReservations();
            default -> System.out.println("No such option");
        }
    }
}