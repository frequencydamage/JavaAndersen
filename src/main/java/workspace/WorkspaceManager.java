package workspace;

import java.util.*;

public class WorkspaceManager {

    private final Scanner scanner = new Scanner(System.in);
    private final WorkspaceConfigurator workspaceConfigurator = new WorkspaceConfigurator();
    private final Map<Integer, Workspace> workspaces = new HashMap<>();
    private final List<WorkspaceObserver> observers = new ArrayList<>();

    public void addObserver(WorkspaceObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void addWorkspace() {
        Workspace workspace = workspaceConfigurator
                .setWorkspacePrice()
                .setWorkspaceType()
                .setWorkspaceAvailability()
                .setWorkspaceId(workspaces)
                .configure();

        workspaces.put(workspace.getId(), workspace);

        if (workspace.getAvailability()) {
            notifyObserversAboutWorkspaceAddition(workspace.getId(), workspace);
        }

        System.out.println("Workspace: " + workspace + " was successfully added!");
    }

    private void notifyObserversAboutWorkspaceAddition(int workspaceId, Workspace workspace) {
        for (WorkspaceObserver observer : observers) {
            observer.onAvailableWorkspaceAdded(workspaceId, workspace);
        }
    }

    public void listAllWorkspaces() {
        if (!workspaces.isEmpty()) {
            System.out.println("List of workspaces: ");
            workspaces.values().forEach(System.out::println);
        } else {
            System.out.println("No workspaces available.");
        }
    }

    public void removeWorkspace() {
        if (workspaces.isEmpty()) {
            System.out.println("No workspaces to remove!");
            return;
        }

        System.out.println("Enter workspace ID to remove:");
        while (true) {
            String input = scanner.next().trim();

            try {
                int workspaceId = Integer.parseInt(input);
                if (workspaces.containsKey(workspaceId)) {
                    workspaces.remove(workspaceId);

                    System.out.println("Workspace with ID: " + workspaceId + " was successfully removed!");
                    notifyObserversAboutWorkspaceRemove(workspaceId);
                    break;
                }
                System.out.println("No workspace with such ID! Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric workspace ID.");
            }
        }
    }

    private void notifyObserversAboutWorkspaceRemove(int workspaceId) {
        for (WorkspaceObserver observer : observers) {
            observer.onWorkspaceRemoved(workspaceId);
        }
    }
}