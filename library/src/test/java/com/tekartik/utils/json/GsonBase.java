package com.tekartik.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import static org.junit.Assert.assertEquals;

// Best is to copy this class
public class GsonBase {

    static private Gson gson = new Gson();

    static public void assertJsonEquals(String expectedJson, String json) {
        JsonElement jsonElementExpected = gson.fromJson(expectedJson, JsonElement.class);
        JsonElement jsonElement = gson.fromJson(json, JsonElement.class);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyExpected = gson.toJson(jsonElement);
        assertEquals(prettyExpected, jsonElementExpected, jsonElement);
    }
}
