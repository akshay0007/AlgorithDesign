package org.example.problems.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Bipart {
    public static void main(String[] args) {
        Bipart bipart = new Bipart();
        int[][] graph = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        bipart.isBipartiteDFS(graph);
    }

    public boolean isBipartiteDFS(int[][] graph) {
        int[] color = new int[graph.length];
        int src = 0;
        color[src] = 1;
        return dfsTraversal(src, color, graph);
    }

    private boolean dfsTraversal(int src, int[] color, int[][] graph) {
        for (int ele : graph[src]) {
            if (color[src] == color[ele]) {
                System.out.println("[bi-false]");
                return false;
            } else if (color[ele] == 0) {
                color[ele] = -color[src];
                if (!dfsTraversal(ele, color, graph)) {
                    System.out.println("[bi-false]");
                    return false;
                }
            }
        }
        System.out.println("[bi-true]");
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        boolean[] isVisited = new boolean[graph.length];
        int[] color = new int[graph.length];
        //0->n,1->r,-1->b
        Queue<Integer> queue = new LinkedList<>();
        int src = 0;
        queue.add(src);
        color[src] = 1;
        while (!queue.isEmpty()) {
            int pollItem = queue.poll();
            for (int item : graph[pollItem]) {
                if (!isVisited[item] && color[item] == 0) {
                    queue.add(item);
                    isVisited[item] = true;
                    color[item] = -color[src];
                } else if (color[src] == color[item]) {
                    return false;
                }
            }
        }
        return true;
    }
}
