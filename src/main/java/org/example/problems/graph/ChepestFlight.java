package org.example.problems.graph;

import java.util.*;

public class ChepestFlight {
    public static void main(String[] args) {
        ChepestFlight chepestFlight = new ChepestFlight();
        int n = 3;
        int[][] edges = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0, dst = 2, k = 1;
        chepestFlight.findCheapestPrice(n, edges, src, dst, k);
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<Integer[]>> adjMap = createFlightAdjMap(flights, K);
        int chepestPrice = findMinCost(adjMap, src, dst, K);
        return chepestPrice;
    }

    private int findMinCost(Map<Integer, List<Integer[]>> adjMap, int src, int dst, int k) {
        PriorityQueue<CityDetails> queueMap = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.amt, c2.amt));
        List<Integer[]> srcList = adjMap.get(src);
        if (srcList == null) {
            return -1;
        }
        if(srcList.size()==0){
            return -1;
        }
        int stopage = k;
        queueMap.add(addCityDetails(null, 0, 0, stopage));
        while (!queueMap.isEmpty()) {
            int size = queueMap.size();
            for (int i = 0; i < size; i++) {
                CityDetails city = queueMap.poll();
                System.out.println("[city]=" + city.vertex);
                if (!adjMap.containsKey(city.vertex)) {
                    System.out.println("[not-exist]=" + city.vertex);
                    continue;
                }
                List<Integer[]> adjCities = adjMap.get(city.vertex);
                for (Integer[] adjCity : adjCities) {
                    CityDetails cityDetails = addCityDetails(city, adjCity[1], adjCity[2], stopage);
                    if (stopage == 0) {
                        if(cityDetails.vertex==dst){
                            System.out.println("[stoppage]=[amt]=" + cityDetails.amt);
                            return cityDetails.amt;
                        }
                    }
                    System.out.println("[adding]=" + cityDetails.vertex);
                    queueMap.add(cityDetails);
                }
            }
            if(stopage==0){
                return -1;
            }
            stopage--;
        }
        return -1;
    }

    private CityDetails addCityDetails(CityDetails city, Integer dst, Integer cost, int stopage) {
        CityDetails cityDetails = new CityDetails();
        cityDetails.vertex = dst;
        cityDetails.stopCount = stopage;
        if (city == null) {
            cityDetails.amt = cost;
        } else {
            cityDetails.amt = city.amt + cost;
        }
        return cityDetails;
    }

    private Map<Integer, List<Integer[]>> createFlightAdjMap(int[][] flights, int stopages) {
        Map<Integer, List<Integer[]>> flightAdjMap = new HashMap<>();
        for (int[] flight : flights) {
            flightAdjMap.putIfAbsent(flight[0], new ArrayList<>());
            flightAdjMap.get(flight[0]).add(new Integer[]{flight[0],flight[1],flight[2]});
        }
        return flightAdjMap;
    }

    class CityDetails {
        int vertex;
        int amt = Integer.MAX_VALUE;
        int stopCount;
    }

}
