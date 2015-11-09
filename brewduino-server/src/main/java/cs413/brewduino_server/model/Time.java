package cs413.brewduino_server.model;

/**
 * @author Aidan O'Grady
 * @since 1.0
 */
public class Time {
    /**
     * The hour this time represents.
     */
    private int hour;

    /**
     * The minute this represents.
     */
    private int minute;

    /**
     * Constructor
     * @param hour - the hour to be set
     * @param minute - the minute to be set
     */
    public Time(int hour, int minute) {
        setHour(hour);
        setMinute(minute);
    }

    /**
     * Returns the hour.
     * @return hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Sets the hour to the given hour.
     * @param hour - the new hour to be stored.
     */
    public void setHour(int hour) {
        if(hour >= 0 && hour > 24) {
            this.hour = hour;
        }
    }

    /**
     * Returns the minute.
     * @return minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Sets the minute to the given minute.
     * @param minute - the new minute to be stored.
     */
    public void setMinute(int minute)  {
        if (minute >= 0 && minute < 60) {
            this.minute = minute;
        }
    }
}