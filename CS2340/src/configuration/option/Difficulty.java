package configuration.option;
import configuration.IConfiguration;
import javafx.scene.control.*;

/**
 * Option for selecting dificulty.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public class Difficulty implements IGeneralOption {

    /**
     * The configuration this option controls.
     */
    private final IConfiguration conf;

    /**
     * Creates a new difficulty that edits the target configuration.
     *
     * @param configuration The configuration to edit.
     */
    public Difficulty(IConfiguration configuration) {
        this.conf = configuration;
    }

    /**
     * The name of this option.
     *
     * @return The name of this option.
     */
    public String getName() {
        return "Difficulty";
    }

    /**
     * Generate a control for difficulty.
     *
     * @param player Ignored.
     * @return A new control for setting difficulty.
     */
    public Control generateControl(int player) {
        // Create a new combos control.
        ComboBox combos = new ComboBox();
        combos.getItems().addAll(
            "Beginner",
            "Standard",
            "Tournament"
        );

        // Make sure the first option is selected.
        combos.getSelectionModel().select(conf.get("general.difficulty"));

        // When something is chosen, set that option for a player.
        combos.setOnAction(e -> {
            conf.set("general.difficulty", combos.getValue());
        });

        // Returns the new combos control
        return combos;
    }
}
