package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

/**
 * Creates an abstract class, acts as a parent class.
 * The `Event` class supports 
 * (i) getting the customer in the event
 * (ii) getting the server list
 * (iii) getting the customer's id
 * (iv) getting the customer's arrival time
 */
public abstract class Event {
    private final Customer customer;
    private final List<Server> servers;

    /**
     * Initialises an event.
     * @param customer the current customer
     * @param servers the list of servers
     */
    public Event(Customer customer, List<Server> servers) {
        this.customer = customer;
        this.servers = new ArrayList<>(servers);
    }
    
    /**
     * Getter method.
     * @return the customer
     */
    public Customer getCust() { 
        return this.customer;
    }

    /**
     * Getter method.
     * @return the list of servers
     */
    public List<Server> getServers() {
        return this.servers;
    }

    /** 
     * Getter method.
     * @return the identifier of customer
     */
    public int getCustId() { 
        return this.customer.getId();
    }

    /**
     * Getter method.
     * @return the time that customer arrives
     */
    public double getCustArrTime() {
        return this.customer.getArrTime();
    }

    /**
     * abstract method to be overriden by sub-classes.
     * @return the next event
     */
    public abstract Event execute();
    
    /**
     * abstract method to be overriden by sub-classes, getter method.
     * @return time of event
     */
    public abstract double getEventTime();
}
