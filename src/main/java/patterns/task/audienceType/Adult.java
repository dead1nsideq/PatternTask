package patterns.task.audienceType;

public class Adult implements AudienceType {

    @Override
    public double getCoefficient() {
        return 1;
    }
}
