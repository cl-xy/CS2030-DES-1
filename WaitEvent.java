package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

/**
 * Creates a event when the customer gets waited.
 * The `WaitEvent` class should support
 * (i) getting the time of event
 * (ii) getting the id of the selected server
 * (iii) making the event happen
 * (iv) updating the server list.
 */
public class WaitEvent extends Event {
    private final int selectedID;
    private final double eventTime;

    /**
     * Initialises the WaitEvent. 
     * @param customer the current customer
     * @param servers the list of servers
     * @param selectedID the id of selected server
     */
    public WaitEvent(Customer customer, List<Server> servers, int selectedID) {
        super(customer, servers);
        this.selectedID = selectedID;
        this.eventTime = customer.getArrTime();
    }

    /**
     * Getter method.
     * @return the time that event happens
     */
    @Override
    public double getEventTime() {
        return this.eventTime;
    }

    /**
     * Getter method.
     * @return the id of the selected server
     */
    public int getSelectedId() {
        return this.selectedID;
    }

    /**
     * The customer is waiting, update the state of server selected
     * update the server list with new server.
     * @return a ServeEvent that should happen next.
     */
    @Override
    public Event execute() {
        int index = this.selectedID - 1;
        List<Server> serverList = this.getServers();
        Server curr = this.getServers().get(index);
        serverList.set(index, new Server(this.selectedID, false, true, curr.getNextAvailTime()));

        return new ServeEvent(getCust(), serverList, this.selectedID, curr.getNextAvailTime());
    }

    /**
     * Overriden toString method.
     * @return prints out the event in the correct format
     */
    @Override
    public String toString() {
        return String.format("%.3f %s waits to be served by %s", 
                getCustArrTime(), getCustId(), this.selectedID);
    }
}
