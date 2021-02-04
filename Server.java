package cs2030.simulator;

/**
 * Creates a server.
 * The `Server` class should support
 * (i) getting the identifier
 * (ii) getting the availability
 * (iii) getting whether the server has a waiting customer
 * (iv) getting the server's next available time
 */
public class Server {
    private final int id;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;

    /** 
     * Initialises the server.
     * @param identifier a unique id for server
     * @param isAvailable whether the server is available
     * @param hasWaitingCustomer whether the server has a customer to be served
     * @param nextAvailableTime the time when the server is available again
     */
    public Server(int identifier, boolean isAvailable, 
            boolean hasWaitingCustomer, double nextAvailableTime) {
        this.id = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
    }

    /**
     * Getter method.
     * @return true if the server is available (ie not serving any customer)
     *         false if the server is busy
     */
    public boolean getAvailable() {
        return this.isAvailable;
    }

    /**
     * Getter method.
     * @return true if there is another customer waiting to be served, false otherwise
     */
    public boolean getHasWaitCust() {
        return this.hasWaitingCustomer;
    }

    /**
     * Getter method.
     * @return the time that server is available again
     */
    public double getNextAvailTime() {
        return this.nextAvailableTime;
    }

    /** 
     * Getter method.
     * @return the id of the server
     */
    public int getID() {
        return this.id;
    }

    /**
     * Overriden toString method.
     * @return prints out the server in the correct format
     */
    @Override
    public String toString() {
        if (this.isAvailable) {
            return this.id + " is available";
        } else if (!this.isAvailable && !hasWaitingCustomer) {
            return this.id + " is busy; available at " + 
                String.format("%.3f", this.nextAvailableTime);
        } else {
            return this.id + " is busy; waiting customer to be served at " + 
                String.format("%.3f", this.nextAvailableTime);
        }
    }
}
