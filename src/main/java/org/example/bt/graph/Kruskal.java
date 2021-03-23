package org.example.bt.graph;

import java.util.*;

public class Kruskal {
    public static void main(String[] args) {
        Integer[][] arr = {{0, 1, 1}, {0, 2, 3}, {0, 3, 9}, {1, 3, 8}, {2, 4, 7}, {2, 5, 5}, {3, 4, 10}, {3, 5, 6}, {4, 5, 2}};
        Kruskal kruskal = new Kruskal();
        kruskal.createSpanningTree(arr, 6);
    }

    private void createSpanningTree(Integer[][] list, int vertex) {
        Integer[] parentSet = new Integer[vertex];
        for (int i = 0; i < vertex; i++) {
            parentSet[i] = i;
        }
        PriorityQueue<Integer[]> priorityQueue = new PriorityQueue<>((c1, c2) -> Integer.compare(c1[2], c2[2]));
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (Integer[] rows : list) {
            priorityQueue.add(rows);
        }
        while (!priorityQueue.isEmpty()) {
            Integer[] ele = priorityQueue.poll();
            graph.putIfAbsent(ele[0], new ArrayList<>());
            if (!hasPath(ele[0], ele[1], parentSet)) {
                graph.get(ele[0]).add(ele[1]);
            }
        }
        System.out.println("[graph]=" + graph);
    }

    public boolean hasPath(Integer v1, Integer v2, Integer[] parentSet) {
        if (parentSet[v1] == parentSet[v2]) {
            return true;
        } else {
            parentSet[v1] = v2;
        }
        return false;
    }

    public boolean hasPath(Integer v1, Integer v2, Map<Integer, List<Integer>> graph) {
        if (graph.containsKey(v1)) {
            for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
                if (entry.getValue().contains(v2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
