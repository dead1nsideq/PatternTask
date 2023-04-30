package patterns.task.MovieClassification;

import java.io.Serializable;

public class OnSale implements MovieClassification, Serializable {

    @Override
    public double getAmount(int daysRented) {
        return daysRented;
    }
}
