package org.example.bt.graph;

import java.util.*;

public class IternaryTravel {
    public static void main(String[] args) {
        IternaryTravel iternaryTravel = new IternaryTravel();
        List<List<String>> tickets = getTickets();
        iternaryTravel.findItinerary(tickets);
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> mapAdj = getTicktsAdjMap(tickets);
        String srcPath = "JFK";
        LinkedList<String> answere = new LinkedList<>();
        answere.add(srcPath);
        ticketFinder(srcPath, mapAdj, answere);
        answere.forEach(System.out::println);
        return answere;
    }

    private void ticketFinder(String src, Map<String, PriorityQueue<String>> ticketMap, LinkedList<String> answere) {
        PriorityQueue<String> pqueue = ticketMap.get(src);
        if (pqueue != null && !pqueue.isEmpty()) {
            String pollItem = pqueue.poll();
            System.out.println("[pollitem]=" + pollItem);
            answere.add(pollItem);
            ticketFinder(pollItem, ticketMap, answere);
        }
    }


    private Map<String, PriorityQueue<String>> getTicktsAdjMap(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> ticketMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            ticketMap.putIfAbsent(ticket.get(0), getTicketQueue());
            ticketMap.get(ticket.get(0)).add(ticket.get(1));
        }
        return ticketMap;
    }

    private PriorityQueue<String> getTicketQueue() {
        return new PriorityQueue<>((c1, c2) -> c2.length() - c1.length());
    }


    private static List<List<String>> getTickets() {
        return Arrays.asList(
                Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO")
        );
    }


}
