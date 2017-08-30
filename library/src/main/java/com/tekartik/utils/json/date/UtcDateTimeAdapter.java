package com.tekartik.utils.json.date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tekartik.utils.date.UtcDateTime;

import java.lang.reflect.Type;

/**
 * Created by alex on 13/02/17.
 */

public class UtcDateTimeAdapter implements JsonSerializer<UtcDateTime>, JsonDeserializer<UtcDateTime> {

    @Override
    public UtcDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String dateText = context.deserialize(json, String.class);
        return UtcDateTime.parse(dateText);
    }

    @Override
    public JsonElement serialize(UtcDateTime utcDateTime, Type typeOfT, JsonSerializationContext context) {
        return new JsonPrimitive(utcDateTime.toString());
    }
}
