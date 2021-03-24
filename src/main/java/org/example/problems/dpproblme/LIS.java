package org.example.problems.dpproblme;

public class LIS {
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 4};
        LIS lis = new LIS();
        lis.solvePairs(arr);
    }

    private void solvePairs(int[] arr) {
        int size = dfsSolver(arr, 0, -1);
        System.out.println("[size]=" + size);
    }

    private int dfsSolver(int[] arr, int index, int preIndex) {
        if (index >= arr.length || preIndex >= arr.length) {
            System.out.println("[termination]");
            return 0;
        }
        int include =0;
        if (preIndex == -1 || arr[preIndex] < arr[index]) {
            include = 1 + dfsSolver(arr, index + 1, index);
            System.out.println("[count]=" + include);
        }
        int exclude = dfsSolver(arr, index + 1, preIndex);
        return Math.min(include,exclude);
    }
}
