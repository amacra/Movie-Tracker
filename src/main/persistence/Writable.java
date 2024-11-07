package persistence;

import org.json.JSONObject;

// modeled on the sample application
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
