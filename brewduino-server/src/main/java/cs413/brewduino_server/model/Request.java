package cs413.brewduino_server.model;

/**
 * This class represents how a user wants their coffee to be made. The user can
 * select the type of milk, amount of sugar, when they want their coffee and the
 * syrups that they may want as part of their request.
 *
 * @author Aidan O'Grady
 * @since 1.0
 */
public class Request {

    /**
     * How much sugar the user wants.
     */
    private int sugar;

    /**
     * The type of milk (if any) the user wants.
     */
    private Milk milk;

    /**
     * Whether the user wants vanilla syrup/
     */
    private boolean vanillaSyrup;

    /**
     * Whether the user wants caramel syrup/
     */
    private boolean caramelSyrup;

    /**
     * Whether the user wants hazelnut syrup/
     */
    private boolean hazelnutSyrup;

    /**
     * The time the user
     */
    private Time time;

    /**
     * Returns how much sugar the user wants.
     * @return sugar
     */
    public int getSugar() {
        return sugar;
    }

    /**
     * Sets the amount of sugar the user wants.
     * @param sugar - the amount of sugar
     */
    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    /**
     * Returns the type of milk the user wants.
     * @return milk type.
     */
    public Milk getMilk() {
        return milk;
    }

    /**
     * Sets the type of milk the user wants.
     * @param milk - the milk the user wants.
     */
    public void setMilk(Milk milk) {
        this.milk = milk;
    }

    /**
     * Returns whether or not the user wants vanilla syrup.
     * @return vanillaSyrup
     */
    public boolean isVanillaSyrup() {
        return vanillaSyrup;
    }

    /**
     * Sets whether or not the user wants vanilla syrup.
     * @return vanillaSyrup
     */
    public void setVanillaSyrup(boolean vanillaSyrup) {
        this.vanillaSyrup = vanillaSyrup;
    }

    /**
     * Returns whether or not the user wants caramel syrup.
     * @return caramelSyrup
     */
    public boolean isCaramelSyrup() {
        return caramelSyrup;
    }

    /**
     * Sets whether or not the user wants caramel syrup.
     * @return caramelSyrup
     */
    public void setCaramelSyrup(boolean caramelSyrup) {
        this.caramelSyrup = caramelSyrup;
    }

    /**
     * Returns whether or not the user wants hazelnut syrup.
     * @return hazelnutSyrup
     */
    public boolean isHazelnutSyrup() {
        return hazelnutSyrup;
    }

    /**
     * Sets whether or not the user wants hazelnut syrup.
     * @return hazelnutSyrup
     */
    public void setHazelnutSyrup(boolean hazelnutSyrup) {
        this.hazelnutSyrup = hazelnutSyrup;
    }

    /**
     * Returns the time when the user wants their coffee.
     * @return time
     */
    public Time getTime() {
        return time;
    }

    /**
     * Sets the time the user wants their coffee.
     * @param time - the time the user wants.
     */
    public void setTime(Time time) {
        this.time = time;
    }
}
