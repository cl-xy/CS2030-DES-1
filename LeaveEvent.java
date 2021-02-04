package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

/**
 * Creates a leave event when customer arrives, does not get served and leaves.
 * The `LeaveEvent` should support
 * (i) getting the time that customer leaves
 */
public class LeaveEvent extends Event {

    /**
     * Initialises the LeaveEvent.
     * @param customer the current customer
     * @param servers the list of server
     */
    public LeaveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
    }

    /**
     * Override execute() in Event class.
     * @return a new LeaveEvent to terminate
     */
    @Override
    public Event execute() {
        return new LeaveEvent(getCust(), getServers());
    }

    /**
     * Getter method.
     * @return the time that customer leaves
     */
    @Override
    public double getEventTime() {
        return getCustArrTime();
    }

    /**
     * Overriden toString method.
     * @return prints out the event in correct format
     */
    @Override
    public String toString() {
        return String.format("%.3f %s leaves", getCustArrTime(), getCustId());
    }
}
