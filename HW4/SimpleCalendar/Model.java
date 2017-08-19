/**
 * SimpleCalendar 
 * @author Paul Diaz
 * CS 151
 * Project 4 SimpleCalendar: Model
 * Prof. Kim
 */
import java.util.*;
import javax.swing.event.ChangeListener;
/**
 * Class that represents the Model
 */
public final class Model {
    private int dayMonth;
    private ArrayList<Event> events;
    private GregorianCalendar todaysDate;
    private SimpleCalendar view;
    private ArrayList<ChangeListener> listeners;
    /**
     * Constructor that creates array list of events and date of the events
     */
    public Model(){
        listeners = new ArrayList<>();
        todaysDate = new GregorianCalendar();
        events = new ArrayList<>();
        dayMonth = GregorianCalendar.DAY_OF_MONTH;
    }
    /**
     * Method that goes back to the previous day
     */      
    public void previous() {
        todaysDate.add(dayMonth, -1);
        view.update();
    }
    /**
     * Method that access and models the next day
     */
    public void next() {
        todaysDate.add(Calendar.DAY_OF_MONTH, 1);
        view.update();
    }
    /**
     * Method that sets the date of the event
     * @param day is the 
     */
    public void setDay(int day) {
        todaysDate.set(dayMonth, day);
        view.update();
    }
    /**
     * Method that returns the date
     * @return the GregorianCalendar date
     */  
    public GregorianCalendar getDate() {
        return todaysDate;
    }
    /**
     * Method that adds an event into our model
     * @param event is the event we want to add
     */
    public void addEvent(Event event) {
        events.add(event);
        Collections.sort(events);
        view.clear();
        view.saveAllEvents();
        events.clear();
        view.loadEvents();
    }
    /**
     * Method that returns the events
     * @return the array list of events
     */    
    public ArrayList<Event> getEvents() {
        return events;
    }
    /**
     * Method that sets the view
     * @param v is the view to be set into
     */
    public void addView(SimpleCalendar v) {
        this.view = v;
    }
    /**
     * Another version of addEvent method
     * @param event is the event to be added in the array list
     */
    public void addEventLoad(Event event) {
        events.add(event);
        Collections.sort(events);
    }
}
