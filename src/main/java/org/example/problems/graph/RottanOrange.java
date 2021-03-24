package org.example.problems.graph;


import java.util.LinkedList;
import java.util.Queue;

public class RottanOrange {
    public static void main(String[] args) {
//        int[][] grid = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        int[][] grid = {{0, 1}};
        RottanOrange rottanOrange = new RottanOrange();
        rottanOrange.orangesRotting(grid);
    }

    public int orangesRotting(int[][] grid) {
        Queue<Pair<Integer, Integer>> rottanQueue = new LinkedList<>();
        int peopleCount = getRottanAndFreshOrange(grid, rottanQueue);
        if (peopleCount == 0) {
            System.out.println("[count]=" + 0);
            return 0;
        }
        if (rottanQueue.isEmpty()) {
            return -1;
        }

        int count = 0;
        boolean isTerminating = false;
        while ((!rottanQueue.isEmpty() && !isTerminating)) {
            count++;
            int size = rottanQueue.size();
            System.out.println("[count]=" + count);
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> rottonPos = rottanQueue.poll();
                peopleCount = putAdjListAndConvertOrangeToRotton(grid, rottonPos, peopleCount, rottanQueue);
                if (peopleCount == 0) {
                    isTerminating = true;
                    System.out.println("[people-found]=" + peopleCount);
                }
            }
        }
        if (peopleCount != 0) {
            System.out.println("[count]=" + -1);
            return -1;
        }
        System.out.println("[count]=" + count);
        return count;
    }

    private int putAdjListAndConvertOrangeToRotton(int[][] grid, Pair<Integer, Integer> rottonPos,
                                                   Integer peopleCount, Queue<Pair<Integer, Integer>> rottanQueue) {
        int x = rottonPos.first;
        int y = rottonPos.second;
        peopleCount = addCordinates(rottanQueue, grid, x + 1, y, peopleCount);
        peopleCount = addCordinates(rottanQueue, grid, x - 1, y, peopleCount);
        peopleCount = addCordinates(rottanQueue, grid, x, y + 1, peopleCount);
        peopleCount = addCordinates(rottanQueue, grid, x, y - 1, peopleCount);
        return peopleCount;
    }

    private int addCordinates(Queue<Pair<Integer, Integer>> rottonQueue, int[][] grid, int x, int y, Integer peopleCount) {
        if (x < 0 || grid.length <= x || y < 0 || grid[0].length <= y) {
            return peopleCount;
        } else {
            if (grid[x][y] == 1) {
                grid[x][y] = 2;
                rottonQueue.add(new Pair(x, y));
                peopleCount--;
            }
        }
        return peopleCount;
    }


    private int getRottanAndFreshOrange(int[][] grid, Queue<Pair<Integer, Integer>> rottanQueue) {
        int peopleCount = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 1) {
                    peopleCount++;
                } else if (grid[row][col] == 2) {
                    rottanQueue.add(new Pair<>(row, col));
                }
            }
        }
        return peopleCount;
    }

    class Pair<T, R> {
        T first;
        R second;

        public Pair(T first, R second) {
            this.first = first;
            this.second = second;
        }
    }
}
