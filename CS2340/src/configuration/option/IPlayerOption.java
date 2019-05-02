package configuration.option;

/**
 * Interface that represents a player option.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public interface IPlayerOption extends IOption {

    /**
     * Returns that this is a player option.
     *
     * @return This is a player option.
     */
    public default boolean isPlayerOption() {
        return true;
    }
}
