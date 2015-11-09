package cs413.brewduino_server.model;

/**
 * The user is able to request various types of milk. This stores all the
 * possibilities the user has.
 *
 * @author Aidan O'Grady
 * @since 1.0
 */
public enum Milk {
    /**
     * The user wants no milk.
     */
    NONE,

    /**
     * The user wants skimmed milk.
     */
    SKIMMED,

    /**
     * The user wants semi-skimmed milk.
     */
    SEMI_SKIMMED;
}
