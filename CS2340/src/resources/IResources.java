package resources;

import java.io.InputStream;
import java.net.URL;
import javafx.scene.image.Image;
import org.json.JSONObject;

/**
 * Class that allows code to retrieve game resources.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public interface IResources {

    /**
     * Returns a URL that represents the given resource.
     *
     * @param path The path to retrieve
     * @return The url for `path`
     */
    public URL getResource(String path);

    /**
     * Returns an input stream for the given resource.
     *
     * @param path The path to retrieve
     * @return The input stream for `path`
     */
    public InputStream getResourceAsInputStream(String path);

    /**
     * Returns the path of the initial configuration of the program.
     *
     * @return That path to the base configuration to the game.
     */
    public String getBaseConfigPath();

    /**
     * Returns the graphics path for maps.
     *
     * @return The path for graphical images for the game.
     */
    public String getMapGraphicPath();

    /**
     * Returns a JSON object for a resource path.
     *
     * @param path The path to convert to json.
     * @return The resource as a json object.
     */
    public JSONObject getJSON(String path);

    /**
     * Returns the image for a map tile.
     *
     * @param file The map tile file name.
     * @return The image for a map tile.
     */
    public Image getMapImage(String file);

    /**
     * Returns the graphics path for town images.
     *
     * @return The path for graphical images for the game.
     */
    public String getTownGraphicPath();

    /**
     * Returns the graphics path for town images.
     *
     * @return The path for graphical images for the game.
     */
    public String getGraphicPath();

    /**
     * Returns the image for a file.
     *
     * @param file The map tile file name.
     * @return The image for a map tile.
     */
    public Image getImage(String file);

    /**
     * Returns the image for a town graphic.
     *
     * @param file The map tile file name.
     * @return The image for a map tile.
     */
    public Image getTownImage(String file);
}
