import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

import cs2030.simulator.Customer;
import cs2030.simulator.Server;
import cs2030.simulator.Event;
import cs2030.simulator.ArriveEvent;
import cs2030.simulator.ServeEvent;
import cs2030.simulator.EventComparator;
import cs2030.simulator.WaitEvent;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.LeaveEvent;

class Main {
    private String calcStats(PriorityQueue<Event> priorityQueue, int numCust) {
        List<Event> finalEvents = new ArrayList<Event>();

        double totalWaitingTime = 0;
        int numServed = 0;
        int numLeft = 0;

        while (!priorityQueue.isEmpty()) {
            finalEvents.add(priorityQueue.remove());
        }

        int currCustId = 1;
        double custArrTime = 0;
        double custServeTime = 0;
        for (int i = 0; i < finalEvents.size(); i++) {
            Event curr = finalEvents.get(i);
            if ((curr instanceof ArriveEvent) && (curr.getCustId() == currCustId)) {
                custArrTime = finalEvents.get(i).getEventTime();

                for (int j = i + 1; j < finalEvents.size(); j++) {
                    Event next = finalEvents.get(j);

                    if ((next instanceof ServeEvent) && (next.getCustId() == currCustId)) {
                        custServeTime = next.getEventTime();
                        break;
                    }

                    if ((next instanceof LeaveEvent) && (next.getCustId() == currCustId)) {
                        custServeTime = custArrTime;
                        numLeft++;
                        break;
                    }
                }
                totalWaitingTime += custServeTime - custArrTime;
                currCustId++;
            }

        }

        numServed = numCust - numLeft;
        double avgWT = totalWaitingTime / (double) numServed;
        return String.format("[%.3f %s %s]", avgWT, numServed, numLeft);
    }

    private PriorityQueue<Event> run(List<Server> serverList, List<Customer> custList) {
        List<Server> servers = serverList;
        PriorityQueue<Event> finalQueue = new PriorityQueue<>(new EventComparator());
        PriorityQueue<Event> tempQueue = new PriorityQueue<>(new EventComparator());

        for (Customer c : custList) {
            tempQueue.add(new ArriveEvent(c, serverList));
        }

        while (!tempQueue.isEmpty()) {
            Event curr = tempQueue.poll();
            finalQueue.add(curr);
            
            if (curr instanceof ArriveEvent) {
                curr = new ArriveEvent(curr.getCust(), servers);
            } else if (curr instanceof ServeEvent) {
                ServeEvent serving = (ServeEvent) curr;
                if (serving.getHasWaited()) {
                    curr = new ServeEvent(curr.getCust(), servers, 
                            serving.getSelectedId(), serving.getEventTime());
                } else {
                    curr = new ServeEvent(curr.getCust(), servers, 
                            serving.getSelectedId());
                }
            } else if (curr instanceof WaitEvent) {
                WaitEvent waiting = (WaitEvent) curr;
                curr = new WaitEvent(curr.getCust(), servers, waiting.getSelectedId());
            } else if (curr instanceof DoneEvent) {
                DoneEvent done = (DoneEvent) curr;
                curr = new DoneEvent(curr.getCust(), servers, 
                        done.getSelectedId(), done.getEventTime());
            }
            
            Event executed = curr.execute();
            servers = executed.getServers();
            
            if ((curr instanceof DoneEvent) || (curr instanceof LeaveEvent)) {
                continue;
            }
            tempQueue.add(executed);            
        }
        return finalQueue;
    }

    private void printPQ(PriorityQueue<Event> priorityQueue) {
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.remove());
        }
    }

    public static void main(String[] args) {
        List<Server> serverList = new ArrayList<Server>();
        List<Customer> custList = new ArrayList<Customer>();
        PriorityQueue<Event> priorityQueue = new PriorityQueue<>(new EventComparator());
        Scanner sc = new Scanner(System.in);

        int numOfServers = sc.nextInt();
        for (int i = 0; i < numOfServers; i++) {
            serverList.add(new Server(i + 1, true, false, 0.0));
        }
        
        int custId = 1;
        int numCust = 0;

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            Customer cust = new Customer(custId, arrivalTime);
            custId++;
            numCust++;
            custList.add(cust);
        }
    
        Main main = new Main();
        priorityQueue = main.run(serverList, custList);
        PriorityQueue<Event> pqForPrint = new PriorityQueue<Event>(priorityQueue);
        main.printPQ(pqForPrint);
        System.out.println(main.calcStats(priorityQueue, numCust));
    }
}
