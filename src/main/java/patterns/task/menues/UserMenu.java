package patterns.task.menues;

import patterns.task.Customer;
import patterns.task.MovieLibrary;

public class UserMenu {
    public static void showMenu(Customer customer) {
        while (true) {
            System.out.println("""
                    Pick option
                    1.Show all films in catalog
                    2.Pick up film
                    3.Search films
                    4.Show my shopping cart
                    5.Show my bonus
                    6.Show my rental history
                    7.Exit
                    """);
            int option = getValuesFromScanner.getIntFromConsole();
            if (option == 1) {
                MovieLibrary.showAllFilms();
            } else if (option == 2) {
                MovieLibrary.pickUpFilms(customer);
            } else if (option == 3) {
                MovieLibrary.searchFilms();
            } else if (option == 4) {
                customer.showMyShoppingCart();
                if (customer.getShoppingCart().size() != 0) {
                    System.out.println("Do you want to buy films that in your shopping cart or clear all films from it?");
                    System.out.println("1. buy all films 2. clear all films 3. exit");
                    int n = getValuesFromScanner.getIntFromConsole();
                    if (n == 1) {
                        System.out.println("The sum would be " + customer.buy());
                    } else if (n == 2) {
                        customer.clearShoppingCart();
                        System.out.println("Shopping cars is clear row");
                    }
                }
            } else if (option == 5) {
                System.out.println("Your bonus points - " + customer.getBonusPoints());
            } else if (option == 6) {
                customer.showRentalHistory();
            } else if (option == 7) {
                break;
            } else {
                System.out.println("Input miss match");
            }
        }
    }
}
