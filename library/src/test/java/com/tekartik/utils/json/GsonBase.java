package com.tekartik.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import static org.junit.Assert.assertEquals;

// Best is to copy this class
public class GsonBase {

    static private Gson gson = new Gson();
    static private Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();

    static public void assertJsonEquals(String expectedJson, String json) {
        JsonElement jsonElementExpected = gson.fromJson(expectedJson, JsonElement.class);
        JsonElement jsonElement = gson.fromJson(json, JsonElement.class);
        String prettyExpected = gsonPretty.toJson(jsonElement);
        assertEquals(prettyExpected, jsonElementExpected, jsonElement);
    }

    static public void assertJsonEquals(String expectedJson, String json, List<String> butFields) {
        // butFields assume object
        if (butFields == null) {
            assertJsonEquals(expectedJson, json);
            return;
        }
        JsonObject jsonElementExpected = gson.fromJson(expectedJson, JsonObject.class);
        JsonObject jsonElement = gson.fromJson(json, JsonObject.class);

        for (String butField : butFields) {
            jsonElement.remove(butField);
            jsonElementExpected.remove(butField);
        }

        String prettyExpected = gsonPretty.toJson(jsonElement);
        assertEquals(prettyExpected, jsonElementExpected, jsonElement);
    }
}
