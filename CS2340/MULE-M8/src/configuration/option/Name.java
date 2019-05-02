package configuration.option;
import configuration.IConfiguration;
import javafx.scene.control.*;

/**
 * Option for selecting dificulty.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public class Name implements IPlayerOption {

    /**
     * The configuration this option controls.
     */
    private final IConfiguration conf;

    /**
     * Creates a new difficulty that edits the target configuration.
     *
     * @param configuration The configuration to edit.
     */
    public Name(IConfiguration configuration) {
        this.conf = configuration;
    }

    /**
     * The name of this option.
     *
     * @return The name of this option.
     */
    public String getName() {
        return "Name";
    }

    /**
     * Generate a control for difficulty.
     *
     * @param player Ignored.
     * @return A new control for setting difficulty.
     */
    public Control generateControl(int player) {
        // Create a new text field control.
        TextField field = new TextField(conf.get("player." + player + ".name").toString());

        // When something is chosen, set that option for a player.
        field.setOnAction(e -> {
            conf.set("player." + player + ".name", field.getText());
        });

        // Returns the new combos control
        return field;
    }
}
