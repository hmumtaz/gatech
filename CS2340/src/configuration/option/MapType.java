package configuration.option;
import configuration.IConfiguration;
import javafx.scene.control.*;

/**
 * Option for selecting dificulty.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public class MapType implements IGeneralOption {

    /**
     * The configuration this option controls.
     */
    private final IConfiguration conf;

    /**
     * Creates a new difficulty that edits the target configuration.
     *
     * @param configuration The configuration to edit.
     */
    public MapType(IConfiguration configuration) {
        this.conf = configuration;
    }

    /**
     * The name of this option.
     *
     * @return The name of this option.
     */
    public String getName() {
        return "Random map";
    }

    /**
     * Generate a control for difficulty.
     *
     * @param player Ignored.
     * @return A new control for setting difficulty.
     */
    public Control generateControl(int player) {
        // Create a new checkbox control.
        CheckBox check = new CheckBox();

        // When something is chosen, set that option for a player.
        check.setOnAction(e -> {
            conf.getJSON()
                .putOpt("general.map.type", check.isSelected());
        });

        // Returns the new combos control
        return check;
    }
}
