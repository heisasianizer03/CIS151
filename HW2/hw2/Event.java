/**
 * My First Calendar
 * @author Paul Diaz
 * CS 151
 * Project 2 Calendar: Event
 * Prof. Kim
 */
import java.util.*;

/**
 * This class represents an event
 */
public class Event{
    private final GregorianCalendar todaysDate;     //holds the date of the event
    private final String eventName;                 //name of the event
    private final int start;                        //start time of the event
    private final int end;                          //end time of the event
    /**
     * Constructor that construct an event object
     * @param date is the date of the event
     * @param eventName is the name of the event
     * @param start is the start time of the event
     * @param end is the end time of the event
     */
    public Event(GregorianCalendar date, String eventName, int start, int end ){
        this.todaysDate = date;
        this.eventName = eventName;
        this.start = start;
        this.end = end;
    }
    /**
     * Method that returns the date of the event
     * @return GregorianCalendar date
     */
    public GregorianCalendar getDate(){
        return todaysDate;
    }
    /**
     * Method that return the name of the event
     * @return the name of the event
     */
    public String getEventName(){
        return eventName;
    }
    /**
     * Method that returns the start time of the event
     * @return the start time of the event
     */
    public int getStartTime(){
        return start;
    }
    /**
     * Method that returns the end time of the event
     * @return the end time of the event
     */
    public int getEndTime(){
        return end;
    }
}
