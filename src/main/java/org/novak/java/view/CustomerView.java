package org.novak.java.view;

import org.novak.java.controller.CustomerController;
import org.novak.java.customException.NoReservationsException;
import org.novak.java.customException.NoWorkspacesException;
import org.novak.java.customException.ReservationNotFoundException;
import org.novak.java.customException.WorkspaceNotFoundException;

class CustomerView extends View {

    private CustomerController customerController = new CustomerController();

    @Override
    public void start() {
        while (true) {
            printMenu();
            String input = scanner.nextLine().trim();
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
    protected void handleInput(String input) {
        switch (input) {
            case "1" -> makeReservation();
            case "2" -> cancelReservation();
            case "3" -> listAvailableWorkspaces();
            case "4" -> listMyReservations();
            default -> System.out.println("No such option");
        }
    }

    private void makeReservation() {
        try {
            System.out.println(
                    "List of available workspaces to reserve :" + customerController.listAvailableWorkspaces());
        } catch (NoWorkspacesException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        int workspaceId;
        while (true) {
            try {
                workspaceId = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric workspace-id.");
            }
        }

        try {
            customerController.makeReservation(workspaceId);
            System.out.println("Reservation was created successfully!");
        } catch (WorkspaceNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void cancelReservation() {
        try {
            System.out.println("List of reservations to cancel: " + customerController.listMyReservations());
        } catch (NoReservationsException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        int reservationId;
        while (true) {
            try {
                reservationId = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a numeric reservation-id.");
            }
        }

        try {
            customerController.cancelReservation(reservationId);
            System.out.println("Reservation with id: " + reservationId + " was successfully deleted!");
        } catch (ReservationNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void listAvailableWorkspaces() {
        try {
            System.out.println("List of available workspaces: " + customerController.listAvailableWorkspaces());
        } catch (NoWorkspacesException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void listMyReservations() {
        try {
            System.out.println("List of reservations :" + customerController.listMyReservations());
        } catch (NoReservationsException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
