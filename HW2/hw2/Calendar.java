/**
 * My First Calendar
 * @author Paul Diaz
 * CS 151
 * Project 2 Calendar: Calendar
 * Prof. Kim
 */
import java.util.*;
import java.text.*;
/**
 * This class represent a Calendar
 */
public class Calendar implements Comparator<GregorianCalendar>{
    Scanner input = new Scanner(System.in);
    
    private final GregorianCalendar gc;
    private ArrayList<ArrayList<Event>> eventArrayList;
    
    /**
     * Constructor
     */
    public Calendar(){
        gc = new GregorianCalendar();
        eventArrayList = new ArrayList<>();
    }
        
    /**
     * This function will view 
     * @param choice is user's choice if calendar viewed by day or by month
     */ 
    public void view(String choice){  
        System.out.println();
        boolean done= false;
        boolean done2 = false;
        ArrayList<Event> messageArrayList = new ArrayList();

        if(choice.equals("d")){
            boolean doneD = false;
            int i = 0;
            
            String month = new DateFormatSymbols().getMonths()[gc.get(gc.MONTH)];
            System.out.println(month + " " + gc.get(gc.DATE) + ", " + gc.get(gc.YEAR));
            
            while(!doneD && i < eventArrayList.size()){
                GregorianCalendar g2 = eventArrayList.get(i).get(0).getDate();
                if(compare(gc, g2) == 0){
                    messageArrayList = eventArrayList.get(i);
                    doneD = true;
                }
                i++;
            }
            System.out.print("Event(s): ");
            for(int j = 0; j < messageArrayList.size(); j++){
                System.out.println("\n" + (j + 1) + ". " + messageArrayList.get(j).getEventName() + " from: " + 
                        convertTime(messageArrayList.get(j).getStartTime()) + 
                        " to: " + convertTime(messageArrayList.get(j).getEndTime()));
            }

            if(messageArrayList.isEmpty()){
                System.out.println("NO event on this day.");
            }
            //System.out.println();
        }else if(choice.equals("m")){
            GregorianCalendar gregCal = new GregorianCalendar();
            
            printEachMonth(gregCal);
            System.out.println();
            
            int currentMonth = gregCal.get(gregCal.MONTH);
            int currentYear = gregCal.get(gregCal.YEAR);
            int pointer = 0;
            
            System.out.println("Event(s):");
            for(int i = 0; i < eventArrayList.size(); i++){
                GregorianCalendar gregCalTest = eventArrayList.get(i).get(0).getDate();
                if(gregCalTest.get(gregCalTest.YEAR) == currentYear){
                    if(gregCalTest.get(gregCalTest.MONTH) == currentMonth){
                        
                        ArrayList<Event> arrayToPrint = eventArrayList.get(i);
                        System.out.println(printArrayList(arrayToPrint));
                    }
                }
            }
            
            while(!done2){
                System.out.print("[P]revious [N]ext [M]ain menu?: ");
                String choice2 = input.nextLine();
                choice2 = choice2.toLowerCase();
                System.out.println();
        
                if(choice2.equals("p")){
                    int k = 0;
                    while(!done && k < eventArrayList.size()){
                        GregorianCalendar gregCalTest = eventArrayList.get(k).get(0).getDate();
                        if(gregCalTest.get(gregCalTest.MONTH) == currentMonth){
                            pointer = k;
                            done = true;
                        }
                        k++;
                    }
                    
                    int newSize = pointer;
                    pointer -= 1; 
                    if(pointer >= 0 && pointer < newSize){
                        System.out.println("Event(s):");
                        int month = eventArrayList.get(pointer).get(0).getDate().get(eventArrayList.get(pointer).get(0).getDate().MONTH);
                        for(int j = pointer; j >= 0; j--){
                            if(eventArrayList.get(j).get(0).getDate().get(eventArrayList.get(j).get(0).getDate().MONTH) == month){
                                ArrayList<Event> arrayToPrint = eventArrayList.get(j);
                                System.out.println(printArrayList(arrayToPrint));
                            }
                        }
                        currentMonth = eventArrayList.get(pointer).get(0).getDate().get(eventArrayList.get(pointer).get(0).getDate().MONTH);
                    }else{
                        System.out.println("No more previous events\n");
                    }
                    done2 = false;
                }else if(choice2.equals("n")){
                    int k = 0;
                    while(!done && k > eventArrayList.size()){
                        GregorianCalendar gregCalTest = eventArrayList.get(k).get(0).getDate();
                        if(gregCalTest.get(gregCalTest.MONTH) == currentMonth){
                            pointer = k;
                            done = true;
                        }
                        k++;
                    }
                    
                    int newSize = eventArrayList.size();
                    pointer += 1;
                    if(pointer >= 0 && pointer < newSize){
                        System.out.println("Event(s):");
                        int month = eventArrayList.get(pointer).get(0).getDate().get(eventArrayList.get(pointer).get(0).getDate().MONTH);
                        for(int j = pointer; j < eventArrayList.size(); j++){
                            if(eventArrayList.get(j).get(0).getDate().get(eventArrayList.get(j).get(0).getDate().MONTH) == month){
                                ArrayList<Event> arrayToPrint = eventArrayList.get(j);
                                System.out.println(printArrayList(arrayToPrint));
                                //pointer += 1;
                            }
                        }
                        currentMonth = eventArrayList.get(pointer).get(0).getDate().get(eventArrayList.get(pointer).get(0).getDate().MONTH);
                    }else{
                        System.out.println("No more previous events\n");
                    }
                    done2 = false;
                }else if(choice2.endsWith("m")){
                    //We do nothing and just go back to the main menu
                    done2 = true;
                }else{
                    System.out.println("Invalid input");
                    done2 = false;
                }
                System.out.println();
            }         
        }
    }
    /**
     * This method creates an event
     * @param event is the event to be added in Calendar list
     * @return true if done adding, false otherwise
     */
    public boolean create(Event event){
        boolean doneAdd = false;
        GregorianCalendar dates1 = event.getDate();
        //System.out.println("Called ");
        if(eventArrayList.isEmpty()){
            ArrayList eventInnerArr = new ArrayList();
            eventInnerArr.add(event);
            eventArrayList.add(eventInnerArr);
            doneAdd = true;
        }else{
            int j = 0;
            boolean createDone = false;
            boolean done = false;
            
            for(int i = 0; i < eventArrayList.size(); i++){
                GregorianCalendar dates2 = eventArrayList.get(i).get(0).getDate();
                ArrayList<Event> array = eventArrayList.get(i);
                
                if(dates2.compareTo(dates1) == 0){ 
                    while(!createDone && j < array.size()){
                        if(event.getStartTime() < array.get(j).getStartTime()){
                            eventArrayList.get(i).add(j, event);
                            createDone = true;
                            doneAdd = true;
                            done = true;
                        }
                        j++;
                    }
                    if(!createDone){
                        eventArrayList.get(i).add(eventArrayList.get(i).size(), event);
                        createDone = true;
                        doneAdd = true;
                        done = true;
                    }
                }
            }
            int l = 0;
            boolean createDone2 = false;
            if(!createDone){
                for(int k = 0; k < eventArrayList.size(); k++){
                    ArrayList newArrayEvent2 = new ArrayList();
                    while(!createDone2 && l < eventArrayList.size()){
                        if(eventArrayList.get(l).get(0).getDate().compareTo(dates1) > 0){ 
                            newArrayEvent2.add(event);
                            eventArrayList.add(l, newArrayEvent2);
                            doneAdd = true;
                            createDone2 = true;
                        }
                        l++;
                    }
                    if(!createDone2){
                        newArrayEvent2.add(event);
                        eventArrayList.add(newArrayEvent2);
                        doneAdd = true;
                        createDone2 = true;
                    }
                }
            }
        }
        return doneAdd;
    }
    
    /**
     * This method displays the Day view of the requested date including event scheduled
     * @param gGo GregorianCalendar that contains the date user wants to view
     */
    public void go(GregorianCalendar gGo){
        String month = new DateFormatSymbols().getMonths()[gGo.get(gGo.MONTH)];
        System.out.println("\n" + month + " " + gGo.get(gGo.DATE) + ", " + gGo.get(gGo.YEAR));
        
        ArrayList<Event> arrayOfEvent = new ArrayList<>();
        boolean done = false;
        int i = 0;
        
        while(!done && i < eventArrayList.size()){
            GregorianCalendar dates2 = eventArrayList.get(i).get(0).getDate(); 
            if(dates2.compareTo(gGo) == 0){
                arrayOfEvent = eventArrayList.get(i);
                done = true;
            }
            i++;
        }
        
        if(done){
            System.out.println("Event(s):");
            for(int j = 0; j < arrayOfEvent.size(); j++){
                System.out.println(j + 1 + ". " + arrayOfEvent.get(j).getEventName() + " from: " + 
                    convertTime(arrayOfEvent.get(j).getStartTime()) + 
                    " to: " + convertTime(arrayOfEvent.get(j).getEndTime()));
            }
        }else{
            System.out.println("\nNo event on that day.");
        }
    }
    
    /**
     * Method that prints all the events created so user can browse on these scheduled events
     * @return a string representation of all the events scheduled/created
     */
    public String eventList(){
        String message = "";

        if(!eventArrayList.isEmpty()){
            for(int i = 0; i < eventArrayList.size(); i++){
                ArrayList<Event> arrayOfEvent = eventArrayList.get(i);
 
                message += arrayOfEvent.get(0).getDate().get(arrayOfEvent.get(0).getDate().YEAR )+ "\n";
               
                for(int j = 0; j < eventArrayList.get(i).size(); j ++){
                    GregorianCalendar greg = arrayOfEvent.get(j).getDate();
                    
                    String month = new DateFormatSymbols().getMonths()[greg.get(greg.MONTH)];
                    String day = new DateFormatSymbols().getWeekdays()[greg.get(greg.DAY_OF_WEEK)];
                    
                    message += " " + day + " " + month + " " + greg.get(greg.DATE) + " ";
                    message += convertTime(arrayOfEvent.get(j).getStartTime()) + " - " + convertTime(arrayOfEvent.get(j).getEndTime());
                    message += " " + arrayOfEvent.get(j).getEventName() + "\n";
                }
            }
        }else{
            message += "No single event has ever created.";
        }
        return message;
    }

    /**
     *@param type is the user's choice if he/she wants to delete all appointments or
     * delete all events on a specific date given by the user
     * @return a string representation of the date deleted
     */
    public String delete(String type){
        String deletedEvent = "";
        
        if(type.equals("s")){
            System.out.print("Enter date (MM/DD/YYYY): ");
            String date = input.nextLine();
            String [] stringDate = date.split("/");
                
            int year = Integer.valueOf(stringDate[2]);
            int month = Integer.valueOf(stringDate[0]);
            int day = Integer.valueOf(stringDate[1]);
           
            GregorianCalendar gc1Delete = new GregorianCalendar(year, month -1, day);
            String newMonth = new DateFormatSymbols().getMonths()[month -1];

            if(!eventArrayList.isEmpty()){
                for(int i = 0; i < eventArrayList.size(); i++){
                    GregorianCalendar gc2Delete = eventArrayList.get(i).get(0).getDate();
                    if(compare(gc1Delete, gc2Delete) == 0){
                        eventArrayList.remove(i);
                        deletedEvent += newMonth + " " +  day + ", " + year;
                    }
                }
            }   
        }else{
            eventArrayList.removeAll(eventArrayList);
            deletedEvent += "\nAll events scheduled on this calendar are deleted";
        }
        return deletedEvent;
    }
    /**
     * This function prints the calendar just like we see on our phones
     * @param date is a GregorianCalendar object
     */
    public void printMonth(GregorianCalendar date) {
        GregorianCalendar dateSet = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH ), date.get(date.DATE), 0,0,0);
        GregorianCalendar todate = new GregorianCalendar();
        
        int today = todate.get(todate.DATE);
	dateSet.set(dateSet.DATE, 1);
	int firstDayInMonth = dateSet.get(dateSet.DAY_OF_WEEK);
	int daysInMonth = dateSet.getActualMaximum(dateSet.DAY_OF_MONTH);
		
	SimpleDateFormat dateFormat = new SimpleDateFormat("   MMMM yyyy ");
	System.out.println( dateFormat.format(dateSet.getTime()) );
        
        System.out.println("Su Mo Tu We Th Fr Sa");	
	for (int i = 1; i < firstDayInMonth; i++){
            System.out.print("   ");
	}
	for(int i = 1; i <= daysInMonth; i++){
            if(i < 10){
		if((today == i) && (date.compareTo(todate) == 0) ){	
                    System.out.print("[" + i + "]");
                }
		if(((today - 1) == i) && (i == 9) && (date.compareTo(todate) == 0)){	
                    System.out.print( " " + i + "[");	
                }else{	
                    System.out.print(" " + i + " ");	
                }
            }else if(i >= 10){
		if((today - 1) == i && (date.compareTo(todate) == 0) ){	
                    System.out.print( i + "["); 
                }else if( today == i && (date.compareTo(todate) == 0) ){	
                    System.out.print( i + "]"); 
                }else{	
                    System.out.print( i + " ");	
                }
            }if((i - 1 + firstDayInMonth) % 7 == 0){
		System.out.println();
            }
	}
	System.out.println();
    }	
    /**
     * Method that compares two GregorianCalendar objects
     * @param dateOfEvent1 is the first object of GregorianCalendar to be compared with
     * @param dateOfEvent2 is the second object of GregorianCalendar to be compared to
     * @return 0 if equal, non-zero otherwise (1 if object 1 is comes first than object2,
     * -1 the other way around)
     */
    @Override
    public int compare(GregorianCalendar dateOfEvent1, GregorianCalendar dateOfEvent2){
        //GregorianCalendar date2 = dateOfEvent1;
 
	if(dateOfEvent1.get(dateOfEvent1.YEAR) > dateOfEvent2.get(dateOfEvent2.YEAR)){
            return 1; 
        }else if(dateOfEvent1.get(dateOfEvent1.YEAR) < dateOfEvent2.get(dateOfEvent2.YEAR)){
            return -1; 
        }else{
            if(dateOfEvent1.get(dateOfEvent1.MONTH) > dateOfEvent2.get(dateOfEvent2.MONTH) ){
                return 1; 
            }else if(dateOfEvent1.get(dateOfEvent1.MONTH) < dateOfEvent2.get(dateOfEvent2.MONTH)){
                return -1; 
            }else{
		if(dateOfEvent1.get(dateOfEvent1.DATE) > dateOfEvent2.get(dateOfEvent2.DATE) ){
                    return 1; 
                }else if(dateOfEvent1.get(dateOfEvent1.DATE) < dateOfEvent2.get(dateOfEvent2.DATE)){
                    return -1; 
                }else{
                    return (dateOfEvent1.get(dateOfEvent1.DATE) - dateOfEvent2.get(dateOfEvent2.DATE));
                }
            }
	}
    }     
    /**
     * Method that saves all the events that we made into our file "events.txt"
     * @return string representation of all the events we have created
     */
    public String saveAllEvents(){
        String message = "";
        for(int i = 0; i < eventArrayList.size(); i++){
            for(int j = 0; j < eventArrayList.get(i).size(); j++){
                ArrayList<Event> eve = eventArrayList.get(i);
                message += eve.get(j).getEventName() + "," + eve.get(j).getDate().get(eve.get(j).getDate().MONTH) + 
                        "," + eve.get(j).getDate().get(eve.get(j).getDate().DATE) + "," + 
                        eve.get(j).getDate().get(eve.get(j).getDate().YEAR) + "," +
                        eve.get(j).getStartTime() + "," +eve.get(j).getEndTime() + "\n";
            }
        }
        return message;
    }
        /**
     * @param time is the time to be converted similar with how we normally write time
     * @return string representation of the newly converted time
     */
    public String convertTime(int time){
        String message = null;
        int hour = time /100;
        int min = time % 100;
        if(hour > 12){
            hour = (hour % 12);
        }
        if(min == 0){
           message = String.valueOf(hour) + ":00";
        }else{
            message = String.valueOf(hour) + ":" + String.valueOf(min);
        }
        return message;
    }
    /////////////////////////PRIVATE UTILITY METHODS////////////////////////////
    /**
     * Private Utility method that prints the an array<Event>
     * @param array is an object of ArrayList that needs to printed
    */
    private String printArrayList(ArrayList<Event> array){
        String message = "";
        GregorianCalendar gregCal = array.get(0).getDate();
        
        String monthInWords = new DateFormatSymbols().getMonths()[gregCal.get(gregCal.MONTH)];
        //System.out.println("Month: " + gregCal.get(gregCal.MONTH));
        
        int year = array.get(0).getDate().get(array.get(0).getDate().YEAR);
        if(array.size() > 1){
            for(int i = 0; i < array.size(); i++){
                message += array.get(i).getEventName() + " " + monthInWords + " " + 
                     array.get(0).getDate().get(array.get(0).getDate().DATE) + ", " + year + "\n";
            }
        }else if(array.size() == 1){
            for(int i = 0; i < array.size(); i++){
                message += array.get(i).getEventName() + " " + monthInWords + " " + 
                     array.get(0).getDate().get(array.get(0).getDate().DATE) + ", " + year;
            }
        }
   
        return message;
    }
    /**
     * Private utility method that prints the month
     * @param date is an object of GregorianCalendar to be be printed per month
     */    
    private void printEachMonth(GregorianCalendar date) {
        GregorianCalendar dateSet = new GregorianCalendar(date.get(date.YEAR), date.get(date.MONTH), date.get(date.DATE), 0,0,0);
        GregorianCalendar todate = new GregorianCalendar();
        
        int today = todate.get(todate.DATE);
	dateSet.set(dateSet.DATE, 1);
	int firstDayInMonth = dateSet.get(dateSet.DAY_OF_WEEK);
	int daysInMonth = dateSet.getActualMaximum(dateSet.DAY_OF_MONTH);
		
	SimpleDateFormat dateFormat = new SimpleDateFormat("   MMMM yyyy ");
	System.out.println( dateFormat.format(dateSet.getTime()) );
        
        System.out.println("Su Mo Tu We Th Fr Sa");	
	for (int i = 1; i < firstDayInMonth; i++){
            System.out.print("   ");
	}
	for(int i = 1; i <= daysInMonth; i++){
            if(i < 10){
		if((today == i) && (date.compareTo(todate) == 0) ){	
                    System.out.print(" " + i + " ");
                }
		if(((today - 1) == i) && (i == 9) && (date.compareTo(todate) == 0)){	
                    System.out.print( " " + i +  " ");	
                }else{	
                    System.out.print(" " + i + " ");	
                }
            }else if(i >= 10){
		if((today - 1) == i && (date.compareTo(todate) == 0) ){	
                    System.out.print( i + " "); 
                }else if( today == i && (date.compareTo(todate) == 0) ){	
                    System.out.print( i + " "); 
                }else{	
                    System.out.print( i + " ");	
                }
            }if((i - 1 + firstDayInMonth) % 7 == 0){
		System.out.println();
            }
	}
	System.out.println();
    }	 
}
