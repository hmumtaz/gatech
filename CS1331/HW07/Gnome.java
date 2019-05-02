/**
 * Represents a Gnome card.
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class Gnome extends Card {
    /**
     * Constructs a Gnome object. It is scorable.
     */
    public Gnome() {
        super("Gnome", "Add 1 to the conspiracy meter.",
                "A desirable Gnome, prime for capture.",
                true, 8);
    }

    /**
     * Gives the player 1 Gnome.
     *
     * @param p the game object to affect
     */
    public void play(PlasterClash p) {
        super.play(p);
        for (Player player : p.opponents()) {
            if (player.hasHand()) {
                player.discardRandom();
            }
        }
    }
}