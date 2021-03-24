package org.example.problems.arrayproblem;

import java.util.Arrays;

public class MergeInterval {
    public static void main(String[] args) {
        MergeInterval mergeInterval = new MergeInterval();
        int[][] arr = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
        mergeInterval.mergeSolve(arr);
    }

    private void mergeSolve(int[][] arr) {
        Arrays.sort(arr, (c1, c2) -> c1[0] - c2[0]);
        int[][] newArr = new int[4][4];
        System.out.println("[arr]=" + arr);
        int preItemSecond = -1;
        int preItemFirst = -1;
        int count = 0;
        for (int[] oneD : arr) {
            int first = oneD[0];
            int second = oneD[1];
            if (preItemSecond == -1) {
                preItemSecond = second;
                preItemFirst = first;
            } else {
                if (preItemSecond >= first) {
                    newArr[count] = new int[]{preItemFirst, second};
                    count++;
                } else {
                    newArr[count] = new int[]{first, second};
                    count++;
                }
            }
        }
        System.out.println("[items]=" + newArr);
    }
}
