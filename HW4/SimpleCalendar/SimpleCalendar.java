/**
 * SimpleCalendar 
 * @author Paul Diaz
 * CS 151
 * Project 4 SimpleCalendar: SimpleCalendar
 * Prof. Kim
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.*;
import javax.swing.border.EmptyBorder;

/**
 * Class that simulates the calendar
 */
public class SimpleCalendar {
    public static void main(String[] args){
        Model model = new Model();
        SimpleCalendar simple = new SimpleCalendar(model);
        model.addView(simple);        
    }
    private final JFrame frame;
    private Model model;
    
    private final JButton create;
    private final JButton backB;
    private final JButton nextB;
    private final JButton quit;
    
    private final JPanel buttons;
    
    private final Calendar calendar;
    private final JLabel monthLabel;
    private final JLabel eventLabel;
    private final JPanel monthPanel;
    private final JPanel eventsPanel;
    private final JPanel wholeMonth;
    private final JPanel wholeEvent;
    private final String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private final int daysInMonth;
   
    public SimpleCalendar(final Model m) {
        model = m;
        frame = new JFrame();
        buttons = new JPanel();
        calendar = model.getDate();
        monthLabel = new JLabel();
        eventLabel = new JLabel();
        daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        wholeMonth = new JPanel(); 
        wholeEvent = new JPanel();
        wholeMonth.setLayout(new BoxLayout(wholeMonth, BoxLayout.Y_AXIS));
        monthPanel = new JPanel();
        monthPanel.setLayout(new GridLayout(0, 7, 5, 5));
        monthPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
        
        //--------- Create Button -----------
        create = new JButton("Create");
        create.setBorderPainted(false);
        create.setBackground(Color.red);
        create.setOpaque(true);
        
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateEvent create = new CreateEvent(model);
                //update();
            }     
        });
        buttons.add(create);
        
        //---------- Back Button --------------
        backB = new JButton("<");
        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.previous();
            }
        });        
        buttons.add(backB);   
        
        //----------- Next Button ---------------
        nextB = new JButton(">");
        nextB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.next();
            }
        });
        buttons.add(nextB);
     
        //----------- Quit Button ----------------
        quit = new JButton("Quit");
        quit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAllEvents();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        buttons.add(quit);
        
        frame.add(buttons, BorderLayout.NORTH);//We add all buttons in JPanel
        wholeMonth.add(monthLabel);
        wholeMonth.add(monthPanel);
        
        //After buttons are all created, we print 
        monthPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        printMonth(monthPanel);
        
        JScrollPane scroll = new JScrollPane();
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.PAGE_AXIS));
        eventsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        eventsPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        eventsPanel.setBackground(Color.CYAN);
        
        //Here we print all events 
        printEvents(eventsPanel);
        
        scroll.getViewport().add(eventsPanel);
        scroll.setPreferredSize(new Dimension(300, 200));
        scroll.setVerticalScrollBarPolicy(ScrollPaneLayout.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(wholeMonth, BorderLayout.WEST);
        frame.add(scroll, BorderLayout.EAST);
        
        //------Here we load all events --------
        loadEvents();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * Method that prints the calendar 
     * @param monthPanel is the JPanel where we print the month
     */
    public void printMonth(JPanel monthPanel) {      
        for( int i = 0; i < 7; i++) {
            JLabel day = new JLabel(" " + days[i] + " ");
            monthPanel.add(day);
        } 
        monthLabel.setText(new SimpleDateFormat("MMM yyyy").format(calendar.getTime()));
        GregorianCalendar startDay = new GregorianCalendar(calendar.get(Calendar.YEAR), 
                calendar.get(Calendar.MONTH), 1);
        int start = startDay.get(Calendar.DAY_OF_WEEK);
        
        for(int i = 1; i < daysInMonth + start; i++) {
            if(i < start) {
                final JLabel day = new JLabel("");     
                monthPanel.add(day);
            }else{
                int dayNumber = i - start + 1;
                final JLabel day = new JLabel(dayNumber+"");
                day.addMouseListener(new MouseListener(){   
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int num = Integer.parseInt(day.getText());
                        model.setDay(num);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                });
                if(dayNumber == calendar.get(Calendar.DAY_OF_MONTH)) {
                    day.setBorder(BorderFactory.createLineBorder(Color.blue));
                }
                monthPanel.add(day);
            }
        }
    }
    /**
     * Method that prints days
     * @param dayPanel is the panel we use to put all the day
     */
    public void printEvents(JPanel dayPanel) {  
        SimpleDateFormat simpleFormat = new SimpleDateFormat("hh:mm aa");           
        ArrayList<Event> events = model.getEvents();
  
        for(Event e: events){
            if(e.getStartTime().get(e.getStartTime().DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                if(e.getDate().get(e.getDate().MONTH) == calendar.get(calendar.MONTH)){
                    Date startDate = e.getStartTime().getTime();
                    Date endDate = e.getEndTime().getTime();
                    String eve = e.getEventName() + ", at " + simpleFormat.format(startDate) + " to " + simpleFormat.format(endDate);
                    dayPanel.add(new JLabel(eve));
                }
            }
        }
    } 
    /**
     * Method that updates the whole window that contains monthPanel and dayPanel
     */
    public void update(){
        //we clear monthPanel
        monthPanel.removeAll();
        //re-printing monthPanel
        printMonth(monthPanel);
        //these two methods of JPanel are needed to
        //view JPanel updated
        monthPanel.revalidate();
        monthPanel.repaint();
        
        //we clear dayPanel as well
        eventsPanel.removeAll();
        //re-printing dayPanel
        printEvents(eventsPanel);
        //these two methods of JPanel are needed to
        //view JPanel updated
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }
    /**
     * Method that creates file and saves all events into it
     */
    public void saveAllEvents(){
        System.out.println("Save");
        String message = "";
        ArrayList<Event> eve = model.getEvents();  
        //We extract all events and put it in String
        for(int i = 0; i < eve.size(); i++){
            Event anEve = eve.get(i);
            message += anEve.getEventName() + "," + 
                anEve.getStartTime().get(anEve.getStartTime().MONTH) + "," +
                anEve.getStartTime().get(anEve.getStartTime().DATE) + "," +
                anEve.getStartTime().get(anEve.getStartTime().YEAR) + "," +
                    
                anEve.getStartTime().get(anEve.getStartTime().HOUR) + "," +
                anEve.getStartTime().get(anEve.getStartTime().MINUTE) + "," +
                
                anEve.getEndTime().get(anEve.getEndTime().HOUR) + "," +
                anEve.getEndTime().get(anEve.getEndTime().MINUTE) + "\n";
        }
        try{
            File fileQ = new File("events.txt");//we create a file first
                    
            if(!fileQ.exists()){ 
                fileQ.createNewFile();
            }              
            //we start to write all events into a file
            FileWriter writerQ = new FileWriter(fileQ.getAbsoluteFile());
            try (
                BufferedWriter bWriterQ = new BufferedWriter(writerQ)) {
                bWriterQ.write(message);
                bWriterQ.close();    //closing the file is very important
            }catch(Exception ex1){
                System.out.println("Error: " + ex1);
            }      
        }catch(Exception ex2){
            System.out.println("Error: " + ex2);
        }
    }
    /**
     * Method that loads all the events from events.txt file
     */
    public void loadEvents(){
        String line;
        File file = new File("events.txt");
        if(file.exists()){
            String[] data = new String[8];
            try{
                Scanner scan = new Scanner(file);
                while(scan.hasNextLine()){
                    line = scan.nextLine();
                    data = line.split(",");
                    
                    String nameOfEvent = data[0];
                    int month = Integer.valueOf(data[1]);
                    int day = Integer.valueOf(data[2]);
                    int year = Integer.valueOf(data[3]);
                    int starthour = Integer.valueOf(data[4]);
                    int startmin = Integer.valueOf(data[5]);
                    int endhour = Integer.valueOf(data[6]);
                    int endmin = Integer.valueOf(data[7]);

                    GregorianCalendar greg = new GregorianCalendar(year, month, day, starthour, startmin);
                    GregorianCalendar gc = new GregorianCalendar(year, month, day, endhour, endmin);
                    
                    Event event = new Event(greg, nameOfEvent, greg, gc);
                    model.addEventLoad(event);
                    update();//We update the whole calendar after adding all events form txt 
                }
            }catch(FileNotFoundException | NumberFormatException message){
                System.out.println("\nError: " + message  + "\n");
            }
        }
    }
    /**
     * Method that clears the events file
     */
    public void clear(){
        try{
            File fileQ = new File("events.txt");//we create a file first
            String message = "";
            if(!fileQ.exists()){ 
                fileQ.createNewFile();
            }              
            //we start to write all events into a file
            FileWriter writerQ = new FileWriter(fileQ.getAbsoluteFile());
            try (
                BufferedWriter bWriterQ = new BufferedWriter(writerQ)) {
                bWriterQ.write(message);
                bWriterQ.close();    //closing the file is very important
            }catch(Exception ex1){
                System.out.println("Error: " + ex1);
            }      
        }catch(Exception ex2){
            System.out.println("Error: " + ex2);
        }
    }
}
