package org.example.problems.arrayproblem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingWindow {

    public static void main(String[] args) {
        int arr[] = {1,3,-1,-3,5,3,6,7};
//        1, 3,-1
        //1,2,3
        //1,2,3,
        int windowSize = 3;
        SlidingWindow slidingWindow=new SlidingWindow();
        slidingWindow.maxSlidingWindow(arr, windowSize);
    }

    private int[] maxSlidingWindow(int[] arr, int window) {
        ArrayDeque<Integer> sequenceList = new ArrayDeque<>();
        ArrayDeque<Integer> orderList = new ArrayDeque<>();
        List<Integer> maxList = new ArrayList<>();

        int index = 0;
        int windowSize = 0;
        windowSize++;
        sequenceList.add(index);
        orderList.add(index);
        index++;
        windowSize++;
        for (; index < arr.length; index++) {
            windowSize++;
            if (isWindowOutOfBound(window, sequenceList, orderList,window)) {
                windowSize = window - 1;
                int orderIndexFirst = orderList.peekFirst();
                int seqIndexFirst = sequenceList.peekFirst();
                if (orderIndexFirst == seqIndexFirst) {
                    System.out.println("[first and out is equals]");
                    orderList = makeOrderToOrderListAndRemoveOutOfIndex(orderList, sequenceList, arr, index);
                    maxList.add(arr[orderList.peek()]);
                } else {
                    System.out.println("[first and out is not equals]");
                    maxList.add(arr[orderIndexFirst]);
                }
            } else {
                makeOrderToOrderListAndRemoveOutOfIndex(orderList, sequenceList, arr, index);
            }

        }
        System.out.println(maxList);
        int[] elems=new int[maxList.size()];
        for (int i = 0; i < maxList.size(); i++) {
            elems[i]=maxList.get(i);
        }
        return elems;
    }

    private boolean isWindowOutOfBound(int window, ArrayDeque<Integer> sequenceList, ArrayDeque<Integer> orderList, int i) {
        return !sequenceList.isEmpty() && !orderList.isEmpty() && window >= window;
    }

    private ArrayDeque<Integer> makeOrderToOrderListAndRemoveOutOfIndex(
            ArrayDeque<Integer> sequenceList,
            ArrayDeque<Integer> orderList,
            int[] arr, int index) {
        sequenceList.add(index);
        int orderFirst = orderList.peekFirst();
        if (arr[orderFirst] < arr[index]) {
            orderList.pollFirst();
            orderList.offerFirst(index);
            sequenceList.remove(orderFirst);
            makeOrderToOrderListAndRemoveOutOfIndex(sequenceList, orderList, arr, index);
            System.out.println("[again recursive call to check order and seq list]");
            return orderList;
        } else {
            ArrayDeque<Integer> newQueue = new ArrayDeque<>();
            for (Integer orderEle : orderList) {
                if (arr[index] > arr[orderEle]) {
                    newQueue.add(index);
                    continue;
                }
                newQueue.add(orderEle);
            }
            System.out.println("[new queue return]");
            return newQueue;
        }
    }


}
