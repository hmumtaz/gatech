package configuration;

import org.json.JSONObject;
import org.json.JSONException;
import java.util.List;
import java.util.Arrays;

/**
 * Represents game configuration as a JSON map of values.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public class Configuration implements IConfiguration {

    /**
     * The configuration object.
     */
    private final JSONObject configuration;

    /**
     * Creates an empty game configuration
     */
    public Configuration() {
        this.configuration = new JSONObject();
    }

    /**
     * Creates a new game configuration, using the given string as the inital
     * JSON configuration.
     *
     * @param json The initial configuration.
     */
    public Configuration(String json) {
        this.configuration = new JSONObject(json);
    }

    /**
     * Creates a new game configuration, using the given JSON as the inital
     * JSON configuration.
     *
     * @param json The initial configuration.
     */
    public Configuration(JSONObject json) {
        this.configuration = json;
    }

    /**
     * Returns the configuration for this MULE game as JSON.
     * Modifying this json object will modify the configuration.
     *
     * @return The configuration of this game.
     */
    public JSONObject getJSON() {
        return this.configuration;
    }

    /**
     * Sets the number of active players.
     *
     * @param player The number of active players.
     */
    public void setNumPlayers(int player) {
        this.getJSON()
            .getJSONObject("general")
            .getJSONObject("players")
            .putOpt("num", player);
    }

    /**
     * Gets the number of active players.
     *
     * @return The number of active players.
     */
    public int getNumPlayers() {
        return this.getJSON()
                   .getJSONObject("general")
                   .getJSONObject("players")
                   .optInt("num");
    };

    /**
     * Convert a json key into its levels.
     *
     * @param key The key to split.
     * @return The levels of the json key
     */
    private List<String> levels(String key) {
        return Arrays.asList(key.split("[.]"));
    }

    /**
     * Sets JSON key, splitted at periods.
     *
     * @param key The key to set.
     * @param value The value to set the key to.
     */
    public void set(String key, Object value) {
        List<String> levels = this.levels(key);

        JSONObject object = this.getJSON();
        for (int i = 0; i < levels.size() - 1; i++) {
            try {
                object = object.getJSONObject(levels.get(i));
            } catch(JSONException e) {
                JSONObject nested = new JSONObject();
                object.put(levels.get(i), nested);
                object = nested;
            }
        }

        object.putOpt(levels.get(levels.size() - 1), value);
    }

    /**
     * Gets JSON key, splitted at periods.
     *
     * @param key The key to get.
     * @return The value of the key.
     */
    public Object get(String key) {
        if (key.equals("")) {
            return null;
        }

        List<String> levels = this.levels(key);

        JSONObject object = this.getJSON();
        for (int i = 0; i < levels.size() - 1; i++) {
            try {
                object = object.getJSONObject(levels.get(i));
            } catch(JSONException e) {
                return null;
            }
        }

        return object.opt(levels.get(levels.size() - 1));
    }
}
