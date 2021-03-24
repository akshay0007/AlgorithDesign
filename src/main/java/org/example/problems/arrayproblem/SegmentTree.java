package org.example.problems.arrayproblem;

import java.util.*;

public class SegmentTree {
    public static void main(String[] args) {
        System.out.println("[segment tree]");
        SegmentTree segmentTree = new SegmentTree();
        segmentTree.constructSegmentTree();
    }

    private void constructSegmentTree() {
        int arr[] = {1, 3, 5, 7, 9, 11,14,3,67,1};
        callSegUpdate(arr, 0, arr.length - 1);
        System.out.println("[segtree]=" + segList);
        printSegTree(segList);
    }

    private void printSegTree(Queue<Integer> segQueue) {
        int pow = 0;
        while (!segQueue.isEmpty()) {
            double size = Math.pow(2, pow);
            for (int i = 0; i < size; i++) {
                if (segQueue.isEmpty()) {
                    System.out.println("");
                    System.out.print("[termination]");
                    return;
                }
                System.out.print(segQueue.poll() + " ");
            }
            System.out.println("");
            pow++;
        }
    }


    Queue<Integer> segList = new LinkedList<>();

    private int callSegUpdate(int[] arr, int left, int right) {
//        System.out.println("[init]=>[left]=" + left + "=[right]=" + right);
        if (left >= right) {
            System.out.println("[termination=[left]=" + left + "=[right]=" + right + "=[value]=" + arr[left]);
            segList.add(arr[left]);
            return arr[left];
        }
        int mid = (left + right) / 2;
        int value = callSegUpdate(arr, left, mid) +
                callSegUpdate(arr, mid + 1, right);
        System.out.println("[left]=" + left + "=[right]=" + right + "=[value]=" + value);
        segList.add(value);
        return value;
    }


    enum NodeType {
        LEFT(1), RIGHT(2);

        private final int val;

        NodeType(int index) {
            this.val = index;
        }
    }

    private int getParent(int index) {
        return (int) (Math.ceil(index / 2) - 1);
    }

    private int getNodeChildIndex(int index, NodeType nodeType) {
        return 2 * index + nodeType.val;
    }
}
