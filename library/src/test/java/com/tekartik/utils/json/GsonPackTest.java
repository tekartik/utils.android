package com.tekartik.utils.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.junit.ComparisonFailure;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.tekartik.utils.debug.Debug.devLog;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GsonPackTest {

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
    public void pack() {
        List<Simple1> list = new ArrayList<>();
        list.add(new Simple1("test1", 1L));
        list.add(new Simple1("test2", 2L));

        Gson gson = new Gson();

        assertNull(GsonUtils.packList(gson, null));
        GsonPack gsonPack = new GsonPack(0, list);
        JsonObject pack = GsonPack.pack(gson, gsonPack);

        // handle both direction
        try {
            assertEquals("{\"columns\":[\"longValue\",\"textValue\"],\"rows\":[[1,\"test1\"],[2,\"test2\"]]}", pack.toString());
        } catch (ComparisonFailure ignore) {
            assertEquals("{\"columns\":[\"textValue\",\"longValue\"],\"rows\":[[\"test1\",1],[\"test2\",2]]}", pack.toString());
        }

        // version = 0, same thing
        assertEquals(pack, GsonUtils.packList(gson, list));

        gsonPack.setVersion(1);

        pack = GsonPack.pack(gson, gsonPack);
        try {
            assertEquals("{\"columns\":[\"longValue\",\"textValue\"],\"rows\":[[1,\"test1\"],[2,\"test2\"]],\"version\":1}", pack.toString());
        } catch (ComparisonFailure ignore) {
            assertEquals("{\"columns\":[\"textValue\",\"longValue\"],\"rows\":[[\"test1\",1],[\"test2\",2]],\"version\":1}", pack.toString());
        }
        devLog(pack.toString());
        /*
        JsonElement raw = gson.toJsonTree(list);
        Debug.devLog(pack.toString());
        //Debug.devLog(raw.toString());
        assertEquals(GsonUtils.parse("[{\"textValue\":\"test1\",\"longValue\":1},{\"textValue\":\"test2\",\"longValue\":2}]"), raw);

        try {
            assertEquals(GsonUtils.parse("{\"columns\":[\"longValue\",\"textValue\"],\"rows\":[[1,\"test1\"],[2,\"test2\"]]}"), pack);
        } catch (AssertionError e) {
            assertEquals(GsonUtils.parse("{\"columns\":[\"textValue\",\"longValue\"],\"rows\":[[\"test1\",1],[\"test2\",2]]}"), pack);
        }

        assertTrue(pack.toString().length() < raw.toString().length());

        GsonPack<Simple1> unpacked = GsonPack.unpack(gson, pack, Simple1.class);
        assertEquals(gson.toJsonTree(unpacked), gson.toJsonTree(list));
        */

    }

    @Test
    public void packNull() {
        Gson gson = new Gson();
        GsonPack<Simple1> pack = new GsonPack<>(1, null);
        assertNull(pack.pack(gson));
    }

    @Test
    public void unpack() {
        assertNull(GsonPack.unpack(null, null, Simple1.class));
        Gson gson = new Gson();
        assertNull(GsonPack.unpack(gson, null, Simple1.class));

        assertNull(GsonPack.unpack(gson, (JsonObject) GsonUtils.parseObject("{}"), Simple1.class));
        assertNull(GsonPack.unpack(gson, (JsonObject) GsonUtils.parseObject("{\"version\":\"gore\"}"), Simple1.class));

        GsonPack<Simple1> gsonPack = GsonPack.unpack(gson, (JsonObject) GsonUtils.parseObject("{\"version\":0}"), Simple1.class);
        assertEquals(0, gsonPack.getVersion());
        assertNull(gsonPack.getList());

        gsonPack = GsonPack.unpack(gson, (JsonObject) GsonUtils.parseObject("{\"version\":1}"), Simple1.class);
        assertEquals(1, gsonPack.getVersion());
        assertNull(gsonPack.getList());

        gsonPack = GsonPack.unpack(gson, (JsonObject) GsonUtils.parseObject("{\"columns\":[\"longValue\",\"textValue\"],\"rows\":[[1,\"test1\"],[2,\"test2\"]]}"), Simple1.class);
        assertEquals(0, gsonPack.getVersion());
        assertEquals(2, gsonPack.getList().size());

        //GsonUtils.parse("[{\"textValue\":\"test1\",\"longValue\":1},{\"textValue\":\"test2\",\"longValue\":2}]"
    }
}
