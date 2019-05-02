package configuration.option;

/**
 * Interface that represents a player option.
 *
 * @author Joshua Songy
 * @version 1.0
 */
public interface IGeneralOption extends IOption {

    /**
     * Returns that this is not a player option.
     *
     * @return This is not a player option.
     */
    public default boolean isPlayerOption() {
        return false;
    }
}
