package patterns.task;

import patterns.task.menues.LoginSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private final String name;

    private ArrayList<Rental> shoppingCart = new ArrayList<>();
    private ArrayList<Rental> rentals;

    private double moneySpent;
    private int bonusPoints;

    public Customer(String name, ArrayList<Rental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public double buy() {
        double totalAmount = 0;
        for (Rental each : shoppingCart) {
            double thisAmount = each.getAmount();
            //show figures for this rental
            totalAmount += thisAmount;
            bonusPoints = each.getFrequentRenterPoints(bonusPoints);
            MovieLibrary.addCountToMovie(each.getMovie());
            rentals.add(each);
        }
        shoppingCart.clear();
        moneySpent+=totalAmount;
        LoginSystem.writeToFileCustomers();
        MovieLibrary.writeMoviesToFile();
        return totalAmount;
    }

    public void showMyShoppingCart() {
        if (shoppingCart.size() > 0) {
            for (Rental rent : shoppingCart
            ) {
                rent.showInfo();
            }
        } else {
            System.out.println("Shopping cart is empty");
        }
    }
    public int getBonusPoints() {
        return bonusPoints;
    }

    public void addToShoppingCart(Rental rental) {
        shoppingCart.add(rental);
    }

    public List<Rental> getShoppingCart() {
        return shoppingCart;
    }

    public void clearShoppingCart() {
        shoppingCart.clear();
    }

    public void showRentalHistory() {
        System.out.println("Here your rental history,there are - " + rentals.size() + " films");
        for (Rental rental: rentals) {
            rental.showInfo();
        }
    }

    public void showInfo() {
        System.out.println("Customer name - " + name);
        System.out.println("Count of films rented - " + rentals.size());
        System.out.println("Count of money spent - " + moneySpent);
        System.out.println("Bonus balance - " + bonusPoints);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", shoppingCart=" + shoppingCart +
                ", rentals=" + rentals +
                ", moneySpent=" + moneySpent +
                ", bonusPoints=" + bonusPoints +
                '}';
    }
}
