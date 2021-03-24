package org.example.problems.dpproblme;

public class RobFrom {
    public static void main(String[] args) {
        RobFrom rfo = new RobFrom();
        int[] arr = {2, 7, 9, 3, 1};
        rfo.solve(arr);
    }

    private void solve(int[] arr) {
        int src = 0;
        Integer[] cache = new Integer[arr.length];
        int totalAmt = robSolverDfs(arr, src, cache);
        System.out.println("[total-Amount]=" + totalAmt);
    }

    private int robSolverDfs(int[] arr, int src, Integer[] cache) {
        if (arr.length <= src || src < 0) {
            System.out.println("[termination]");
            return 0;
        }
        if (cache[src] != null) {
            System.out.println("[pick from cache]");
            return cache[src];
        }
        int robInHome = arr[src] + robSolverDfs(arr, (src + 2), cache);
        int skipHome = robSolverDfs(arr, src + 1, cache);

        int amtRob = Math.max(robInHome, skipHome);
        cache[src] = amtRob;
        System.out.println("[total max amt rob]=" + amtRob);
        return amtRob;
    }
}
