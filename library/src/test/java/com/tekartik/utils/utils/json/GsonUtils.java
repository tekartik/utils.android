package com.tekartik.utils.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GsonUtils {

    static private String COLUMNS = "columns";
    static private String ROWS = "rows";

    static private List<String> getKeys(JsonObject jsonObject) {
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, JsonElement> jsonElementEntry : jsonObject.entrySet()) {
            keys.add(jsonElementEntry.getKey());
        }
        return keys;
    }

    ///
    /// Convert to
    /// { "columns": ["column1", "column2"],
    /// "rows": [["row1_col1", "row1_col2"],["row2_col1", "row2_col2"]]
    static public JsonObject toColumnsRows(List<JsonObject> list) {

        Set<String> columnSet = new HashSet<>();
        // Gather all the columns
        for (JsonObject jsonObject : list) {
            columnSet.addAll(getKeys(jsonObject));
        }

        JsonArray jsonColumns = new JsonArray();
        for (String column : columnSet) {
            jsonColumns.add(new JsonPrimitive(column));
        }

        List<String> columns = new ArrayList<>(columnSet);

        // build the rows
        JsonArray jsonRows = new JsonArray();
        for (JsonObject jsonObject : list) {
            JsonArray jsonRow = new JsonArray();
            for (String column : columns) {
                jsonRow.add(jsonObject.get(column));
            }
            jsonRows.add(jsonRow);
        }

        JsonObject jsonResult = new JsonObject();
        jsonResult.add(COLUMNS, jsonColumns);
        jsonResult.add(ROWS, jsonRows);

        return jsonResult;
    }


    // Safe
    static public JsonObject parseObject(String src) {
        if (src != null) {
            try {
                JsonElement element = parse(src);
                if (element instanceof JsonObject) {
                    return element.getAsJsonObject();
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    static public JsonArray parseArray(String src) {
        if (src != null) {
            try {
                JsonElement element = parse(src);
                if (element instanceof JsonArray) {
                    return element.getAsJsonArray();
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }


    static public JsonElement parse(String src) {
        if (src == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        return parser.parse(src);
    }

    // safe
    static public <T> T fromJson(Gson gson, String json, Class<T> klass) {
        if (json == null) {
            return null;
        }
        try {
            return gson.fromJson(json, klass);
        } catch (Exception ignored) {
        }
        return null;
    }

    static public class ListOfJson<T> implements ParameterizedType {
        private Class<?> wrapped;

        public ListOfJson(Class<T> wrapper) {
            this.wrapped = wrapper;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{wrapped};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

    static public <T> List<T> listFromJson(Gson gson, String json, Class<T> klass) {
        if (json == null) {
            return null;
        }
        try {
            return gson.fromJson(json, new ListOfJson<T>(klass));
        } catch (Exception ignored) {
        }
        return null;
    }

    static public <T> JsonObject packList(Gson gson, List<T> list) {
        if (list != null) {
            List<JsonObject> jsonList = new ArrayList<>();
            for (T object : list) {
                jsonList.add(gson.toJsonTree(object).getAsJsonObject());
            }
            return toColumnsRows(jsonList);
        }
        return null;
    }


    static public <T> List<T> unpackList(Gson gson, JsonObject jsonObject, Class<T> type) {
        if (jsonObject != null) {
            try {
                List<T> list = new ArrayList<>();
                JsonArray jsonColumns = jsonObject.getAsJsonArray(COLUMNS);
                JsonArray jsonRows = jsonObject.getAsJsonArray(ROWS);

                String columns[] = gson.fromJson(jsonColumns, String[].class);
                int columnCount = columns.length;
                for (int i = 0; i < jsonRows.size(); i++) {
                    JsonArray jsonRow = jsonRows.get(i).getAsJsonArray();

                    JsonObject jsonItem = new JsonObject();

                    for (int j = 0; j < columnCount; j++) {
                        jsonItem.add(columns[j], jsonRow.get(j));
                    }
                    T item = gson.fromJson(jsonItem, type);
                    list.add(item);
                }
                return list;
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    static public String prettyPrint(JsonElement jsonElement) {
        if (jsonElement == null) {
            return null;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonElement);
    }

    static public String prettyPrint(String json) {
        return prettyPrint(parse(json));
    }
}
