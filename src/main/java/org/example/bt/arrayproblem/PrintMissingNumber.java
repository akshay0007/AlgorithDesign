package org.example.bt.arrayproblem;

import java.util.Arrays;

public class PrintMissingNumber {
    public static void main(String[] args) {
        int arr[] = {7, 3, 4, 5, 5, 6, 2};
        int n = arr.length;
        printMissting(arr, arr.length);
    }

    private static void printMissting(int[] eles, int size) {
        int[] arr = new int[size+1];
        Arrays.fill(arr, 0);
        for (int i = 0; i < size; i++) {
            arr[eles[i]] = arr[eles[i]] + 1;
        }
        for (int i = 1; i <= size; i++) {
            if (arr[i] == 0)
                System.out.println("[missing]=" + i);
            if (arr[i] > 1)
                System.out.println("[dupicate]=" + i);
        }
    }
}
