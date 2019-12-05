package json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private List<JsonPair> pairs = new ArrayList<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair p : jsonPairs) {
            add(p);
        }
    }

    @Override
    public String toJson() {
        final StringBuilder s = new StringBuilder();
        s.append("{");
        for (Iterator<JsonPair> pi = pairs.iterator(); pi.hasNext(); ) {
            s.append(pi.next().toJson());
            if (pi.hasNext()) {
                s.append(", ");
            }
        }
        s.append("}");
        return s.toString();
    }

    public void add(JsonPair jsonPair) {
        boolean found = false;
        for (int i = 0; i < pairs.size() && !found; i++) {
            JsonPair p = pairs.get(i);
            if (p.key.equals(jsonPair.key)) {
                found = true;
                pairs.set(i, jsonPair);
            }
        }
        if (!found) {
            pairs.add(jsonPair);
        }
    }

    public Json find(String name) {
        for (JsonPair p : pairs) {
            if (p.key.equals(name)) {
                return p.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        Set<String> nameSet = new HashSet<>(Arrays.asList(names));
        JsonObject other = new JsonObject();
        for (JsonPair p : pairs) {
            if (nameSet.contains(p.key)) {
                other.add(p);
            }
        }
        return other;
    }
}