package patterns.task.menues;

import patterns.task.Customer;
import patterns.task.MovieLibrary;

public class AdminMenu {

    public static void showMenu() {
        while (true) {
            System.out.println("""
                    Pick option
                    1.Show all films in catalog
                    2.Add movie
                    3.Show all users and their revenue
                    4.Show films statistics
                    5.Exit
                    """);
            int option = getValuesFromScanner.getIntFromConsole();
            if (option == 1) {
                MovieLibrary.showAllFilms();
            } else if (option == 2) {
                MovieLibrary.makeMovie();
            } else if (option == 3) {
                showCustomersInfo();
            } else if (option == 4) {
                MovieLibrary.showFilmsStatistics();
            } else if (option == 5) {
               break;
            } else {
                System.out.println("Input miss match");
            }
        }
    }

    private static void showCustomersInfo() {
        for (Customer customer: LoginSystem.getCustomers().values()) {
            System.out.println("---------------");
            customer.showInfo();
        }
    }
}
