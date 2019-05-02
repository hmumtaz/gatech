package configuration;

import org.json.JSONObject;

/**
 * Represents game configuration as a JSON map of values.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public interface IConfiguration {

    /**
     * Returns the configuration for this MULE game as JSON.
     * Modifying this json object will modify the configuration.
     *
     * @return The configuration of this game.
     */
    public JSONObject getJSON();

    /**
     * Sets the number of active players.
     *
     * @param player The number of active players.
     */
    public void setNumPlayers(int player);

    /**
     * Gets the number of active players.
     *
     * @return The number of active players.
     */
    public int getNumPlayers();

    /**
     * Sets JSON key, splitted at periods.
     *
     * @param key The key to set.
     * @param value The value to set the key to.
     */
    public void set(String key, Object value);

    /**
     * Gets JSON key, splitted at periods.
     *
     * @param key The key to get.
     * @return The value of the key.
     */
    public Object get(String key);
}
