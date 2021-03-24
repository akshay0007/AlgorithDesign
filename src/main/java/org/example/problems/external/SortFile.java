package org.example.problems.external;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SortFile {
    public static void main(String[] args) {
        int arr[] = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int n = 4;
        SortFile sortFile = new SortFile();
        int arrRe = sortFile.findKthLargest(arr, n);
        System.out.println(arrRe);
//        LargButMinFreq(arr, n);
    }

    public int[] topKFrequent(int[] arr, int k) {
        TreeMap<Integer, Integer> mapCount = new TreeMap<>();
        for (int num : arr) {
            mapCount.putIfAbsent(num, 0);
            mapCount.put(num, mapCount.get(num) + 1);
        }
        List<Integer> list = mapCount.entrySet().stream()
                .sorted((c1, c2) -> c2.getValue() - c1.getValue())
                .peek(cons -> System.out.println(cons.getKey()))
                .map(e -> e.getKey()).collect(Collectors.toList());
        return list.stream().limit(k).mapToInt(i -> i).toArray();
    }

    public int findKthLargest(int[] arr, int k) {
        TreeMap<Integer, Integer> mapCount = new TreeMap<>();
        for (int num : arr) {
            mapCount.putIfAbsent(num, 0);
            mapCount.put(num, mapCount.get(num) + 1);
        }
        int[] els = mapCount.entrySet().stream()
                .map(en -> en.getKey())
                .mapToInt(ele -> ele).toArray();
        return els[k - 1];
    }

    public static int LargButMinFreq(int arr[], int n) {
        // Your code here
        TreeMap<Integer, Integer> mapCount = new TreeMap<>();
        for (int num : arr) {
            mapCount.putIfAbsent(num, 0);
            mapCount.put(num, mapCount.get(num) + 1);
        }
        return mapCount.size();
    }
}
