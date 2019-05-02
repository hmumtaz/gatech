package configuration.option;

import javafx.scene.control.Control;

/**
 * This class represents an option in the auto-generated configuration menu.
 * The configuration menu is generated from a list of options.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public interface IOption {

    /**
     * Reports whether this options represents an option that gets applied to a
     * player. If this returns false, this option is assumed to be a more
     * general system settings.
     *
     * @return boolean Whether this is a player option.
     */
    boolean isPlayerOption();

    /**
     * Returns the name of this option.
     *
     * @return string The name of this option.
     */
    String getName();

    /**
     * Returns a newly created javafx control object for use by the
     * configuration menu. The configuration menu is allowed to call this an
     * arbitrary number of times, and it expects a different object reference
     * every time. This control object is responsible for its own interaction
     * with the user.
     *
     * @param player The player this control is for. This is 0 if a general
     *               option.
     * @return control A new control object for this option
     */
    Control generateControl(int player);
}
