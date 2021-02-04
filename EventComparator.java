package cs2030.simulator;

import java.util.Comparator;

/** 
 * Creates my own comparator to compare the order of the events.
 * The `EventComparator` class should support
 * (i) the overriden compare metho
 */
public class EventComparator implements Comparator<Event> {
    
    /**
     * Compare between 2 events.
     * @param e1 the first event
     * @param e2 the second event
     * @return -1 if e1 occurs before e2, 1 if e1 occurs after e2
     */
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getEventTime() < e2.getEventTime()) {
            return -1;
        } else if (e1.getEventTime() > e2.getEventTime()) {
            return 1;
        } else {
            if (e1.getCustId() < e2.getCustId()) {
                return -1;
            } else if (e1.getCustId() == e2.getCustId()) {
                if (e1 instanceof ArriveEvent) {
                    return -1;
                } else if ((e1 instanceof WaitEvent) && (e2 instanceof ServeEvent)) {
                    return -1;
                } else if ((e1 instanceof ServeEvent) && (e2 instanceof DoneEvent)) {
                    return -1;
                }
            }
            return 1;
        }
    }
}
