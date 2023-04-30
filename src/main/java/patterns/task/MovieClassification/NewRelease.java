package patterns.task.MovieClassification;

import java.io.Serializable;

public class NewRelease implements MovieClassification {
    @Override
    public double getAmount(int daysRented) {
        return daysRented * 3;
    }
}
