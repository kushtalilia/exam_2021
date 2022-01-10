package json;

import java.util.HashMap;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    HashMap<String, Json> itemsData = new HashMap<String, Json>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair jsonPair: jsonPairs) {
            itemsData.put(jsonPair.key, jsonPair.value);
        }
    }

    @Override
    public String toJson() {
        String intoJson = "{";
        for (String key: itemsData.keySet()) {
            intoJson += key + ": " + itemsData.get(key).toJson() + ", ";
        }
        if (intoJson.equals("{")){
            return "{}";
        }
        return intoJson.substring(0, intoJson.length() - 2) + "}";
    }

    public void add(JsonPair jsonPair) {
        itemsData.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return itemsData.get(name);
    }


    public JsonObject projection(String... names) {
        JsonObject curProjection = new JsonObject();

        for (String name: names) {
            Json result = itemsData.get(name);
            if (result != null) {
                curProjection.add(new JsonPair(name, result));
            }
        }
        return curProjection;
    }
}
