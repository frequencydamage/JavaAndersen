package org.novak.java.view;

import org.novak.java.controller.CustomerController;
import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class CustomerView extends View {

    private CustomerController customerController;

    @Autowired
    public CustomerView(CustomerController customerController) {
        this.customerController = customerController;
    }

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
        List<Workspace> availableWorkspaces = customerController.listAvailableWorkspaces();
        if (availableWorkspaces.isEmpty()) {
            System.out.println("No available workspaces!");
            return;
        }
        System.out.println("List of available workspaces to reserve: " + availableWorkspaces);

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
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void cancelReservation() {
        List<Reservation> myReservations = customerController.listMyReservations();
        if (myReservations.isEmpty()) {
            System.out.println("No reservations!");
            return;
        }
        System.out.println("List of reservations to cancel: " + myReservations);

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
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void listAvailableWorkspaces() {
        List<Workspace> availableWorkspaces = customerController.listAvailableWorkspaces();
        if(availableWorkspaces.isEmpty()) {
            System.out.println("No available workspaces!");
            return;
        }
        System.out.println("Available workspaces :" + availableWorkspaces);
    }

    private void listMyReservations() {
        List<Reservation> myReservations = customerController.listMyReservations();
        if(myReservations.isEmpty()) {
            System.out.println("No reservations to list!");
            return;
        }
        System.out.println("List of reservations: " + myReservations);
    }
}
