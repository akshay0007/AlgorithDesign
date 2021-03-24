package org.example.problems.graph;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {
    public static void main(String[] args) {
//        TopologicalSort topologicalSort=new TopologicalSort();
//        topologicalSort.
        topoSort(9, getAdjList());
    }

    private static List<List<Integer>> getAdjList() {
        return Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(4),
                Arrays.asList(),
                Arrays.asList(3),
                Arrays.asList(1, 5),
                Arrays.asList(8),
                Arrays.asList()
        );
    }


    static int[] topoSort(int v, List<List<Integer>> adj) {
        // add your code here
        boolean[] isVisited = new boolean[v];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < v; i++) {
            topo(adj, isVisited, stack, i);
        }
        int[] arr = new int[stack.size()];
        int count = 0;
        while (!stack.isEmpty()) {
            arr[count++] = stack.pop();
        }
        return arr;
    }

    private static void topo(List<List<Integer>> adj, boolean[] isVisited, Stack<Integer> stack, int el) {
        for (Integer ele : adj.get(el)) {
            if (isVisited.length > ele || !isVisited[ele]) {
                isVisited[ele] = true;
                topo(adj, isVisited, stack, ele);
            }
        }
        stack.push(el);
    }
}
