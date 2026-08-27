package dk.zealand;

import dk.zealand.domain.Dish;
import dk.zealand.service.MenuService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final MenuService MENU_SERVICE = new MenuService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("ByteBites – festivalens foodtruck");

        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> showDishes();
                case "2" -> System.out.println("Oprettelse af bestillinger er endnu ikke implementeret.");
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
}
