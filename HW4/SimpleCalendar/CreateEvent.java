/**
 * SimpleCalendar 
 * @author Paul Diaz
 * CS 151
 * Project 4 SimpleCalendar: CreateEvent
 * Prof. Kim
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class CreateEvent{
    private final JFrame frame;
    
    private final JPanel panelN;
    private final JPanel panelS;
    private final GregorianCalendar calendar;
    
    private final Model model;
    private final JButton saveButton;    
    private final SimpleDateFormat format;
    
    private final JTextField eventName;
    private final JTextField theDate;
    private final JTextField theStart;
    private final JTextField theEnd;
    private Event eve;
   
    public CreateEvent(final Model m) {
        frame = new JFrame();
        model = m;
        eve = null;
        frame.setLayout(new BorderLayout());
        
        panelN = new JPanel();
        panelS = new JPanel();
        
        calendar = model.getDate();
        
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mmaa");
        GregorianCalendar formatEnd = new GregorianCalendar();
        formatEnd.setTime(calendar.getTime());
        formatEnd.add(GregorianCalendar.MINUTE, 30);
        currentTime.format(calendar.getTime());
        
        eventName = new JTextField("Untitled event");
        eventName.setBorder(BorderFactory.createLineBorder(Color.blue));
        eventName.setPreferredSize(new Dimension(300, 20));
        
        String theDateMessage = (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR);
        theDate = new JTextField(theDateMessage);
        theStart = new JTextField(currentTime.format(calendar.getTime()));
        theEnd = new JTextField(currentTime.format(formatEnd.getTime()));
                      
        format = new SimpleDateFormat("MM/dd/yyyyhh:mmaa");
                
        //------------- saveButton --------------------
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Date startDate = null;
                
                String name = eventName.getText();
                String date = theDate.getText();
                String start = theStart.getText();
                String end = theEnd.getText();
               
                String[] data = new String[3];
                data = date.split("/");
                
                try{
                    startDate = format.parse(date + start);
                }catch(ParseException exception) {}
                    Date endDate = null;
                
                try{
                    endDate = format.parse(date + end);
                }catch(ParseException exception){}
                
                GregorianCalendar startCal = new GregorianCalendar();
                startCal.setTime(startDate);
                GregorianCalendar endCal = new GregorianCalendar();
                endCal.setTime(endDate);
           
                int year = Integer.valueOf(data[2]);
                int month = Integer.valueOf(data[1]);
                int day = Integer.valueOf(data[0]);
        
                GregorianCalendar gc = new GregorianCalendar(year,month,day);
                Event eventNew = new Event(gc, name, startCal, endCal);
                eve = eventNew;
        
                boolean timeConflict = false;
                if(eventNew.getEndTime().before(eventNew.getStartTime()) || eventNew.getStartTime().equals(eventNew.getEndTime())) {
                    JOptionPane.showMessageDialog(frame, "Opps! time conflict", "",JOptionPane.WARNING_MESSAGE);
                    timeConflict = true;
                }
                for(Event event : model.getEvents()) {
                    if(event.compareTo(eventNew) == 0) {
                        JOptionPane.showMessageDialog(frame, "Opps! time conflict!", "",JOptionPane.WARNING_MESSAGE);
                        timeConflict = true;
                        break;
                    }
                }          
                if(!timeConflict) { model.addEvent(eventNew);}
                frame.dispose();
            }
        });
        panelN.add(eventName);
        
        panelS.add(theDate);
        panelS.add(theStart);
        panelS.add(theEnd);
        panelS.add(saveButton); 
        
        frame.add(panelN, BorderLayout.NORTH);
        frame.add(panelS, BorderLayout.SOUTH);
        
        frame.pack();
        frame.setVisible(true);

    }
}
