package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

/** 
 * Creates an event when customer is being served.
 * The `ServeEvent` should support 
 * (i) deciding the next event.
 */
public class ServeEvent extends Event {
    private final int selectedID;    
    private final boolean hasWaited; 
    private final double eventTime;

    /**
     * Initialises the ServeEvent for customers who arrive and get served immediately.
     * @param customer the current customer
     * @param servers the list of servers
     * @param selectedID the identifier of selected server
     */
    public ServeEvent(Customer customer, List<Server> servers, int selectedID) {
        super(customer, servers);
        this.selectedID = selectedID;
        this.hasWaited = false;
        this.eventTime = customer.getArrTime();
    }
    
    /**
     * Overloaded constructor
     * Initialises the ServeEvent for customers who gets waited before getting served.
     * @param customer the current customer
     * @param servers the list of servers
     * @param selectedID the identifier of selected server
     * @param eventTime the time that customer gets served
     */
    public ServeEvent(Customer customer, List<Server> servers, int selectedID, double eventTime) {
        super(customer, servers);
        this.selectedID = selectedID;
        this.hasWaited = true;
        this.eventTime = eventTime;
    }

    /**
     * Getter method.
     * @return the time that customer gets served
     */
    @Override
    public double getEventTime() {
        return this.eventTime;
    }

    /**
     * Getter method.
     * @return true if customer has waited, false if customer did not wait
     */
    public boolean getHasWaited() {
        return this.hasWaited;
    }

    /**
     * Getter method.
     * @return the id of selected server
     */
    public int getSelectedId() {
        return this.selectedID;
    }

    /**
     * update the state of the server to "busy"
     * update the server list.
     * @return a new doneEvent
     */
    @Override
    public Event execute() {
        List<Server> serverList = this.getServers();
        Server curr = serverList.get(this.selectedID - 1);
        serverList.set(this.selectedID - 1, 
                new Server(this.selectedID, false, false, this.eventTime + 1.0));
        
        return new DoneEvent(getCust(), serverList, this.selectedID, this.eventTime + 1.0);
    }

    /**
     * Overriden toString method.
     * @return prints out the event in the correct format
     */
    @Override
    public String toString() {
        return String.format("%.3f %s served by %s", this.eventTime, 
                super.getCustId(), this.selectedID);        
    }
}
