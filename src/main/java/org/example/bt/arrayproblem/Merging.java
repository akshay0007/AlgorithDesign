package org.example.bt.arrayproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Merging {
    public static void main(String[] args) {
        int ar1[] = {1, 5, 9, 10, 15, 20};
        int ar2[] = {2, 3, 8, 13};
//        mergeArraySort(ar1, ar2);
//        mergeInsertionSort(ar1, ar2);
        gapAlgo(ar1, ar2);
    }

    private static void gapAlgo(int[] ar1, int[] ar2) {
        int[] longArr = new int[ar1.length + ar2.length];
        for (int i = 0; i < longArr.length; i++) {
            if (i < ar1.length) {
                longArr[i] = ar1[i];
            } else {
                longArr[i] = ar2[(i - ar1.length)];
            }
        }
        int gap = longArr.length / 2;
        gepAlgoApply(longArr, ar1, ar2, gap);
        System.out.println("[arr]="+longArr);
    }

    private static void gepAlgoApply(int[] longArr, int[] ar1, int[] ar2, int window) {
        if (window < 1) {
            System.out.println("[temination]");
            return;
        }
        int left = 0;
        int right = left + window;
        while (left >= longArr.length || right >= longArr.length) {
            if (longArr[left] > longArr[right]) {
                int temp = longArr[left];
                longArr[left] = longArr[right];
                longArr[right] = temp;
            }
            left++;
            right++;
        }
        window = window / 2;
        gepAlgoApply(longArr, ar1, ar2, window);
    }

    private static void mergeInsertionSort(int[] ar1, int[] ar2) {
        for (int index1 = 0; index1 < ar1.length; index1++) {
            int itemOne = ar1[index1];
            for (int index2 = 0; index2 < ar2.length; index2++) {
                if (itemOne > ar2[index2]) {
                    swapItems(ar1, index1, ar2, index2);
                }
            }
        }
        Arrays.sort(ar1);
        Arrays.sort(ar2);
        System.out.println("[Items]=" + ar1);
    }

    private static void swapItems(int[] ar1, int index1, int[] ar2, int index2) {
        int temp = ar1[index1];
        ar1[index1] = ar2[index2];
        ar2[index2] = temp;
    }

    private static void mergeArraySort(int[] ar1, int[] ar2) {
        List<Integer> list1 = addIntoList(ar1);
        List<Integer> list2 = addIntoList(ar2);
        list1.sort((c1, c2) -> Integer.compare(c1, c2));
        list2.sort((c1, c2) -> Integer.compare(c1, c2));
        list1.addAll(list2);
        list1.forEach(System.out::println);
    }

    private static List<Integer> addIntoList(int[] ar1) {
        List<Integer> listArray = new ArrayList<>();
        for (int item : ar1) {
            listArray.add(item);
        }
        return listArray;
    }
}
