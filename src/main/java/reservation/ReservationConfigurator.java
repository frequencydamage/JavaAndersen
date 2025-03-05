package reservation;

import workspace.Workspace;

import java.util.Map;
import java.util.Scanner;

class ReservationConfigurator {

    private final Scanner scanner = new Scanner(System.in);

    private int id;
    private Workspace workspace;

    public ReservationConfigurator selectWorkspace(Map<Integer, Workspace> availableWorkspaces) {
        System.out.println("Please, enter the id to configure a workspace: ");

        while (true) {
            String input = scanner.next().trim();
            try {
                id = Integer.parseInt(input);

                if (!availableWorkspaces.containsKey(id)) {
                    System.out.println("No such workspace-id! Please, enter an id from the list below!");
                    availableWorkspaces.values().forEach(System.out::println);
                    continue;
                }

                workspace = availableWorkspaces.get(id);
                break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric workspace-id.");
            }
        }
        return this;
    }

    public Reservation configure() {
        workspace.setAvailability(false);

        return new Reservation()
                .setId(id)
                .setWorkspace(workspace);
    }
}