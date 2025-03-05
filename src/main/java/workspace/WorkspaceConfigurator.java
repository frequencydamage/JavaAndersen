package workspace;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class WorkspaceConfigurator {

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private int id;
    private double price;
    private boolean isAvailable;
    private WorkspaceType workspaceType;

    public WorkspaceConfigurator setWorkspacePrice() {
        System.out.println("Please, enter the price: ");

        while (true) {
            String input = scanner.next().trim();

            try {
                price = Double.parseDouble(input);
                if (price > 0.00 && price < 1500.00) {
                    price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    break;
                }
                System.out.println("Price must be between 0.01 and 1499.99. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric price.");
            }
        }
        return this;
    }

    public WorkspaceConfigurator setWorkspaceType() {
        System.out.println("""
                Please, select workspace type
                1 - Open-space workspace
                2 - Private workspace
                3 - Cabin
                """);

        int selectedTypeOfWorkspace;
        while (true) {
            String input = scanner.next().trim();

            try {
                selectedTypeOfWorkspace = Integer.parseInt(input);
                switch (selectedTypeOfWorkspace) {
                    case 1 -> this.workspaceType = WorkspaceType.OPEN_SPACE;
                    case 2 -> this.workspaceType = WorkspaceType.PRIVATE;
                    case 3 -> this.workspaceType = WorkspaceType.CABIN;
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
        return this;
    }

    public WorkspaceConfigurator setWorkspaceAvailability() {
        System.out.println("""
                Please, select availability for a workspace:
                1 - Available
                2 - Reserved
                """);

        while (true) {
            String input = scanner.next().trim();

            try {
                int selectedOption = Integer.parseInt(input);
                if (selectedOption == 1 || selectedOption == 2) {
                    isAvailable = (selectedOption == 1);
                    break;
                }
                System.out.println("Invalid option! Please select 1 for Available or 2 for Reserved.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 for Available or 2 for Reserved.");
            }
        }

        return this;
    }

    public WorkspaceConfigurator setWorkspaceId(Map<Integer, Workspace> workspaces) {
        int workSpaceId = random.nextInt(1_000);
        while (workspaces.containsKey(workSpaceId)) {
            workSpaceId = random.nextInt(1_000);
        }
        this.id = workSpaceId;
        return this;
    }

    public Workspace configure() {
        return new Workspace()
                .setPrice(price)
                .setAvailability(isAvailable)
                .setWorkspaceType(workspaceType)
                .setId(id);
    }
}
