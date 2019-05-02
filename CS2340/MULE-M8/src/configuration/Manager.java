package configuration;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;

/**
 * Singleton manager for the active configuration.
 *
 * @author Joshua Songy
 * @version 1.2
 */
public class Manager {

    private static String readFile(String path,
                                   Charset encoding) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    /**
     * The configuration singleton.
     */
    private static IConfiguration configuration = null;

    /**
     * A private constructor so a manager can never be created.
     */
    private Manager() {
    }

    /**
     * Returns the global configuration instance.
     *
     * @return The global configuration instance.
     */
    public static IConfiguration getConfiguration() {
        if (configuration == null) {
            try{
                configuration = new Configuration(readFile("save.json", Charset.defaultCharset()));
            } catch (Exception e) {
                configuration = new Configuration(
                    resources.Manager
                             .getResources()
                             .getJSON(
                                resources.Manager
                                         .getResources()
                                         .getBaseConfigPath()
                            )
                );
            }
        }

        return configuration;
    }
}
