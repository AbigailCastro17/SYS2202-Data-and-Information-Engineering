//

public class Taxi {
		
    /*
     * Put global fields here.
     */
	public double rate;
	public int capacity;
	public int passengers;
	

	public Taxi() { // NO-ARGUMENT CONSTRUCTOR
        /*
         * Initialize global fields.
         * These values will never be changed because the constructor has no parameters.
         */
		this.rate = 0.50;
		this.capacity = 4;
		this.passengers = 1;
	}
	
	public Taxi(double rate, int capacity) {
        /*
         * Initialize global fields.
         * These values can be changed via constructor parameters.
         */
		this.rate = rate;
		this.capacity = capacity;
		this.passengers = 1;
	}
	
	public double calculateFare(int passengersLeaving, int durationOfRide) {
        /*
         * Your method implementation goes here.
         */
		if (passengersLeaving<=0 || durationOfRide<=0) {
			return 0.0;
		}
		else
			return passengersLeaving*durationOfRide*rate;
	}
	
	public boolean pickUp(int passengersLoading) {
        /*
         * Your method implementation goes here.
         */
		if (passengersLoading>0 && passengersLoading<=capacity)
			return true;
		else
			return false;
	}

}
