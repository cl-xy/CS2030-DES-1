package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

/**
 * Creates an event when the customer arrives.
 * The `ArriveEvent` class supports (i) making the event happen 
 * (ii) deciding on the next event for this particular customer
 * (iii) storing the time of occurrence of event.
 */

public class ArriveEvent extends Event {
    private final double eventTime;

    /** Initialises ArriveEvent.
     * @param customer the customer who arrives
     * @param servers the list of servers
     */
    public ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.eventTime = customer.getArrTime();
    }

    /**Getter method.
     * @return Gets the time that event occurs
     */
    @Override
    public double getEventTime() {
        return this.eventTime;
    }

    /** 
     * Checks through the list of servers, return new event.
     * @return 3 possible events - (i) if there is an available server
     *         keep track of the serverID and return a ServeEvent, (ii) if there is 
     *         server with no waiting customer, keep track of serverID and return a WaitEvent,(iii)
     *         if all servers are busy and have waiting customers,
     *         return a LeaveEvent, customer leaves.
     */
    @Override
    public Event execute() {
        int numServers = this.getServers().size(); 
        for (int i = 0; i < numServers; i++) {
            Server current = this.getServers().get(i);
            if (current.getAvailable()) {
                return new ServeEvent(getCust(), getServers(), current.getID());
            }
        }

        for (int i = 0; i < getServers().size(); i++) {
            Server curr = getServers().get(i);
            if (!curr.getHasWaitCust()) {
                return new WaitEvent(getCust(), getServers(), curr.getID());
            }
        }

        return new LeaveEvent(getCust(), getServers());
        
    }

    /**
     * Override toString method.
     * @return prints out the arriveEvent in the correct format
     */
    @Override
    public String toString() {
        String custDetails = String.format("%.3f ", super.getCustArrTime()) + super.getCustId();
        return custDetails + " arrives";
    }
}
