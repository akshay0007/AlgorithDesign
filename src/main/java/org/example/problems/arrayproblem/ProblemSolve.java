package org.example.problems.arrayproblem;

public class ProblemSolve {
    public static void main(String[] args) {
        int[] arr = {0};
        ProblemSolve problemSolve = new ProblemSolve();
        int slidewindow = arr.length / 2;
        System.out.println("[sliding size]=" + slidewindow);
        int[] ele = problemSolve.solveAns(arr, slidewindow);
        System.out.println("[arr]=" + ele);
    }

    public int findDuplicate(int[] nums) {
        int returnValue = -1;
        for (int ind = 0; ind < nums.length; ind++) {

        }
        return returnValue;
    }

    private int[] solveAns(int[] arr, int slideWindow) {
        if (slideWindow < 1) {
            System.out.println("[termination]");
            return arr;
        }
        int index = 0;
        int slidingIndex = index + slideWindow;
        while (index < arr.length) {
            if (slidingIndex < arr.length && arr[index] > arr[slidingIndex]) {
                int temp = arr[index];
                arr[index] = arr[slidingIndex];
                arr[slidingIndex] = temp;
            }
            index = index + 1;
            slidingIndex = slidingIndex + 1;
        }
        slideWindow = slideWindow / 2;
        System.out.println("[sliding size]=" + slideWindow);
        return solveAns(arr, slideWindow);
    }
}
