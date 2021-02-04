package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

/**
 * Creates an event for customers who are done.
 * The `DoneEvent` supports (i) changing the state of selected server to available
 * (ii) getting the identifier of selected server
 */
public class DoneEvent extends Event {
    private final int selectedID;
    private final double eventTime;

    /**
     * Initialises the DoneEvent.
     * @param customer the current customer
     * @param servers the list of servers
     * @param selectedID the identifier of the server selected to serve the customer
     * @param eventTime the time that DoneEvent happens
     */
    public DoneEvent(Customer customer, List<Server> servers, int selectedID, double eventTime) {
        super(customer, servers);
        this.selectedID = selectedID;
        this.eventTime = eventTime;
    }
    
    /**
     * Changes the state of selected server to available
     * Update the server in the server list.
     * @return DoneEvent to terminate
     */
    @Override
    public Event execute() {
        List<Server> serverList = this.getServers();
        Server curr = serverList.get(this.selectedID - 1);
        serverList.set(this.selectedID - 1, 
                new Server(this.selectedID, true, false, curr.getNextAvailTime()));
        return new DoneEvent(getCust(), serverList, this.selectedID, this.eventTime);
    }

    /**
     * Getter method.
     * @return the time that event occurs
     */
    @Override
    public double getEventTime() {
        return this.eventTime;
    }

    /**
     * Getter method.
     * @return the identifier of the server used to serve the customer
     */
    public int getSelectedId() {
        return this.selectedID;
    }

    /**
     * Overriden toString method.
     * @return prints out the DoneEvent in the correct format
     */
    @Override
    public String toString() {
        return String.format("%.3f %s done serving by %s", this.eventTime, 
                getCustId(), this.selectedID);
    }
}
