package patterns.task.factories;

import patterns.task.MovieClassification.MovieClassification;
import patterns.task.MovieClassification.NewRelease;
import patterns.task.MovieClassification.OnSale;
import patterns.task.MovieClassification.Regular;


//public class MovieFactory {
//    private static final Map<Type, MovieType> cache = new HashMap<>();
//    public enum Type {
//        NEW_RELEASE,
//        REGULAR,
//        ON_SALES
//    }
//    public static MovieType createMovie(Type mv) {
//        MovieType movieType = cache.get(mv);
//        if (mv == null) {
//            switch (mv) {
//                case NEW_RELEASE -> movieType = new NewRelease();
//                case REGULAR -> movieType = new Regular();
//                case ON_SALES -> movieType = new OnSale();
//                default -> throw new IllegalArgumentException("Unknown movie type: " + mv);
//            }
//            cache.put(mv, movieType);
//        }
//        return movieType;
//    }
//}
public class MovieFactory {
    private static final MovieClassification newRelease = new NewRelease();
    private static final MovieClassification regular = new Regular();
    private static final MovieClassification onSale = new OnSale();

    public static MovieClassification getNewRelease() {
        return newRelease;
    }

    public static MovieClassification getRegular() {
        return regular;
    }
    public static MovieClassification getOnSale() {
        return onSale;
    }
}