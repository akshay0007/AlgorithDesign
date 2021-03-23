package org.example.bt.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphProblems {
    public static void main(String[] args) {
        GraphProblems graphProblems = new GraphProblems();
        Scanner scanner = new Scanner(System.in);
        System.out.println("[edges]");
        int edges = scanner.nextInt();
        System.out.println("[vertex]");
        int vertx = scanner.nextInt();
        boolean isDirected = true;
        List<List<Integer>> graph = graphProblems.createGraph(scanner, vertx, edges, isDirected);
        detectCycleInUndirect(graph, vertx, edges);
    }

    private static void detectCycleInUndirect(List<List<Integer>> graph, int vertx, int edges) {
        for (int i = 0; i < vertx; i++) {
            boolean[] isVisited = new boolean[vertx];
            List<Integer> adjList = graph.get(i);
            if (dfsTraversal(graph, adjList, isVisited, i)) {
                System.out.println("[cycle]");
            } else {
                System.out.println("[nocycle]");
            }
        }
    }

    private static boolean dfsTraversal(List<List<Integer>> graph, List<Integer> adjList, boolean[] isVisited, int parent) {
        for (Integer node : adjList) {
            if (isVisited[node] && node != parent) {
                System.out.println("[dect scycle termin]");
                return true;
            }
            List<Integer> adjNodes = graph.get(node);
            isVisited[node] = true;
            if (adjNodes.size() == 0) {
                continue;
            }
            if(dfsTraversal(graph, adjNodes, isVisited, node)){
                return true;
            }
        }
        markUnvisited(adjList,isVisited);
        return false;

    }

    private static void markUnvisited(List<Integer> adjList,boolean[] isVisited) {
        for (Integer integer : adjList) {
            isVisited[integer]=false;
        }
    }

    private List<List<Integer>> createGraph(Scanner scanner, int vertx, int edges, boolean isDirected) {
        List<List<Integer>> graph = new ArrayList<>();
        graphInit(graph, vertx);
        for (int i = 0; i < edges; i++) {
            System.out.println("[src]");
            int src = scanner.nextInt();
            System.out.println("[dst]");
            int dst = scanner.nextInt();
            graph.get(src).add(dst);
            if (!isDirected) {
                graph.get(dst).add(src);
            }
        }
        return graph;
    }

    private void graphInit(List<List<Integer>> graph, int vertx) {
        for (int i = 0; i < vertx; i++) {
            graph.add(new ArrayList<>());
        }
    }

}
