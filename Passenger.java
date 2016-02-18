/**
 * Passenger class that represents a passenger
 * A passenger has name, serviceClass and groupName (optional)
 * @author Paul Diaz
 */
public class Passenger {
    private String name = "";
    private String serviceClass = "";
    private String groupName = "";
    
    /**
    * This constructs a Passenger with name, classType(First Class or Economy),
    * @param name of a passenger
    * @param serviceClass preferred of a passenger
    * @param groupName of a passenger if he/she belongs to one
    */
    public Passenger(String name, String serviceClass, String groupName){
        if(groupName.equals("")){
            groupName = "No Group";
        }
        this.name = name;
        this.serviceClass = serviceClass;
        this.groupName = groupName;
    }
    /**
     * Accessor method that returns the name of a passenger
     * @return name of a passenger
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Accessor method that returns the preferred classType of a passenger 
     * @return classType preferred by a passenger
     */
    public String getServiceClass(){
        return this.serviceClass;
    }
    
    /**
     * Accessor method that returns group name of passenger belongs to
     * @return group name of a passenger
     */
    public String getGroupName(){
        return this.groupName;
    }
    /**
     * String representation of a passenger
     * @return passenger information 
     */
    public String passengerToString(){
        String passString = getName() + " " + getServiceClass() + " " + getGroupName() + " ";
        return passString;
    }
}
