package com.tekartik.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.LongSerializationPolicy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.tekartik.utils.json.GsonUtils.fromJson;
import static com.tekartik.utils.json.GsonUtils.listFromJson;
import static com.tekartik.utils.json.GsonUtils.parseArray;
import static com.tekartik.utils.json.GsonUtils.parseObject;
import static com.tekartik.utils.json.GsonUtils.prettyPrint;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GsonUtilsTest {

    static public class Simple1 {
        public Simple1() {
        }

        public Simple1(String textValue,
                       Long longValue) {
            this.textValue = textValue;
            this.longValue = longValue;
        }

        String textValue;
        Long longValue;


        @Override
        public String toString() {
            return new Gson().toJson(this);
        }

        /*
        @Override
        public int hashCode() {
            return textValue != null : textValue.hashCode(),super.hashCode();
        }
        */
    }

    static public class Simple2 extends Simple1 {
        public Simple2() {
        }

        public Simple2(String textValue,
                       Long longValue) {
            super(textValue, longValue);
        }

        String otherTextValue = "other";
        Long otherLongValue = 1234L;


        @Override
        public String toString() {
            return new Gson().toJson(this);
        }

        /*
        @Override
        public int hashCode() {
            return textValue != null : textValue.hashCode(),super.hashCode();
        }
        */
    }

    @Test
    public void parser() {
        //assertEquals(2, 2);
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        assertEquals("{}", object.toString());

        Simple1 simple1 = new Simple1();
        JsonObject jsonSimple1 = (JsonObject) gson.toJsonTree(simple1);

        assertEquals(object, jsonSimple1);
        simple1.textValue = "text";
        simple1.longValue = Long.MAX_VALUE;

        jsonSimple1 = (JsonObject) gson.toJsonTree(simple1);
        assertFalse(object.equals(jsonSimple1));
        object.addProperty("textValue", "text");
        object.addProperty("longValue", Long.MAX_VALUE);
        assertEquals(object, jsonSimple1);
    }

    @Test
    public void toRowsColumns() {

        List<JsonObject> list = new ArrayList<JsonObject>();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("key", "value");
        list.add(jsonObject);

        JsonObject jsonResult = GsonUtils.toColumnsRows(list);
        //Debug.devLog(jsonResult.toString());
        JsonObject expected = new JsonObject();
        JsonArray jsonColumns = new JsonArray();
        jsonColumns.add(new JsonPrimitive("key"));
        JsonArray jsonRows = new JsonArray();
        JsonArray jsonRow = new JsonArray();
        jsonRow.add(new JsonPrimitive("value"));
        jsonRows.add(jsonRow);
        expected.add("columns", jsonColumns);
        expected.add("rows", jsonRows);
        assertEquals(expected, jsonResult);


    }


    @Test
    public void pack() {
        List<Simple1> list = new ArrayList<>();
        list.add(new Simple1("test1", 1L));
        list.add(new Simple1("test2", 2L));

        Gson gson = new Gson();

        assertNull(GsonUtils.packList(gson, null));
        JsonObject pack = GsonUtils.packList(gson, list);
        JsonElement raw = gson.toJsonTree(list);
        //Debug.devLog(pack.toString());
        //Debug.devLog(raw.toString());
        assertEquals(GsonUtils.parse("[{\"textValue\":\"test1\",\"longValue\":1},{\"textValue\":\"test2\",\"longValue\":2}]"), raw);

        try {
            assertEquals(GsonUtils.parse("{\"columns\":[\"longValue\",\"textValue\"],\"rows\":[[1,\"test1\"],[2,\"test2\"]]}"), pack);
        } catch (AssertionError e) {
            assertEquals(GsonUtils.parse("{\"columns\":[\"textValue\",\"longValue\"],\"rows\":[[\"test1\",1],[\"test2\",2]]}"), pack);
        }

        assertTrue(pack.toString().length() < raw.toString().length());

        List<Simple1> unpacked = GsonUtils.unpackList(gson, pack, Simple1.class);
        assertEquals(gson.toJsonTree(unpacked), gson.toJsonTree(list));


    }

    @Test
    public void parse() {
        assertNull(parseObject("[]"));
        assertNull(parseObject(null));
        assertNull(parseObject(""));
        assertEquals(0, parseObject("{}").entrySet().size());
        assertNull(parseArray("{}"));
        assertNull(parseArray(null));
        assertNull(parseArray(""));
        assertEquals(0, parseArray("[]").size());
    }

    @Test
    public void unpack() {
        assertNull(GsonUtils.unpackList(null, null, Simple1.class));
        Gson gson = new Gson();
        assertNull(GsonUtils.unpackList(gson, null, Simple1.class));

    }

    @Test
    public void testFromGson() {
        Gson gson = new Gson();
        assertEquals(null, fromJson(gson, null, Simple1.class));
        assertEquals(null, fromJson(gson, "", Simple1.class));
        assertEquals(null, fromJson(gson, "[]", Simple1.class));
        assertNotNull(fromJson(gson, "{}", Simple1.class));
        Simple1 simple = fromJson(gson, "{\"textValue\":\"test1\"}", Simple1.class);
        assertEquals("test1", simple.textValue);
    }

    @Test
    public void testListFromGson() {
        Gson gson = new Gson();
        assertEquals(null, listFromJson(gson, null, Simple1.class));
        assertEquals(null, listFromJson(gson, "", Simple1.class));
        assertEquals(null, listFromJson(gson, "{}", Simple1.class));
        assertNotNull(listFromJson(gson, "[]", Simple1.class));
        List<Simple1> simples = listFromJson(gson, "[{\"textValue\":\"test1\"}]", Simple1.class);
        assertEquals("test1", simples.get(0).textValue);
    }

    static class LongTest {
        Long value;
    }

    @Test
    public void longAsString() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        Gson gson = gsonBuilder.create();
        LongTest test = new LongTest();
        test.value = 123456789L;
        assertEquals("{\"value\":\"123456789\"}", gson.toJson(test));
        test = fromJson(gson, "{\"value\":\"123456789\"}", LongTest.class);
        assertEquals(123456789L, test.value.longValue());
        test = fromJson(gson, "{\"value\":123456789}", LongTest.class);
        assertEquals(123456789L, test.value.longValue());
    }
    /*
    @Test
    public void performance() {
        int repeatCount = 20;
        int itemCount = 10000;

        Gson gson = new Gson();


        List<Simple2> list = new ArrayList<>();

        for (int i = 0; i < itemCount; i++) {
            list.add(new Simple2("test" + Integer.toString(i), (long) i));
        }

        Stopwatch stopwatch = Stopwatch.createStarted();

        JsonObject packed = null;

        for (int k = 0; k < repeatCount; k++) {
            packed = GsonUtils.packList(gson, list);
        }
        Debug.devLog("packList: " + stopwatch.toString());
        stopwatch = Stopwatch.createStarted();

        for (int k = 0; k < repeatCount; k++) {
            GsonUtils.unpackList(gson, packed, Simple2.class);
        }
        Debug.devLog("unpackList: " + stopwatch.toString());

        stopwatch = Stopwatch.createStarted();

        String packedString = null;
        for (int k = 0; k < repeatCount; k++) {
            packedString = packed.toString();
        }
        Debug.devLog("toString: " + stopwatch.toString());

        stopwatch = Stopwatch.createStarted();

        for (int k = 0; k < repeatCount; k++) {
            GsonUtils.parse(packedString);
        }
        Debug.devLog("fromString: " + stopwatch.toString());

        Debug.devLog("" + repeatCount + "x" + itemCount + " packed (" + packedString.length() + " bytes): " + packedString);

    }
    */

    @Test
    public void stringList() {
        Gson gson = new Gson();
        assertArrayEquals(new String[]{"v1"}, gson.fromJson("[\"v1\"]", List.class).toArray());
    }

    @Test
    public void testPrettyPrint() {
        assertEquals(null, prettyPrint((String) null));
        assertEquals("null", prettyPrint(""));
        assertEquals("{}", prettyPrint("{}"));
        assertEquals("{\n" +
                "  \"test\": 1\n" +
                "}", prettyPrint("{\"test\":1}"));
        assertEquals("[\n" +
                "  1\n" +
                "]", prettyPrint("[1]"));
    }

}
