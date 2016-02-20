/**
 * Airline Reservation System
 * @author Paul Diaz
 */
import java.util.*;
import java.io.*;

public class ReservationSystem {
    public static void main(String[] args){
        Plane plane = new Plane();
        Scanner input = new Scanner(System.in);
        
        if (args.length == 1){
            readinfile(plane, args[0]);
        }
        boolean done = true;
        while(done){
            System.out.print("Enter P to reserve a seat for a passenger\n");    //1
            System.out.print("Enter G to reserve seats for a group\n");         //2
            System.out.print("Enter C to cancel seat(s) reservation\n");        //3
            System.out.print("Enter A to view seats availability\n");           //4
            System.out.print("Enter M to view airplane flight manifest\n");     //5
            System.out.print("Enter Q to quit the program: ");                  //6
            char task = input.next().charAt(0);
            task = Character.toUpperCase(task);
            
            if(task == 'P'){                            //To reserve a seat passenger
                addPassenger(plane);
            }else if(task == 'G'){                      //To reserve group passengers
                addGroup(plane);
            }else if(task == 'C'){                      //To cancel a reservation
                boolean cancel = cancelReservation(plane);
            }else if(task == 'A'){                      //To view seats availability
                plane.printAvailSeats();
            }else if(task == 'M'){                      //To view airplane flight manifest
                plane.printPlaneManifest();
            }else if(task == 'Q'){                      //To quit the program
                done = false;
                //We quit and save all information
            }else{
                System.out.println("Invalid ");
            }
        }
      
    }
    public static void addPassenger(Plane plane){
        Scanner input = new Scanner(System.in);
        String groupName = "";
        
        System.out.println();
        System.out.print("Name: ");
        String name = input.nextLine();
        
        System.out.print("Service Class: ");
        String serviceClass = input.nextLine();
        serviceClass = serviceClass.toLowerCase();
        
        System.out.print("Seat Preference: ");
        char seatPreference = input.next().charAt(0);
        seatPreference = Character.toLowerCase(seatPreference);
        
        Passenger pass = new Passenger(name, serviceClass,groupName);
        plane.addOnePassenger(pass, serviceClass, seatPreference);
        System.out.println();
    }
    
    public static boolean cancelReservation(Plane plane){
        Scanner input = new Scanner(System.in);
        boolean doneCancel = false;
        
        System.out.println();
        System.out.print("Cancel [I]ndividual or [G]roup: ");
        char type = input.next().charAt(0);
        input.nextLine();
        type = Character.toLowerCase(type);
        
        if(type == 'i'){
            
            System.out.print("Name: ");
            String name = input.nextLine();
            
            String groupName = "";
                    
            System.out.print("Service Class: ");
            String serviceClass = input.nextLine();
            
            doneCancel = plane.cancelReservation(name, serviceClass, groupName, type);
            if(doneCancel){
                System.out.println(name + "'s reservation has been cancelled");
            }else{
                System.out.println("Cancelation UNSUCCESSFULL");
            }
            System.out.println();
            
        }else if(type == 'g'){
            System.out.print("Group Name: ");
            String groupName = input.nextLine();
                        
            String name = "";
            
            System.out.print("Service Class: ");
            String serviceClass = input.next();
            
            doneCancel = plane.cancelReservation(name, serviceClass, groupName, type); 
            if(doneCancel){
                System.out.println(groupName + "'s reservation has been cancelled");
            }else{
                System.out.println("Cancelation UNSUCCESSFULL");
            }
            System.out.println();
        }
        return doneCancel;
    }
    public static void addGroup(Plane plane){
        Scanner input = new Scanner(System.in);
        boolean groupAdded = false;
        
        System.out.print("\nGroup name: ");
        String groupName = input.nextLine();
        
        System.out.print("Service class: ");
        String serviceClass = input.nextLine();
        serviceClass = serviceClass.toLowerCase();
        
        System.out.print("Name: ");
        String namesOfPassengers = input.nextLine();
        
        String[] names = namesOfPassengers.split(",\\s+");
        String[] namesArray = new String[names.length];
        
        for(int i = 0; i < names.length; i++){
            namesArray[i] = names[i];
        }
        
        if(namesArray.length >= 2){
            groupAdded = plane.addGroupPassenger(groupName, serviceClass, namesOfPassengers);
            if(groupAdded == true){
                System.out.println("Success");
            }else{
                System.out.println("Failed");
            }
        }else{
            System.out.print("Group must be two or more members");
            System.out.println();
        }
        
    }
    public static void cancelIndividual(Plane plane){
    }
    
    public static void readinfile(Plane plane, String temp){
    }
}
