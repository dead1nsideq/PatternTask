package patterns.task.MovieClassification;

import java.io.Serializable;

public class Regular implements MovieClassification {
    @Override
    public double getAmount(int daysRented) {
        double res = 2;
        if (daysRented > 2)
            res += (daysRented - 2) * 1.5;
        return res;
    }
}
