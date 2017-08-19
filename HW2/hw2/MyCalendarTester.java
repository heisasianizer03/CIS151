/**
 * My First Calendar
 * @author Paul Diaz
 * CS 151
 * Project 2 Calendar: MyCalendarTest
 * Prof. Kim
 */
import java.io.*;
import java.text.DateFormatSymbols;
import java.util.*;

public class MyCalendarTester {
   
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Calendar calendar = new Calendar();
       
        GregorianCalendar gc = new GregorianCalendar();//this will print a Calendar of the current Month
        calendar.printMonth(gc);
        boolean done = true;
        String line;
        
        System.out.println();
        while(done){    
            System.out.println("Select one of the following options");
            System.out.print("[L]oad [V]iew [C]reate [G]o [E]vent list [D]elete [Q]uit: ");
            String choice = input.nextLine();
            choice = choice.toLowerCase();
            
            switch(choice){
                case "l":
                    File file = new File("events.txt");
                    if(file.exists()){
                        String[] data = new String[6];
                        try{
                            Scanner scan = new Scanner(file);
                            while(scan.hasNextLine()){ //extracting all information from the file
                                line = scan.nextLine();//application is currently reading
                                data = line.split(",");
                                
                                int year = Integer.valueOf(data[3]);
                                int month = Integer.valueOf(data[1]);
                                int day = Integer.valueOf(data[2]);
                                int start = Integer.valueOf(data[4]);
                                int end = Integer.valueOf(data[5]);
                                String nameOfEvent = data[0];
                            
                                //System.out.print(year + " " + month + " " + day + " " + start + " " + end + " " + nameOfEvent+ "\n");
                            
                                GregorianCalendar greg = new GregorianCalendar(year, month -1, day);                               
                                Event events = new Event(greg, nameOfEvent, start, end);
                            
                                calendar.create(events);
                            }
                            System.out.println("\nDone Loading\n");
                        }catch(FileNotFoundException | NumberFormatException message){
                            System.out.println("\nError: " + message  + "\n");
                        }
                    }
                    break;
                case "v":
                     boolean done1 = false;
                    String choiceView = "";
                
                    while(!done1){
                        System.out.print("\n[D]ay view [M]onth view: ");
                        choiceView = input.nextLine();
                        choiceView = choiceView.toLowerCase();
                    
                        if(choiceView.equals("d") || choiceView.equals("m")){
                            calendar.view(choiceView);
                            done1 = true;
                        }else{
                            System.out.println("Invalid input.");
                            done1 = false;
                        }
                    }
                    System.out.println();
                    break;
                    
                case "c":
                    System.out.println();
                    System.out.print("Enter date (MM/DD/YYYY): ");
                    String date = input.nextLine();
                    String [] stringDate = date.split("/");
                
                    int year = Integer.valueOf(stringDate[2]);
                    int month = Integer.valueOf(stringDate[0]);
                    int day = Integer.valueOf(stringDate[1]);
                    
                    System.out.print("Event name: ");
                    String eventName = input.nextLine();
                    
                    System.out.print("Start time (1300 is 1:00): ");
                    String startS = input.nextLine();
                    int start = Integer.parseInt(startS);
                
                    System.out.print("End time (1300 is 1:00): ");
                    String endS = input.nextLine();
                    int end = Integer.parseInt(endS);
                    //System.out.println(start + " " + end);
                    GregorianCalendar gg = new GregorianCalendar(year, month -1, day);
                    Event newEvent = new Event(gg, eventName, start, end);
                    int weekDay = gg.get(gg.DAY_OF_WEEK);

                    String weekDayString = new DateFormatSymbols().getWeekdays()[weekDay];
                    boolean doneAdd = calendar.create(newEvent);
                    //calendar.create(newEvent);
                    String monthInWords = new DateFormatSymbols().getMonths()[month -1];
                
                    if(doneAdd){
                        System.out.print("\n" + weekDayString + ", " + monthInWords + " " + day + ", " + year);
                        System.out.println("\n" + eventName + " " + calendar.convertTime(start)+ " - " + calendar.convertTime(end));
                    }else{
                        System.out.println("\nFailed creating event");
                    }
                    System.out.println(); 
                    break;
                case "g":
                    System.out.println();
                    System.out.print("Enter date (MM/DD/YYYY): ");
                    String dateG = input.nextLine();
                    String [] stringDateG = dateG.split("/");
                
                    int yearG = Integer.valueOf(stringDateG[2]);
                    int monthG = Integer.valueOf(stringDateG[0]);
                    int dayG = Integer.valueOf(stringDateG[1]);
                    monthG -=1;
                    GregorianCalendar ggG = new GregorianCalendar(yearG, monthG, dayG);
                    calendar.go(ggG);
                    System.out.println();
                    break;
                case "e":
                    System.out.println();
                    System.out.println(calendar.eventList());
                    System.out.println();
                    break;
                case "d":
                    System.out.println();
                    boolean doneD = false;

                    while(!doneD){
                        System.out.print("[S]elected [A]ll: ");
                        String choiceD = input.nextLine();
                        choiceD = choiceD.toLowerCase();
                    
                        if(choiceD.equals("s")){
                           
                            String deleted = calendar.delete(choiceD);
                            if(deleted.length() > 0){
                                System.out.println("Event(s) on " + deleted + " deleted.");
                                doneD = true;
                            }else{
                                System.out.println("\nDelete failed or event doesn't exists.\n");
                            }
                        }else if(choiceD.equals("a")){
                                  
                            String deleted = calendar.delete(choiceD);
                            if(deleted.length() > 0){
                                System.out.println("All events are deleted.");
                                doneD = true;
                            }else{
                                System.out.println("\nDelete failed or event doesn't exists.\n");
                            }
                        }else{
                            System.out.println("Invalid input");
                        }
                    }
                    System.out.println();
                    break;
                case "q":
                    String calendarEvents = calendar.saveAllEvents();
                
                    try{
                        File fileQ = new File("events.txt");//we create a file first
                    
                        if(!fileQ.exists()){ 
                            fileQ.createNewFile();
                        }   
                    
                        //we start to write our plane reservations into a file
                        FileWriter writerQ = new FileWriter(fileQ.getAbsoluteFile());
                        try (
                            BufferedWriter bWriterQ = new BufferedWriter(writerQ)) {
                            bWriterQ.write(calendarEvents);
                            bWriterQ.close();    //closing the file is very important
                        }catch(Exception ex1){
                            System.out.println("Error: " + ex1);
                        }      
                    }catch(Exception ex2){
                        System.out.println("Error: " + ex2);
                    }
                    done = false;
                    break; 
            }
        }
        System.out.println("\nGood bye.\nProgrammed by: Paul Diaz\n");
    }
}