/**
 * Seat class that represents a passenger's seat on the plane
 * @author Paul
 */
public class Seat {
    private Passenger passenger;
    private char seatPreference;
    private String seatNumber;
    
    /**
     * Constructor that creates Seat class (includes passenger's name, serviceClass and groupName (optional),
     * @param passenger object that has (name, service class, and group name)
     * @param seatPreference of a passenger (w- window, c- center, a- aisle)
     * @param seatNumber of a passenger provided by the program
     */
    public Seat(Passenger passenger, char seatPreference, String seatNumber){
        this.passenger = passenger;
        this.seatPreference = seatPreference;
        this.seatNumber = seatNumber;
    }
    /**
     * Accessor method that returns passenger object
     * @return passenger object (has name, service class, and group name)
     */
    public Passenger getPassenger(){
        return passenger;
    }
    
    /**
     * Accessor method that returns seat preference of a passenger
     * @return seat preference of a passenger
     */
    public char getSeatPreference(){
        return seatPreference;
    }
    
    /**
     * Accessor method that returns seat number of a passenger
     * @return seat number (example: 10F, 15A)
     */
    public String getSeatNumber(){
        return seatNumber;
    }
    
    /** String representation of Seat class
     *  @return seat information (passenger name, service class, group name, seat preference, )
     */
    public String seatToString(){
        String seatString = passenger.passengerToString() + " " + 
            " " + getSeatNumber() + "\n";
        return seatString;
    }
    /**
     * Mutator method that set the seat preference of a passenger
     * @param preferedSeat of a passenger seat (w - window, a - aisle, c - center)
     */
    public void setSeatPreference(char preferedSeat){
        seatPreference = preferedSeat;
    }
    
}
