/**
 * SimpleCalendar 
 * @author Paul Diaz
 * CS 151
 * Project 4 SimpleCalendar: Event
 * Prof. Kim
 */
import java.util.*;
/**
 * This class represents an event
 */
public class Event implements Comparable<Event> {
    private GregorianCalendar todaysDate;
    private final String eventName;
    private final GregorianCalendar start;
    private final GregorianCalendar end;
    public Event(){
        todaysDate = new GregorianCalendar();
        eventName = "";
        start = todaysDate;
        end = todaysDate;
    }
    /**
     * Constructor that construct an event object
     * @param date is the date of the event
     * @param eventName is the name of the event
     * @param start is the start time of the event
     * @param end is the end time of the event
     */
    public Event(GregorianCalendar date, String eventName, GregorianCalendar start, GregorianCalendar end){
        this.todaysDate = date;              //holds the date of the event
        this.eventName = eventName;          //name of the event
        this.start = start;                  //start time of the event
        this.end = end;                      //end time of the event
    }
    /**
     * Method that return the name of the event
     * @return the name of the event
     */
    public String getEventName(){
        return eventName;
    }
    
    public GregorianCalendar getDate(){
        return todaysDate;
    }
    /**
     * Method that returns the start time of the event
     * @return the start time of the event
     */
    public GregorianCalendar getStartTime(){
        return start;
    } 
    /**
     * Method that returns the end time of the event
     * @return the end time of the event
     */
    public GregorianCalendar getEndTime(){
        return end;
    }
    /**
     * Method that compares two events
     * requirement so we can we implement Comparable
     * @param event1 is the second event to compared with the caller
     * @return 0 if equal, non-zero otherwise (1 if object 1 is comes first than object2,
     * -1 the other way around)
     */
    @Override
    public int compareTo(Event event1) {
        if(event1.getStartTime().before(start) && event1.end.before(start)) {
            return 1;
        }else if(event1.start.after(end) && event1.end.after(end)) {
            return -1;
        }else {
            return 0;
        }
    }
}
