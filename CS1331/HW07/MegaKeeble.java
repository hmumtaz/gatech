/**
 * Represents a Mega Keeble card.
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class MegaKeeble extends Card {
    /**
    * Constructs a Mega Keeble object. It is not scorable.
    */
    public MegaKeeble() {
        super("Mega Keeble", "Add 5 to the current keeble count.",
                "A massive keeble out to bake a giant cookie.",
                false, 4);
    }

    /**
    * Gives the player 3 Keebles.
    *
    * @param p the game object to affect
    */
    public void play(PlasterClash p) {
        super.play(p);
        playToPlayZone(p);
        p.incKeebles();
        p.incKeebles();
        p.incKeebles();
        p.incKeebles();
    }

}