package org.example.problems.testdir;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pencake {
    public static void main(String[] args) {
        Pencake pencake = new Pencake();
        //[10,2,5,10,9,1,1,4,3,7]
//        pencake.findDuplicates(new int[]{10,2,5,10,9,1,1,4,3,7});
        pencake.findCycle(new int[]{10, 2, 5, 10, 9, 1, 1, 4, 3, 7});
    }

    class A{

    }


    private void findCycle(int[] ints) {
        String arr[]={"arr","add"};
        List<List<Integer>> graph = new ArrayList<>();
        createGraph(graph, ints);
    }

    private void createGraph(List<List<Integer>> graph, int[] eles) {
        //0-1
        //1-2
        //2-3
        //3-4
        //4
        for (int i = 0; i < 5; i++) {
            int length = graph.size();
            if (length <= i) {

            }
        }
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                System.out.println("duplicat evalues=" + Math.abs(nums[index]));
                result.add(Math.abs(nums[i]));
            }
            nums[index] = -(nums[index]);
        }
        result.forEach(System.out::println);
        return result;
    }

    private static void extract() {
        Integer[] arr = {3, 2, 1, 4};
        Pencake pencake = new Pencake();
        pencake.pancakeSort(arr);
    }

    List<Integer> cache = new ArrayList<>();

    public List<Integer> pancakeSort(Integer[] arr) {
        List<Integer> arrays = Arrays.asList(arr);
        extracted(arrays, arrays.size());
        cache.stream().forEach(System.out::println);
        return cache;
    }

    private int findMaxIndex(List<Integer> arrays, int lastIndex) {
        int maxPos = Integer.MIN_VALUE;
        int pos = -1;
        for (int i = 0; i < lastIndex; i++) {
            int current = arrays.get(i);
            if (maxPos < current) {
                maxPos = current;
                pos = i;
            }
        }
        return pos;
    }

    private void extracted(List<Integer> arrays, int lastIndex) {
//        System.out.println("before==" + lastIndex);
        if (lastIndex <= 0) {
//            System.out.println("exit==" + lastIndex);
            return;
        }
        int maxPos = findMaxIndex(arrays, lastIndex);
        int firstPos = 0;
        if ((maxPos + 1) == lastIndex) {
            extracted(arrays, lastIndex - 1);
        } else {
            reverse(arrays, firstPos, maxPos);
            reverse(arrays, firstPos, lastIndex - 1);
            addToCache(maxPos, lastIndex);
            extracted(arrays, lastIndex - 1);
        }
//        System.out.println("after==" + lastIndex);
    }

    private void addToCache(int lastPos, int lastIndex) {
        System.out.println("cache value=" + lastPos + 1 + "=lastIndex=" + lastIndex);
        cache.add(lastPos + 1);
        cache.add(lastIndex);
    }


    public void reverse(List<Integer> arr, int first, int last) {
        for (int i = first, j = last; i < j; i++, j--) {
            int left = arr.get(i);
            int right = arr.get(j);
            arr.set(i, right);
            arr.set(j, left);
        }
    }
}
