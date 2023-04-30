package patterns.task.factories;

import patterns.task.audienceType.Adult;
import patterns.task.audienceType.AudienceType;
import patterns.task.audienceType.Children;
import patterns.task.audienceType.Teen;

public class AudienceFactory {
    private static final AudienceType adult = new Adult();
    private static final AudienceType teen = new Teen();
    private static final AudienceType children = new Children();

    public static AudienceType getAdult() {
        return adult;
    }

    public static AudienceType getTeen() {
        return teen;
    }
    public static AudienceType getChildren() {
        return children;
    }
}
