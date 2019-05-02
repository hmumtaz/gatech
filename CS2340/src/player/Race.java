package player;

/**
 * @author Janet Liang
 * @version 1.0
 */
public enum Race {

    HUMAN(600),
    FLAPPER(1600),
    BONZOID(1000),
    UGAITE(1000),
    BUZZITE(1000);

    /**
     * The players starting money.
     */
    private int startingMoney;

    /**
     * Sets the starting money for each race, respectively.
     * Human, 600
     * Flapper, 1600
     * Bonzoid, 1000
     * Ugaite, 1000
     * Buzzite, 1000
     *
     * @param amount The amount of money the race has.
     */
    Race(int amount) {
        startingMoney = amount;
    }

    /**
     * Converts an integer to a race.
     *
     * @param race The integer to convert.
     * @return The race.
     */
    public static Race toRace(Integer race) {
        if (race == null) {
            return HUMAN;
        }

        switch (race) {
            case 1:
                return HUMAN;
            case 2:
                return FLAPPER;
            case 3:
                return BONZOID;
            case 4:
                return UGAITE;
            case 5:
                return BUZZITE;
            default:
                return HUMAN;
        }
    }

    /**
     * Gets the integer representation of this race.
     *
     * @return The integer representation of this race.
     */
    public int getRace() {
        switch (this) {
            case HUMAN:
                return 1;
            case FLAPPER:
                return 2;
            case BONZOID:
                return 3;
            case UGAITE:
                return 4;
            case BUZZITE:
                return 5;
            default:
                return 1;
        }
    }

    /**
     * Get starting money for each race.
     *
     * @return startingMoney
     */
    public int getStartingMoney() {
        return startingMoney;
    }
}
