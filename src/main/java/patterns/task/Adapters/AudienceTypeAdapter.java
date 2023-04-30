package patterns.task.Adapters;

import com.google.gson.*;
import patterns.task.audienceType.AudienceType;
import patterns.task.factories.AudienceFactory;

import java.lang.reflect.Type;

public class AudienceTypeAdapter implements JsonSerializer<AudienceType>, JsonDeserializer<AudienceType> {
    private static final String TYPE_FIELD = "type";

    @Override
    public JsonElement serialize(AudienceType src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(TYPE_FIELD, src.getClass().getSimpleName());
        return jsonObject;
    }

    @Override
    public AudienceType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String typeName = jsonObject.get(TYPE_FIELD).getAsString();

        return switch (typeName) {
            case "Children" -> AudienceFactory.getChildren();
            case "Teen" -> AudienceFactory.getTeen();
            case "Adult" -> AudienceFactory.getAdult();
            default -> throw new JsonParseException("Unknown AudienceType: " + typeName);
        };
    }
}
