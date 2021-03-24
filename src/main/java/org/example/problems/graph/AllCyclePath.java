package org.example.problems.graph;

import java.util.ArrayList;
import java.util.List;

public class AllCyclePath {
    public static void main(String[] args) {
        AllCyclePath allCyclePath = new AllCyclePath();
//        int[][] graph = {{1, 2}, {3}, {3}, {}};
        int[][] graph = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        allCyclePath.allPathsSourceTarget(graph);
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> graphList = new ArrayList<>();
        createGraph(graph, graphList);
        return graphList;
    }

    private void createGraph(int[][] graph, List<List<Integer>> graphList) {
        int src=0;
        int dst=graphList.size();
        List<Integer> listItem=new ArrayList<>();
        dfsGraphTraversal(src,dst,graph,graphList,listItem);
        System.out.println("[dfs]");
    }

    private void dfsGraphTraversal(int src, int dst, int[][] graph, List<List<Integer>> graphList, List<Integer> listItem) {
        if(src==dst){
            graphList.add(new ArrayList<>(listItem));
            return;
        }

        for (Integer ele : graphList.get(src)) {
            listItem.add(ele);
            dfsGraphTraversal(ele,dst,graph,graphList,listItem);
            listItem.remove(listItem.size()-1);
        }

    }


}
