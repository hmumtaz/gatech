package resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class that allows code to retrieve game resources.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public class Resources implements IResources {

    private static final String RESOURCE_CONFIG_PATH  = "resources.json";
    private static final String RESOURCE_MAP_PATH_KEY = "path.graphics.map";
    private static final String RESOURCE_TOWN_PATH_KEY = "path.graphics.town";
    private static final String RESOURCE_GRAPHIC_PATH_KEY = "path.graphics.generic";
    private static final String BASE_CONFIG_PATH_KEY  = "path.config.initial";

    /**
     * Returns a URL that represents the given resource.
     *
     * @param path The path to retrieve
     * @return The url for `path`
     */
    public URL getResource(String path) {
        return getClass().getClassLoader()
                         .getResource(path);
    }

    /**
     * Returns an input stream for the given resource.
     *
     * @param path The path to retrieve
     * @return The input stream for `path`
     */
    public InputStream getResourceAsInputStream(String path) {
        return getClass().getClassLoader()
                         .getResourceAsStream(path);
    }

    /**
     * Returns the path of the initial configuration of the program.
     *
     * @return That path to the base configuration to the game.
     */
    public String getBaseConfigPath() {
        return this.getKey("path.config.initial");
    }

    /**
     * Returns the graphics path for maps.
     *
     * @return The path for graphical images for the game.
     */
    public String getMapGraphicPath() {
        return this.getKey(this.RESOURCE_MAP_PATH_KEY);
    }

    /**
     * Returns the graphics path for town images.
     *
     * @return The path for graphical images for the game.
     */
    public String getTownGraphicPath() {
        return this.getKey(this.RESOURCE_TOWN_PATH_KEY);
    }

    /**
     * Returns the image for a map tile.
     *
     * @param file The map tile file name.
     * @return The image for a map tile.
     */
    public Image getMapImage(String file) {
        return new Image(
            this.getResourceAsInputStream(
                this.getMapGraphicPath() + "/" + file
            )
        );
    }

    /**
     * Returns the image for a town graphic.
     *
     * @param file The map tile file name.
     * @return The image for a map tile.
     */
    public Image getTownImage(String file) {
        return new Image(
            this.getResourceAsInputStream(
                this.getTownGraphicPath() + "/" + file + ".png"
            )
        );
    }

    /**
     * Returns the graphics path for town images.
     *
     * @return The path for graphical images for the game.
     */
    public String getGraphicPath() {
        return this.getKey(this.RESOURCE_GRAPHIC_PATH_KEY);
    }

    /**
     * Returns the image for a town graphic.
     *
     * @param file The map tile file name.
     * @return The image for a map tile.
     */
    public Image getImage(String file) {
        return new Image(
            this.getResourceAsInputStream(
                this.getGraphicPath() + "/" + file + ".png"
            )
        );
    }

    /**
     * Returns a JSON object for a resource path.
     *
     * @param path The path to convert to json.
     * @return The resource as a json object.
     */
    public JSONObject getJSON(String path) {
        try {
            return new JSONObject(
                org.apache.commons.io.IOUtils.toString(
                    this.getResourceAsInputStream(path),
                    "UTF-8"
                )
            );
        } catch(IOException e) {
            return new JSONObject();
        }
    }

    /**
     * Returns a JSON object for the resource config.
     *
     * @param path The path to convert to json.
     * @return The resource as a json object.
     */
    private JSONObject getJSON() {
        try {
            return new JSONObject(
                org.apache.commons.io.IOUtils.toString(
                    this.getResourceAsInputStream(
                        this.RESOURCE_CONFIG_PATH
                    ),
                    "UTF-8"
                )
            );
        } catch(IOException e) {
            return new JSONObject();
        }
    }

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
     * Returns the string value for a JSON key in the resources config.
     *
     * @param key The key.
     * @return The value at key.
     */
    private String getKey(String key) {
        List<String> levels = this.levels(key);

        JSONObject object = this.getJSON();
        for (int i = 0; i < levels.size() - 1; i++) {
            try {
                object = object.getJSONObject(levels.get(i));
            } catch(JSONException e) {
                return "";
            }
        }

        return object.optString(levels.get(levels.size() - 1));
    }
}
