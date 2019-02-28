package com.tekartik.utils.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.tekartik.utils.core.IntegerUtils;
import com.tekartik.utils.core.StringUtils;

import java.util.List;

/**
 * Created by alex on 29/03/17.
 */
public class GsonPack<T> {
    static private String VERSION = "version";

    // If not set, it is set to 0
    private int version;
    private List<T> list;

    public GsonPack(int version, List<T> list) {
        this.version = version;
        this.list = list;
    }

    static public <T> JsonObject pack(Gson gson, GsonPack<T> pack) {
        if (pack != null) {
            return pack.pack(gson);
        }
        return null;
    }

    // to unpack we need at least a valid list or version
    static public <T> GsonPack<T> unpack(Gson gson, JsonObject jsonObject, Class<T> type) {
        if (jsonObject != null) {

            try {

                JsonPrimitive jsonPrimitive = jsonObject.getAsJsonPrimitive(VERSION);
                Integer version = null;
                if (jsonPrimitive != null) {
                    version = gson.fromJson(jsonPrimitive, Integer.class);
                }
                List<T> list = GsonUtils.unpackList(gson, jsonObject, type);

                if ((list != null || version != null)) {
                    return new GsonPack<>(IntegerUtils.nonNull(version, 0), list);
                }

            } catch (Exception ignored) {
            }
        }
        return null;
    }

    public <T> JsonObject pack(Gson gson) {

        JsonObject jsonObject = GsonUtils.packList(gson, list);
        if (version != 0 && jsonObject != null) {
            jsonObject.addProperty(VERSION, version);
        }
        return jsonObject;
    }

    @Override
    public String toString() {
        return StringUtils.toString(pack(new Gson()));
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<T> getList() {
        return list;
    }
}
