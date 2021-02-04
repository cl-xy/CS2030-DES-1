package cs2030.simulator;

/**
 * Creates a customer. 
 * The `Customer` class supports (i) getting identifier of customer 
 * (ii) getting arrival time of customer
 */
public class Customer {
    private final int id;
    private final double arrivalTime;

    /** Initialises the customer.
     * @param id the customer's ID
     * @param arrivalTime the time that customer arrives
     */
    public Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    /**Getter method.
     * @return Gets the customer's ID
     */
    public int getId() {
        return this.id;
    }

    /**Getter method.
     * @return Gets the customer's arrival time
     */
    public double getArrTime() {
        return this.arrivalTime;
    }

    /**Overriden toString method.
     * @return Prints the customer in the correct format
     */
    @Override
    public String toString() {
        return String.format("%s arrives at %s", this.id, this.arrivalTime);
        //return this.id + " arrives at " + String.format("%.3f", this.arrivalTime);
    }
}
