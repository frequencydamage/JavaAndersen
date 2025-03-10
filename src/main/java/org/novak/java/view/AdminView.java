package org.novak.java.view;

import org.novak.java.controller.AdminController;
import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.model.workspace.WorkspaceType;

import java.math.BigDecimal;
import java.math.RoundingMode;

class AdminView extends View {

    private AdminController adminController = new AdminController();

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
                Admin Menu:
                1 - Add Workspace
                2 - Remove Workspace
                3 - View All Workspaces
                4 - List All Reservations
                5 - Exit
                ==================================
                """);
    }

    @Override
    public void handleInput(String input) {
        switch (input) {
            case "1" -> addWorkspace();
            case "2" -> removeWorkspace();
            case "3" -> listWorkspaces();
            case "4" -> listAllReservations();
            default -> System.out.println("No such option");
        }
    }

    private void addWorkspace() {
        double price;
        WorkspaceType workspaceType;

        System.out.println("Please, enter the price: ");
        while (true) {
            try {
                price = Double.parseDouble(scanner.nextLine().trim());
                if (price > 0.00 && price < 1500.00) {
                    price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    break;
                }
                System.out.println("Price must be between 0.01 and 1499.99. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric price.");
            }
        }

        System.out.println("""
                Please, select workspace type
                1 - Open-space workspace
                2 - Private workspace
                3 - Cabin
                """);
        while (true) {
            try {
                int selectedTypeOfWorkspace = Integer.parseInt(scanner.nextLine().trim());
                switch (selectedTypeOfWorkspace) {
                    case 1 -> workspaceType = WorkspaceType.OPEN_SPACE;
                    case 2 -> workspaceType = WorkspaceType.PRIVATE;
                    case 3 -> workspaceType = WorkspaceType.CABIN;
                    default -> {
                        System.out.println("No such workspace type! Select from the list above!");
                        continue;
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("No such workspace type! Select from the list above!");
            }
        }

        adminController.addWorkspace(price, workspaceType);
        System.out.println("Workspace was successfully added!");
    }

    private void removeWorkspace() {
        try {
            System.out.println("Select workspace to remove: " + adminController.listWorkspaces());
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        int workspaceId;
        while (true) {
            try {
                workspaceId = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric workspace ID.");
                continue;
            }

            try {
                adminController.removeWorkspace(workspaceId);
                System.out.println("Workspace with ID: " + workspaceId + " was successfully removed.");
                break;
            } catch (ResourceNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void listWorkspaces() {
        try {
            System.out.println("List of workspaces :" + adminController.listWorkspaces());
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void listAllReservations() {
        try {
            System.out.println("List of all reservations: " + adminController.listAllReservations());
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}