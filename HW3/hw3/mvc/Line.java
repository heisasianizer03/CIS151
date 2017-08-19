/**
 * MVC - Line class borrowed from Horstmann
 * @author Paul Diaz
 * CS 151 
 * Prof. Kim
 */
import java.util.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//Class that acts as the MODEL
public class Line {
    private ArrayList<String> text;
    private ArrayList<ChangeListener> listeners;
    
    /**
      Constructs a DataModel object
   */
    public Line(){
        text = new ArrayList<>();
        listeners = new ArrayList<>();
    }
    
    /**
      Adds an text to the ArrayList String
      @param text the text to be added
    */
    public void addText(String text){
        this.text.add(text);
        
        ChangeEvent event = new ChangeEvent(this);
        for(ChangeListener listener: listeners){
            listener.stateChanged(event);
        }
    }
    /** Method that takes all the string in our ArrayList
     *  so we can actually print in the frame
     *  @return a return representation of all the values in ArrayList
     */
    public String getLine(){
        String line = "";
      
        for(String str: text ){		
            line += str + "\n";	
        }   
        return line;
    }
    /**
      Attach a listener to the Model
      @param listener the listener
    */
    public void attach(ChangeListener listener){
        listeners.add(listener);
    }
}
