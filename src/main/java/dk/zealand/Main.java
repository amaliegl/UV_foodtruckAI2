package dk.zealand;

import dk.zealand.domain.Dish;
import dk.zealand.domain.Order;
import dk.zealand.service.MenuService;
import dk.zealand.service.OrderService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final MenuService MENU_SERVICE = new MenuService();
    private static final OrderService ORDER_SERVICE = new OrderService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("ByteBites – festivalens foodtruck");

        while (running) {
            showMenu();
            String choice;
            try {
                choice = scanner.nextLine().trim();
            } catch (java.util.NoSuchElementException e) {
                break;
            }

            switch (choice) {
                case "1" -> showDishes();
                case "2" -> createOrder(scanner);
                case "0" -> running = false;
                default -> System.out.println(
                        "Ugyldigt valg. Vælg 0, 1 eller 2."
                );
            }
        }

        System.out.println("Programmet er afsluttet.");
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Vis retter");
        System.out.println("2. Opret bestilling");
        System.out.println("0. Afslut");
        System.out.print("Vælg: ");
    }

    private static void showDishes() {
        System.out.println("Retter:");
        List<Dish> dishes = MENU_SERVICE.getDishes();

        for (int i = 0; i < dishes.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, dishes.get(i).getDisplayName());
        }
    }

    private static void createOrder(Scanner scanner) {
        if (ORDER_SERVICE.getOrderCount() >= OrderService.MAX_ORDERS) {
            System.out.println("Der kan højst gemmes ti bestillinger.");
            return;
        }

        List<Dish> dishes = MENU_SERVICE.getDishes();
        System.out.println("Vælg en ret:");

        for (int i = 0; i < dishes.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, dishes.get(i).getName());
        }

        int dishNumber = readInt(scanner, "Ret nummer: ", 1, dishes.size());
        if (dishNumber == -1) {
            System.out.println("Ugyldig ret. Vælg en af de tre retter.");
            return;
        }

        int quantity = readInt(scanner, "Antal: ", 1, Integer.MAX_VALUE);
        if (quantity == -1) {
            System.out.println("Ugyldigt antal. Indtast et positivt heltal.");
            return;
        }

        Order order = ORDER_SERVICE.createOrder(dishes.get(dishNumber - 1), quantity);
        if (order == null) {
            System.out.println("Der kan højst gemmes ti bestillinger.");
            return;
        }

        System.out.println("Bestilling oprettet:");
        System.out.println(order);
    }

    private static int readInt(Scanner scanner, String prompt, int min, int max) {
        System.out.print(prompt);
        String line;
        try {
            line = scanner.nextLine().trim();
        } catch (java.util.NoSuchElementException e) {
            return -1;
        }

        int value;
        try {
            value = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }

        if (value < min || value > max) {
            return -1;
        }

        return value;
    }
}
