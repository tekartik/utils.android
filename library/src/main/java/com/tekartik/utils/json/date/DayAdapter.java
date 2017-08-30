package com.tekartik.utils.json.date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tekartik.utils.date.Day;

import java.lang.reflect.Type;

/**
 * Created by alex on 13/02/17.
 */

public class DayAdapter implements JsonSerializer<Day>, JsonDeserializer<Day> {

    @Override
    public Day deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String dayText = context.deserialize(json, String.class);
        return Day.parseDay(dayText);
    }

    @Override
    public JsonElement serialize(Day day, Type typeOfT, JsonSerializationContext context) {
        return new JsonPrimitive(day.toString());
    }
}
