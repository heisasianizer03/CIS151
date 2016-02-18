
public class Test {
    public static void main(String[] args){
        /*Passenger pass1 = new Passenger("Paul","First Class","SJSU");
        Passenger pass2 = new Passenger("India","First Class","SJSU");
        Passenger pass3 = new Passenger("Josh","First Class","SJSU");
        Passenger pass4 = new Passenger("Mats","First Class","SJSU");
        Passenger pass5 = new Passenger("Eve","First Class","SJSU");
        Passenger pass6 = new Passenger("Chamie","First Class","SJSU");
        Passenger pass7 = new Passenger("Rick","First Class","SJSU");
        Passenger pass8 = new Passenger("Tim","First Class","SJSU");
        //Passenger pass9 = new Passenger("Joe","First Class","SJSU");
        //pass.passengerToString();
        
        Seat seat1 = new Seat(pass1, 'W', "1A");
        Seat seat2 = new Seat(pass2, 'W', "2A");
        Seat seat3 = new Seat(pass3, 'A', "1B");
        Seat seat4 = new Seat(pass4, 'A', "2B");
        Seat seat5 = new Seat(pass5, 'A', "1C");
        Seat seat6 = new Seat(pass6, 'A', "2C");
        Seat seat7 = new Seat(pass7, 'W', "1D");
        Seat seat8 = new Seat(pass8, 'W', "2D");/*
        
        //System.out.println(seat.seatToString());
        
        Plane plane = new Plane();
        ReservationSystem reserve = new ReservationSystem();
        reserve.addPassenger(plane);
        
        /*plane.addGroupPassenger();
        plane.addGroupPassenger();
        plane.addGroupPassenger();*/
        /*plane.addOnePassenger();
        plane.addOnePassenger();
        plane.addOnePassenger();
        plane.addOnePassenger();
        plane.addOnePassenger();
        plane.addOnePassenger();
        plane.addOnePassenger();*/
        
        Plane plane = new Plane();
        ReservationSystem reserve = new ReservationSystem();
        //reserve.addPassenger(plane);
        
        System.out.println();
        plane.toStringArray(plane.firstA);
        plane.toStringArray(plane.firstB);
        plane.toStringArray(plane.firstC);
        plane.toStringArray(plane.firstD);
        System.out.println();
    }
}
