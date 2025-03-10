package org.novak.java.view;

public class MainView extends View {

    private AdminView adminMenu = new AdminView();
    private CustomerView customerMenu = new CustomerView();

    @Override
    public void start() {
        while (true) {
            printMenu();
            String input = scanner.nextLine();
            handleInput(input);
        }
    }

    @Override
    protected void printMenu() {
        System.out.println("""
                ==================================
                Welcome!
                Please, select a login option:
                1 - Sign In as Admin
                2 - Sign In as Customer
                3 - Exit
                ==================================
                """);
    }

    @Override
    protected void handleInput(String input) {
        switch (input) {
            case "1" -> {
                System.out.println("You selected Admin login");
                adminMenu.start();
            }
            case "2" -> {
                System.out.println("You selected Customer login");
                customerMenu.start();
            }
            case "3" -> {
                System.out.println("Closing the app...");
                System.exit(0);
            }
            default -> System.out.println("Invalid option. Please, try again.");
        }
    }
}