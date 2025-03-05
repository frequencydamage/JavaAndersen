package user;

import service.CustomerWorkspaceService;

public class Customer {

    private final CustomerWorkspaceService customerWorkspaceService;

    public Customer(CustomerWorkspaceService customerWorkspaceService) {
        this.customerWorkspaceService = customerWorkspaceService;
    }

    public void makeReservation() {
        customerWorkspaceService.makeReservation();
    }

    public void cancelReservation() {
        customerWorkspaceService.cancelReservation();
    }

    public void listAvailableWorkspaces() {
        customerWorkspaceService.listAvailableWorkspaces();
    }

    public void listMyReservations() {
        customerWorkspaceService.listMyReservations();
    }
}
