package org.example.bt.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CycleDetect {

    public static void main(String[] args) {
        CycleDetect cycleDetect = new CycleDetect();
        int[] arr = {1, 4, 6};
        cycleDetect.closestNumber(arr, 5);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        return null;
    }

    public int closestNumber(int[] A, int target) {
        if (A == null || A.length <= 0)
            return -1;
        int minDist = Integer.MAX_VALUE;
        List<Integer> listArr = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int nextMin = Math.abs((A[i] - target));
            if (minDist > nextMin) {
                System.out.println("less value");
                System.out.println("size value=" + listArr.size());
                listArr.clear();
                minDist = nextMin;
            }
            if (minDist == nextMin) {
                listArr.add(i);
            }
        }
        return listArr.size();
    }


    public int maxProfit(int[] prices) {
        int atMosttxn = 2;
        int withouttxn = 0;
        boolean isSell = false;
        boolean isBuy = true;
        for (int price : prices) {
            if (isBuy) {

            } else {

            }
        }
        return 0;
    }


    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        for (int i = 0; i < V; i++) {
            Map<Integer, Boolean> cache = new HashMap<>();
            if (dfs(adj, i, cache)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(ArrayList<ArrayList<Integer>> paths, Integer ele, Map<Integer, Boolean> cache) {
        for (Integer nextEle : paths.get(ele)) {
            if (nextEle != ele && cache.containsKey(nextEle) && cache.get(nextEle)) {
                return true;
            }
            cache.putIfAbsent(nextEle, true);
            dfs(paths, nextEle, cache);
        }
        return false;
    }
}
