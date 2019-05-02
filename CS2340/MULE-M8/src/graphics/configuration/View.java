package graphics.configuration;

import configuration.option.IOption;
import java.util.List;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View implements graphics.IView {

    /**
     * Holds list of objects that apply to individual players.
     */
    private List<IOption> playerOptions = null;

    /**
     * Holds list of objects that apply to the entire game..
     */
    private List<IOption> generalOptions = null;

    /**
     * Holds all of the tabs for the configuration window.
     */
    private TabPane tabs = null;

    /**
     * Holds the tab of general settings, this should only be generated once.
     */
    private Tab generalTab = null;

    /**
     * Sets up the view for rendering.
     *
     * @param options A list of options this configuration window can display.
     */
    public View(List<IOption> options) {
        // Create the organizes options array lists.
        this.generalOptions = new java.util.ArrayList<IOption>();
        this.playerOptions = new java.util.ArrayList<IOption>();

        // Organize the options passed in.
        for (IOption option: options) {
            if (option.isPlayerOption()) {
                this.playerOptions.add(option);
            } else {
                this.generalOptions.add(option);
            }
        }
    }

    /**
     * Re-creates all player tabs.
     */
    private void resetTabs() {
        // Delete all the player tabs.
        ObservableList<Tab> tabList= this.tabs.getTabs();
        if (tabList.size() > 1) {
            tabList.remove(1, tabList.size());
        }

        // Create the player tabs.
        for (Tab playerTab: this.playerTabs()) {
            this.tabs.getTabs().add(playerTab);
        }
    }

    /**
     * Create a new tab, settings its title and content to a pane.
     *
     * @param title The title of the newly created tab.
     * @return A newly created tab with an appropriate body.
     */
    private Tab tab(String title) {
        Tab tab = new Tab();
        // Tabs are not closable as the number of tabs is dictated by the
        // number of active players.
        tab.setClosable(false);
        tab.setText(title);
        tab.setStyle("-fx-background: #FFFFFF;");

        return tab;
    }

    /**
     * Create a new general tab.
     *
     * @return A newly created general tab with an appropriate body.
     */
    private Tab tab() {
        Tab generalTab = this.tab("General");

        // Create a gridpane to hold the tab data.
        GridPane content = this.body();

        // Create the combo box for selecting the number of players.
        ComboBox numPlayers = new ComboBox();
        for (int player = 2; player <= 4; player++) {
            numPlayers.getItems().add(player);
        }
        numPlayers.getSelectionModel().selectLast();

        // Make the combo box reset the player tabs when changed.
        numPlayers.setOnAction(e -> {
            // Set the new number of players.
            configuration.Manager.getConfiguration()
                                 .setNumPlayers((int) numPlayers.getValue());

            // Regenerate the player tabs based on the new number of players.
            this.resetTabs();
        });

        // Add the Number of players option to the actual gridpane.
        Label numPlayersLabel = new Label("Number of players: ");
        content.setConstraints(numPlayersLabel, 0, 0);
        content.setConstraints(numPlayers, 1, 0);
        content.getChildren().addAll(numPlayersLabel, numPlayers);
        content.setHalignment(numPlayersLabel, HPos.LEFT);
        content.setHalignment(numPlayers, HPos.RIGHT);

        this.addGeneralOptions(content);
        generalTab.setContent(content);

        return generalTab;
    }

    /**
     * Adds the general options of this view to a GridPane.
     *
     * @param body The gridpane to render against.
     */
    private void addGeneralOptions(GridPane body) {
        for (IOption generalOption: this.generalOptions) {
            // This 0 is arbitrary since general options don't have players.
            Control control = generalOption.generateControl(0);
            this.addOption(body, generalOption.getName(), control);
        }
    }

    /**
     * Adds the player options of this view to a GridPane.
     *
     * @param body The gridpane to render against.
     * @param player The number of the player we are rendering.
     */
    private void addPlayerOptions(GridPane body, int player) {
        for (IOption playerOption: this.playerOptions) {
            Control control = playerOption.generateControl(player);
            this.addOption(body, playerOption.getName(), control);
        }
    }

    /**
     * Adds an option to a gridpane.
     *
     * @param body The gridpane to render to.
     * @param name The name of the option.
     * @param control The control of the option.
     */
    private void addOption(GridPane body, String name, Control control) {
        int column = body.getChildren().size() / 2;
        Label label = new Label(name);
        body.setConstraints(label, 0, column);
        body.setConstraints(control, 1, column);
        body.getChildren().addAll(label, control);
        body.setHalignment(label, HPos.LEFT);
        body.setHalignment(control, HPos.RIGHT);
    }

    /**
     * Creates a new gridpane to render against.
     *
     * @return A new gridpane to render against.
     */
    private GridPane body() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(3);

        // Set the column constraints on the gridpane to make it scale to parent.
        ColumnConstraints labels = new ColumnConstraints();
        labels.setPercentWidth(50);
        pane.getColumnConstraints().add(labels);

        ColumnConstraints values = new ColumnConstraints();
        values.setPercentWidth(50);
        pane.getColumnConstraints().add(values);
        pane.setStyle("-fx-background-color: #f4f4f4;");

        return pane;
    }

    /**
     * Create a new player tab.
     *
     * @param player The number of the player this tab is for.
     * @return A newly created general tab with an appropriate body.
     */
    private Tab tab(int player) {
        Tab playerTab = this.tab("Player " + (player + 1));

        // Create a gridpane to hold the tab data.
        GridPane content = this.body();

        this.addPlayerOptions(content, player);
        playerTab.setContent(content);

        return playerTab;
    }

    /**
     * Generates a new list of player tabs, based on the current of registered
     * players.
     *
     * @return A list of player tabs.
     */
    private List<Tab> playerTabs() {
        int numPlayers = configuration.Manager.getConfiguration()
                                              .getNumPlayers();

        List<Tab> players = new ArrayList<Tab>(numPlayers);
        for(int player = 0; player < numPlayers; ++player) {
            players.add(this.tab(player));
        }

        return players;
    }

    /**
     * Returns the title of the configuration window.
     *
     * @return The title of the configuration window.
     */
    public String getTitle() {
        return "Configure M.U.L.E.";
    }

    /**
     * Renders this view to a scene.
     *
     * @return The rendered scene
     */
    public Scene render() {
        // Create the master tab pane.
        this.tabs = new TabPane();

        // Create the general settings tab.
        this.generalTab = this.tab();
        this.tabs.getTabs().add(generalTab);

        // Create the player tabs.
        this.resetTabs();

        // Create the master window.
        VBox window = new VBox();
        window.setAlignment(Pos.TOP_RIGHT);
        window.setStyle("-fx-background: #d0d0d0;");
        window.getChildren().add(this.tabs);

        // Actually render against the scene.
        return new Scene(window);
    }
}
