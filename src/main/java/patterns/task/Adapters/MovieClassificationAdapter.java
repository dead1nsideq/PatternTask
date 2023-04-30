package patterns.task.Adapters;

import com.google.gson.*;
import patterns.task.MovieClassification.MovieClassification;
import patterns.task.factories.MovieFactory;

import java.lang.reflect.Type;
public class MovieClassificationAdapter implements JsonSerializer<MovieClassification>, JsonDeserializer<MovieClassification> {
    private static final String TYPE_FIELD = "type";

    @Override
    public JsonElement serialize(MovieClassification src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(TYPE_FIELD, src.getClass().getSimpleName());
        return jsonObject;
    }

    @Override
    public MovieClassification deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String typeName = jsonObject.get(TYPE_FIELD).getAsString();

        return switch (typeName) {
            case "NewRelease" -> MovieFactory.getNewRelease();
            case "Regular" -> MovieFactory.getRegular();
            case "OnSale" -> MovieFactory.getOnSale();
            default -> throw new JsonParseException("Unknown MovieClassification type: " + typeName);
        };
    }
}

