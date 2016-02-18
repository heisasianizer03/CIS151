import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Character;
import java.util.Arrays;
/**
 * Plane Class that has all the plane information (seats information)
 * Two sections: First Class and Economy
 * @author Paul
 */
public class Plane {
    public ArrayList<Seat> firstA;
    public ArrayList<Seat> firstB;
    public ArrayList<Seat> firstC;
    public ArrayList<Seat> firstD;
    
    public ArrayList<Seat> economyA;       //Window Seats I
    public ArrayList<Seat> economyB;       //Center Seats I
    public ArrayList<Seat> economyC;       //Aisle Seats I
    public ArrayList<Seat> economyD;       //Aisle Seats II
    public ArrayList<Seat> economyE;       //Center Seats II
    public ArrayList<Seat> economyF;       //Window Seats II
    
    Scanner input = new Scanner(System.in);
    /**
     * 
     */
    public Plane(){
        firstA = new ArrayList<Seat>();
        firstB = new ArrayList<Seat>();
        firstC = new ArrayList<Seat>();
        firstD = new ArrayList<Seat>();
        
        economyA = new ArrayList<Seat>();
        economyB = new ArrayList<Seat>();
        economyC = new ArrayList<Seat>();
        economyD = new ArrayList<Seat>();
        economyE = new ArrayList<Seat>();
        economyF = new ArrayList<Seat>();
    }
    /***/
    public void addOnePassenger(Passenger pass, String serviceClass, char seatPreference){
        String seatAvail = "";
        

        if(serviceClass.equals("first")){
            seatAvail = generateSeatNumber(serviceClass, seatPreference);
            Seat seat = new Seat(pass, seatPreference, seatAvail);
            
            if(seatPreference == 'w'){
                if(firstA.size() <= firstD.size()){
                    firstA.add(seat);
                }else if(firstA.size() > firstD.size()){
                    firstD.add(seat);
                }else if(isSectionFull(firstA, 2) && isSectionFull(firstD, 2)){          //Both are full
                    System.out.println("FIRST CLASS WINDOW IS FULL");
                }
            }else if(seatPreference == 'a'){
                if(firstB.size() <= firstC.size()){
                    firstB.add(seat);
                }else if(firstB.size() > firstC.size()){
                    firstC.add(seat);
                }else if(isSectionFull(firstB, 2) && isSectionFull(firstC, 2)){          //Both are full
                    System.out.println("FIRST CLASS AISLE IS FULL");
                }
            }
        }   
        if(serviceClass.equals("economy")){
            seatAvail = generateSeatNumber(serviceClass, seatPreference);
            Seat seat = new Seat(pass, seatPreference, seatAvail);
            
            if(seatPreference == 'w'){
                if(economyA.size() <= economyF.size()){
                    economyA.add(seat);
                }else if(economyA.size() > economyF.size()){
                    economyF.add(seat);
                }else if(isSectionFull(economyA, 20) && isSectionFull(economyB, 20)){      //Both are full
                    System.out.println("ECONOMY CLASS WINDOW IS FULL");
                }
            }else if(seatPreference == 'a'){
                if(economyC.size() <= economyD.size()){
                    economyC.add(seat);
                }else if(economyC.size() > economyD.size()){
                    economyD.add(seat);
                }else if(isSectionFull(economyC, 20) && isSectionFull(economyD, 20)){        //Both are full
                    System.out.println("ECONOMY CLASS AISLE IS FULL");
                }
            }else if(seatPreference == 'c'){
                if(economyB.size() <= economyE.size()){
                    economyB.add(seat);
                }else if(economyB.size() > economyE.size()){
                    economyE.add(seat);
                }else if(isSectionFull(economyB, 20) && isSectionFull(economyE, 20)){        //Both are full
                    System.out.println("ECONOMY CLASS AISLE IS FULL");
                }
            }
        }       
    }
    
    /***/
    public void addGroupPassenger(){
        String seatAvail = "";
        char seatPreference = ' ';
        String[] seatArray = {};
        
        System.out.print("\nGroup name: ");
        String groupName = input.nextLine();
        
        System.out.print("Service class: ");
        String serviceClass = input.nextLine();
        serviceClass = serviceClass.toLowerCase();
        
        System.out.print("Name: ");
        String namesOfPassengers = input.nextLine();
        
        String[] names = namesOfPassengers.split(",\\s+");
        String[] namesArray = new String[names.length];
        
        System.out.print(this);
        for(int i = 0; i < names.length; i++){
            namesArray[i] = names[i];
        }
        
        if(serviceClass.equals("first")){                                       //Reserve group in first class
            int currentSizeFirstClass = firstA.size() + firstB.size() + firstC.size() + firstD.size();
            if(8 - currentSizeFirstClass >= namesArray.length){                    //There's enough seats for the entire group
                for(int i = 0; i < namesArray.length; i++){
                    
                    //pass (name, serviceClass, groupName)
                    Passenger pass = new Passenger(namesArray[i], serviceClass, groupName);
                    seatAvail = generateSeatNumber(serviceClass, seatPreference);
                    //seat (pass, seatPreference, seatNumber)
                    Seat seat = new Seat(pass, seatPreference, seatAvail);
                    
                    if(!isSectionFull(firstA, 2)){                                     //means firstA has more seat
                        firstA.add(seat);
                    }else if(isSectionFull(firstA, 2) && !isSectionFull(firstB, 2)){   //firstA is full, and firstB has more seats
                        firstB.add(seat);
                    }else if(isSectionFull(firstB, 2) && !isSectionFull(firstC, 2)){   //firstB is full, and firstC has more seats
                        firstC.add(seat);
                    }else if(isSectionFull(firstC, 2) && !isSectionFull(firstD, 2)){   //all is full except firstD
                        firstD.add(seat);
                    }  
                }
            }else{
                System.out.println("No can do. There's only " + (8 - currentSizeFirstClass) + " seat/s left");                      
            }
        }else if(serviceClass.equals("economy")){                               //Reserve group in economy
            
            //System.out.println("Entering else if economy addGroyp");
            int currentSizeEconClass = economyA.size() + economyB.size() + economyC.size() + economyD.size() +
                    economyE.size() + economyF.size();
            if(120 - currentSizeEconClass >= namesArray.length){
                //System.out.println("Entering else if economy 120 ");
                for(int i = 0; i < namesArray.length; i++){ 
                    //String[]groupSeatsAvail = generateSeatGroup(serviceClass, namesArray.length);
                    seatArray = generateSeatGroup(serviceClass, namesArray.length);        
                }
                
                //System.out.println("SeatArraylength " + seatArray.length);
                //System.out.println("namesArraylength " + namesArray.length);
                for(int i = 0; i < namesArray.length; i++){
                    //pass (name, serviceClass, groupName)
                    Passenger pass = new Passenger(namesArray[i], serviceClass, groupName);
                    System.out.println("SeatArray " + seatArray[i].charAt(2));
                    
                    char newSeatPrefer = seatArray[i].charAt(2);
                    System.out.println("NewSeat: " + newSeatPrefer);
                    
                    //seat (pass, seatPreference, seatNumber)
                    Seat seat = new Seat(pass, ' ', seatArray[i]);
                    
                    if(newSeatPrefer == 'A'){
                        economyA.add(seat);
                    }else if(newSeatPrefer == 'B'){
                        economyB.add(seat);
                    }else if(newSeatPrefer == 'C'){
                        economyC.add(seat);
                    }else if(newSeatPrefer == 'D'){
                        economyD.add(seat);
                    }else if(newSeatPrefer == 'E'){
                        economyE.add(seat);
                    }else if(newSeatPrefer == 'F'){
                        economyF.add(seat);
                    }
                }
            }
        }
        
    }
    
    /**/
    public boolean cancelReservation(String name, String serviceClass, String groupName, char type){
        boolean done = false;
        Passenger pass = new Passenger(name, serviceClass, groupName);
        System.out.println(name + " " + serviceClass + " " +  groupName + " " +  type);
        int totalCancel = 0;
        if(type == 'i'){
            if(serviceClass.equals("first")){
                for(int i = 0; i < firstA.size(); i++){
                    if(firstA.get(i).getPassenger().getName().equals(name)){
                        firstA.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < firstB.size(); i++){
                    if(firstB.get(i).getPassenger().getName().equals(name)){
                        firstB.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < firstC.size(); i++){
                    if(firstC.get(i).getPassenger().getName().equals(name)){
                        firstC.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < firstD.size(); i++){
                    if(firstD.get(i).getPassenger().getName().equals(name)){
                        firstD.remove(i);
                        totalCancel++;
                    }
                }
            }else if(serviceClass.equals("economy")){
            
                for(int i = 0; i < economyA.size(); i++){
                    if(economyA.get(i).getPassenger().getName().equals(name)){
                        economyA.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < economyB.size(); i++){
                    if(economyB.get(i).getPassenger().getName().equals(name)){
                        economyB.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < economyC.size(); i++){
                    if(economyC.get(i).getPassenger().getName().equals(name)){
                        economyC.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < economyD.size(); i++){
                    if(economyD.get(i).getPassenger().getName().equals(name)){
                        economyD.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < economyE.size(); i++){
                    if(economyE.get(i).getPassenger().getName().equals(name)){
                        economyE.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < economyF.size(); i++){
                    if(economyF.get(i).getPassenger().getName().equals(name)){
                        economyF.remove(i);
                        totalCancel++;
                    }
                }
            }
        }else if(type == 'g'){
            if(serviceClass.equals("first")){
                for(int i = 0; i < firstA.size(); i++){
                    if(firstA.get(i).getPassenger().getGroupName().equals(name)){
                        firstA.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < firstB.size(); i++){
                    if(firstB.get(i).getPassenger().getGroupName().equals(name)){
                        firstB.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < firstC.size(); i++){
                    if(firstC.get(i).getPassenger().getGroupName().equals(name)){
                        firstC.remove(i);
                    }
                }
                for(int i = 0; i < firstD.size(); i++){
                    if(firstD.get(i).getPassenger().getGroupName().equals(name)){
                        firstD.remove(i);
                        totalCancel++;
                    }
                }
            }else if(serviceClass.equals("economy")){
            
                for(int i = 0; i < economyA.size(); i++){
                    if(economyA.get(i).getPassenger().getGroupName().equals(name)){
                        economyA.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < economyB.size(); i++){
                    if(economyB.get(i).getPassenger().getGroupName().equals(name)){
                        economyB.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < economyC.size(); i++){
                    if(economyC.get(i).getPassenger().getGroupName().equals(name)){
                        economyC.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < economyD.size(); i++){
                    if(economyD.get(i).getPassenger().getGroupName().equals(name)){
                        economyD.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < economyE.size(); i++){
                    if(economyE.get(i).getPassenger().getGroupName().equals(name)){
                        economyE.remove(i);
                        totalCancel++;
                    }
                }
                for(int i = 0; i < economyF.size(); i++){
                    if(economyF.get(i).getPassenger().getGroupName().equals(name)){
                        economyF.remove(i);
                        totalCancel++;
                    }
                }
                
            }
        }
        if(totalCancel > 0){
            done = true;
        }
        return done;
    }
    /***/
    public void printAvailSeats(){
        String firstArray1 = "";
        String firstArray2 = "";
        String firstArray3 = "";
        String firstArray4 = "";
        
        String econArray1 = "";
        String econArray2 = "";
        String econArray3 = "";
        String econArray4 = "";
        String econArray5 = "";
        String econArray6 = "";
        
        
        System.out.println("\n       AVAILABLE SEATS");
        System.out.println("FIRST Class");
        
        if(firstA.size() <= 2){
            System.out.println("       A-Window");
            for(int i = 0; i < 2 - firstA.size(); i++){
                firstArray1 += Integer.toString(firstA.size() + 10 + i) + "A ";
            }
            System.out.println(firstArray1);
        }
        if(firstB.size() <= 2){
            System.out.println("       B-Aisle");
            for(int i = 0; i < 2 - firstB.size(); i++){
                firstArray2 += Integer.toString(firstB.size() + 10 + i) + "B ";
            }
            System.out.println(firstArray2);
        }
        if(firstC.size() <= 2){
            System.out.println("       C-Aisle");
            for(int i = 0; i < 2 - firstC.size(); i++){
                firstArray3 += Integer.toString(firstC.size() + 10 + i) + "C ";
            }
            System.out.println(firstArray3);
        }
        if(firstD.size() <= 2){
            System.out.println("       D-Window");
            for(int i = 0; i < 2 - firstD.size(); i++){
                firstArray4 += Integer.toString(firstD.size() + 10 + i)+ "D ";
            }
            System.out.println(firstArray4);
        }
        System.out.println("ECONOMY CLASS");
        if(economyA.size() <= 20){
            System.out.println("       A-Window");
            for(int i = 0; i < 20 - economyA.size(); i++){
                econArray1 += Integer.toString(economyA.size() + 10 + i) + "A ";
            }
            System.out.println(econArray1);
        }
        if(economyB.size() <= 20){
            System.out.println("       B-Center");
            for(int i = 0; i < 20 - economyB.size(); i++){
                econArray2 += Integer.toString(economyB.size() + 10 + i) + "B ";
            }
            System.out.println(econArray2);
        }
       
        if(economyC.size() <= 20){
            System.out.println("       C-Aisle");
            for(int i = 0; i < 20 - economyC.size(); i++){
                econArray3 += Integer.toString(economyC.size() + 10 + i) + "C ";
            }
            System.out.println(econArray3);
        }
        if(economyD.size() <= 20){
            System.out.println("       D-Aisle");
            for(int i = 0; i < 20 - economyD.size(); i++){
                econArray4 += Integer.toString(economyD.size() + 10 + i) + "D ";
            }
            System.out.println(econArray4);
        }
        if(economyE.size() <= 20){
            System.out.println("       E-Center");
            for(int i = 0; i < 20 - economyE.size(); i++){
                econArray5 += Integer.toString(economyE.size() + 10 + i) + "E ";
            }
            System.out.println(econArray5);
        }
        if(economyF.size() <= 20){
            System.out.println("       F-Window");
            for(int i = 0; i < 20 - economyF.size(); i++){
                econArray6 += Integer.toString(economyF.size() + 10 + i) + "F ";
            }
            System.out.println(econArray6);
        }
        
        System.out.println();
    }
    
    public void printPlaneManifest(){
        String firstArray1 = "";
        String firstArray2 = "";
        String firstArray3 = "";
        String firstArray4 = "";
        
        String econArray1 = "";
        String econArray2 = "";
        String econArray3 = "";
        String econArray4 = "";
        String econArray5 = "";
        String econArray6 = "";
        
        System.out.println("\n         FLIGHT MANIFEST");
        System.out.println("FIRST CLASS");
        
        if(!firstA.isEmpty()){
            System.out.println("       A-Window");
            for(int i = 0; i < firstA.size(); i++){
                firstArray1 += firstA.get(i).seatToString() + " ";
            }
            System.out.println(firstArray1);
        }else{
            System.out.println("A - Window is empty");
        }
        if(!firstB.isEmpty()){
            System.out.println("       B-Aisle");
            for(int i = 0; i < firstB.size(); i++){
                firstArray2 += firstB.get(i).seatToString() + " ";
            }
            System.out.println(firstArray2);
        }else{
            System.out.println("B - Aisle is empty");
        }
        if(!firstC.isEmpty()){
            System.out.println("       C-Aisle");
            for(int i = 0; i < firstC.size(); i++){
                firstArray3 += firstC.get(i).seatToString() + " ";
            }
            System.out.println(firstArray3);
        }else{
            System.out.println("C - Aisle is empty");
        }
        if(!firstD.isEmpty()){
            System.out.println("       D-Window");
            for(int i = 0; i < firstD.size(); i++){
                firstArray4 += firstD.get(i).seatToString() + " ";
            }
            System.out.println(firstArray4);
        }else{
            System.out.println("D - Window is empty");
        }
        
        System.out.println("\nECONOMY");
        if(!economyA.isEmpty()){
            System.out.println("       A-Window");
            for(int i = 0; i < economyA.size(); i++){
                econArray1 += economyA.get(i).seatToString() + " ";
            }
            System.out.println(econArray1);
        }else{
            System.out.println("A - Window is empty");
        }
        if(!economyB.isEmpty()){
            System.out.println("       B-Center");
            for(int i = 0; i < economyB.size(); i++){
                econArray2 += economyB.get(i).seatToString() + " ";
            }
            System.out.println(econArray2);
        }else{
            System.out.println("B - Center is empty");
        }
        if(!economyC.isEmpty()){
            System.out.println("       C-Aisle");
            for(int i = 0; i < economyC.size(); i++){
                econArray3 += economyC.get(i).seatToString() + " ";
            }
            System.out.println(econArray3);
        }else{
            System.out.println("C - Aisle is empty");
        }
        if(!economyD.isEmpty()){
            System.out.println("       D-Aisle");
            for(int i = 0; i < economyD.size(); i++){
                econArray4 += economyD.get(i).seatToString() + " ";
            }
            System.out.println(econArray4);
        }else{
            System.out.println("D - Aisle is empty");
        }
        if(!economyE.isEmpty()){
            System.out.println("       E-Center");
            for(int i = 0; i < economyE.size(); i++){
                econArray5 += economyE.get(i).seatToString() + " ";
            }
            System.out.println(econArray5);
        }else{
            System.out.println("E - Center is empty");
        }
        if(!economyF.isEmpty()){
            System.out.println("       F-Window");
            for(int i = 0; i < economyF.size(); i++){
                econArray6 += economyF.get(i).seatToString() + " ";
            }
            System.out.println(econArray6);
        }else{
            System.out.println("F - Window is empty");
        }
        
        System.out.println();
    }
    /***/
    private String generateSeatNumber(String serviceClass, char seatPreference){
        String seatNum = "";

        if(serviceClass.equals("first")){
            if(seatPreference == 'w'){
                if(!isSectionFull(firstA, 2)){
                    seatNum = Integer.toString(firstA.size() + 1) + "A";
                }else if(firstD.size() < 2){
                    seatNum = Integer.toString(firstD.size() + 1) + "D";
                }
            }else if(seatPreference == 'a'){
                if(firstB.size() < 2){ 
                    seatNum = Integer.toString(firstB.size() + 1) + "B";
                }else if(firstC.size() < 2){
                    seatNum = Integer.toString(firstC.size() + 1) + "C";
                }
            }else if(seatPreference == ' '){                                    //generate seatNumber for group only
                if(!isSectionFull(firstA, 2)){                                  //means firstA has more seat
                    seatNum = Integer.toString(firstA.size() + 1) + "A";
                    System.out.println(seatNum);
                }else if(isSectionFull(firstA, 2) && !isSectionFull(firstB, 2)){   //firstA is full, and firstB has more seats
                    seatNum = Integer.toString(firstB.size() + 1) + "B";
                    System.out.println(seatNum);
                }else if(isSectionFull(firstB, 2) && !isSectionFull(firstC, 2)){   //firstB is full, and firstC has more seats
                    seatNum = Integer.toString(firstC.size() + 1) + "C";
                    System.out.println(seatNum);
                }else if(isSectionFull(firstC, 2) && !isSectionFull(firstD, 2)){   //all is full except firstD
                    seatNum = Integer.toString(firstD.size() + 1) + "D";
                    System.out.println(seatNum);
                }
            }
        }else if(serviceClass.equals("economy")){
            if(seatPreference == 'w'){                                      //Window seats
                if(economyA.size() < 3){
                    seatNum = Integer.toString(economyA.size() + 10) + "A";
                }else if(economyF.size() < 3){
                    seatNum = Integer.toString(economyF.size() + 10) + "F";
                }
            }else if(seatPreference == 'a'){                                //Aisle seats
                if(economyC.size() < 3){ 
                    seatNum = Integer.toString(economyC.size() + 10) + "C";
                }else if(economyD.size() < 3){
                    seatNum = Integer.toString(economyD.size() + 10) + "D";
                }
            }else if(seatPreference == 'c'){                                //Center seats
                if(economyB.size() < 3){ 
                    seatNum = Integer.toString(economyB.size() + 10) + "B";
                }else if(economyE.size() < 3){
                    seatNum = Integer.toString(economyE.size() + 10) + "E";
                }
            }else if(seatPreference == ' '){                                //generate seatNumber for group only 
            }                
        }
     return seatNum;
    }
    /***/
    private String[] generateSeatGroup(String serviceClass, int passNumber){
        String[] seatArray = new String[passNumber];;
        String seatNum = "";
        String[] letterArr = {"A", "B", "C", "D", "E", "F"};
        String closeArr = "";
        
        int a = economyA.size();
        int b = economyB.size();
        int c = economyC.size();
        int d = economyD.size();
        int e = economyE.size();
        int f = economyF.size();
        
        int smallest = a;
        
        if(smallest > b){
            smallest = b;
        }else if(smallest > c){
            smallest = c;
        }else if(smallest > d){
            smallest = d;
        }else if(smallest > e){
            smallest = e;
        }else if(smallest > f){
            smallest = f;
        }
       
        if(a == smallest && a == b){
            if(a == c){
                if(a == d){
                    if(a == e){
                        if(a == f){
                            closeArr = "ABCDEF";
                        }else{
                            closeArr = "ABCDE";
                        }
                    }else{
                        closeArr = "ABCD";
                    }
                }else{
                    closeArr = "ABC";
                }
            }else{
                closeArr = "AB";
            }
        }else if(b == smallest && b == c){
            if(b == d){
                if(b == e){
                    if(b == f){
                        closeArr = "BCDEF";
                    }else{
                        closeArr = "BCDE";
                    }
                }else{
                    closeArr = "BCD";
                }
            }else{
                closeArr = "BC";
            }
        }else if(c == smallest && c == d){
            if(c == e){
                if(c == f){
                    closeArr = "CDEF";
                }else{
                    closeArr = "CDE";
                }
            }else{
                closeArr = "CD";
            }  
        }else if(d == smallest && d == e){
            if(d == f){
                closeArr = "DEF";
            }else{
                closeArr = "CD";
            }
        }else if(e == smallest && e == f){
            closeArr = "EF";
        }else if(f == smallest){
            
        }
        
        return seatArray;
    }
    /***/
    public void toStringArray(ArrayList<Seat> seat){
        for(Seat s: seat){
            System.out.print(s.seatToString());
        }
    }
    
    /***/
    private boolean isSectionFull(ArrayList<Seat> seat, int capacity){
        if(seat.size() >= capacity){
            return true;
        }else{
            return false;
        }
    }
    /***/
    private String findSeats(){
        String seatsAvail = "";
        
        return seatsAvail;
    }
}
