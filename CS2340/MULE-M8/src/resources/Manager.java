package resources;

/**
 * Singleton class for the resource manager.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public class Manager {

    /**
     * Our resources singleton.
     */
    private static final IResources resources = new Resources();

    /**
     * Private constructor so that our manager can never be insantiated.
     */
    private Manager() {}

    /**
     * Returns the resources instance.
     *
     * @return The resources instance.
     */
    public static IResources getResources() {
        return resources;
    }
}
