package reservation;

import workspace.Workspace;
import workspace.WorkspaceManager;
import workspace.WorkspaceObserver;

import java.util.*;

public class ReservationManager implements WorkspaceObserver {

    private final Scanner scanner = new Scanner(System.in);
    private final ReservationConfigurator reservationConfigurator = new ReservationConfigurator();
    private final WorkspaceManager workspaceManager;
    private final Map<Integer, Reservation> reservations = new HashMap<>();
    private Map<Integer, Workspace> availableWorkspaces = new HashMap<>();

    public ReservationManager(WorkspaceManager workspaceManager) {
        this.workspaceManager = workspaceManager;
        this.workspaceManager.addObserver(this);
    }

    public void makeReservation() {
        if (availableWorkspaces.isEmpty()) {
            System.out.println("No available workspaces to make a reservation!");
            return;
        }
        System.out.println("Please, select available workspaces from the list below: ");
        listAvailableWorkspaces();

        Reservation reservation = reservationConfigurator
                .selectWorkspace(availableWorkspaces)
                .configure();

        reservations.put(reservation.getId(), reservation);
        availableWorkspaces.remove(reservation.getId());
        System.out.println("Workspace was reserved successfully: " + reservation);
    }

    public void cancelReservation() {
        if (!reservations.isEmpty()) {
            while (true) {
                System.out.println("Please, select a reservation to cancel: ");
                listMyReservations();

                String input = scanner.next().trim();

                if (input.equalsIgnoreCase("null")) {
                    System.out.println("Invalid input. Please enter a numeric workspace ID.");
                    continue;
                }

                try {
                    int reservationId = Integer.parseInt(input);

                    if (reservations.containsKey(reservationId)) {
                        reservations.get(reservationId).getWorkspace().setAvailability(true);

                        availableWorkspaces.put(
                                reservations.get(reservationId).getId(),
                                reservations.get(reservationId).getWorkspace());

                        reservations.remove(reservationId);
                        System.out.println("Reservation with ID: " + reservationId + " was successfully cancelled!");
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric workspace ID.");
                }
            }
        }

        System.out.println("You have no reservations!");
    }

    public void listAvailableWorkspaces() {
        if (availableWorkspaces.isEmpty()) {
            System.out.println("No available workspaces!");
            return;
        }

        System.out.println("List of available workspaces to reserve: ");
        availableWorkspaces.values().forEach(System.out::println);
    }

    public void listMyReservations() {
        if (!reservations.isEmpty()) {
            System.out.println("Your reservation list: ");
            reservations.values().forEach(System.out::println);
            return;
        }

        System.out.println("You have no reservations!");
    }

    @Override
    public void onAvailableWorkspaceAdded(int workspaceId, Workspace workspace) {
        availableWorkspaces.put(workspaceId, workspace);
    }

    @Override
    public void onWorkspaceRemoved(int workspaceId) {
        reservations.remove(workspaceId);
        availableWorkspaces.remove(workspaceId);
    }
}