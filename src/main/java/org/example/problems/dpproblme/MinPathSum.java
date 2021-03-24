package org.example.problems.dpproblme;

import java.util.Arrays;
import java.util.function.Predicate;

public class MinPathSum {
    public static void main(String[] args) {
        MinPathSum minPathSum = new MinPathSum();
        int[][] arr = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
//        int minValue = minPathSum.minPathSum(arr);
        int minValue = minPathSum.dpSolution(arr);
        System.out.println("[sumvalue]=" + minValue);
    }

    private int dpSolution(int[][] arr) {
        int[][] dpArr = new int[arr.length][arr[0].length];
        for (int[] rows : dpArr) {
            Arrays.fill(rows, Integer.MAX_VALUE);
        }
        for (int row = dpArr.length - 1; row >= 0; row--) {
            for (int col = dpArr[row].length - 1; col >= 0; col--) {
                dpArr[row][col] = getDpItem(arr, dpArr, row, col);
            }
        }
        System.out.println("[grid]" + dpArr);
        return 0;
    }

    private int getDpItem(int[][] arr, int[][] dp, int row, int col) {
        int sum = arr[row][col];
        Predicate<int[][]> isColInBoundry = (int[][] grid) -> {
            if (grid[row].length <= (col + 1) || (col) < 0) {
                return true;
            }
            return false;
        };
        Predicate<int[][]> isRowInBoundry = (int[][] grid) -> {
            if (grid.length <= (row + 1) || row < 0) {
                return true;
            }
            return false;
        };

        if (isColInBoundry.test(arr) && isRowInBoundry.test(arr)) {
            return arr[row][col];
        }
        if (isColInBoundry.test(arr)) {
            sum = Math.min((sum + arr[row + 1][col]), dp[row][col]);
        } else if (isRowInBoundry.test(arr)) {
            sum = Math.min((sum + arr[row][col + 1]), dp[row][col]);
        } else {
            int minValue = Math.min((sum + arr[row + 1][col]), (sum + arr[row][col + 1]));
            sum = Math.min(dp[row][col], minValue);
        }
        return sum;
    }


    public int minPathSum(int[][] arr) {
        boolean[][] isVisited = new boolean[arr.length][arr.length];
        int row = 0, col = 0;
        return dfsAlgo(arr, isVisited, row, col);
    }

    private int dfsAlgo(int[][] graph, boolean[][] isVisited, int row, int col) {
        if (isBoundryCondition(row, col, graph)) {
            System.out.println("[termination]=");
            return Integer.MAX_VALUE;
        }
        if (row == (graph.length - 1) || col == (graph[row].length - 1)) {
            System.out.println("[dst-condition]");
            return graph[row][col];
        }
//        int rowPlusOne = dfsAlgo(graph, isVisited, row + 1, col);
        int rowMinOne = dfsAlgo(graph, isVisited, row - 1, col);
        int colPlusOne = dfsAlgo(graph, isVisited, row, col + 1);
//        int colMinOne = dfsAlgo(graph, isVisited, row, col - 1);
        int returnValue = min(rowMinOne, colPlusOne);
        System.out.println("[sum-value]=");
        return (graph[row][col] + returnValue);
    }

    private int min(int... items) {
        int minItem = Integer.MAX_VALUE;
        for (int item : items) {
            minItem = Math.min(minItem, item);
        }
        return minItem;
    }

    private boolean isBoundryCondition(int row, int col, int[][] graph) {
        return (graph.length <= row || row < 0 || graph[row].length <= col || col < 0);
    }
}
