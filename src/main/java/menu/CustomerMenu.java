package menu;

import service.CustomerWorkspaceService;
import user.Customer;
import workspace.WorkspaceManager;

class CustomerMenu extends Menu {

    private final Customer customer;

    public CustomerMenu(WorkspaceManager workspaceManager) {
        CustomerWorkspaceService customerWorkspaceService = new CustomerWorkspaceService(workspaceManager);
        this.customer = new Customer(customerWorkspaceService);
    }

    @Override
    public void start() {
        while (true) {
            printMenu();
            String input = scanner.nextLine();
            if (input.equals("5")) {
                return;
            }
            handleInput(input);
        }
    }

    @Override
    protected void printMenu() {
        System.out.println("""
                ==================================
                Customer Menu:
                1 - Make a reservation
                2 - Cancel a reservation
                3 - Show available workspaces
                4 - View my reservations
                5 - Exit
                ==================================
                """);
    }

    @Override
    public void handleInput(String input) {
        switch (input) {
            case "1" -> customer.makeReservation();
            case "2" -> customer.cancelReservation();
            case "3" -> customer.listAvailableWorkspaces();
            case "4" -> customer.listMyReservations();
            default -> System.out.println("No such option");
        }
    }
}
