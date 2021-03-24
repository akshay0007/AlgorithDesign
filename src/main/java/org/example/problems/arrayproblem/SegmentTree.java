package org.example.problems.arrayproblem;

import java.util.Arrays;

public class SegmentTree {
    public static void main(String[] args) {
        System.out.println("[segment tree]");
        SegmentTree segmentTree = new SegmentTree();
        segmentTree.constructSegmentTree();
    }

    private void constructSegmentTree() {
        int arr[] = {1, 3, 5, 7, 9, 11};
        constructTree(arr);
    }

    private void constructTree(int[] arr) {
        int[] segTree = new int[arr.length];
        Arrays.fill(segTree, 0);
//        callSegUpdate(arr, segTree);
//        for (int index = 0; index < arr.length; index++) {
//            updateSegmentNode(arr[index],index,segTree);
//        }
    }

    private void callSegUpdate(int[] arr, int[] segTree, int mid, int size) {
        if (mid > size) {

        }
        callSegUpdate(arr, segTree, 0, mid);
        callSegUpdate(arr, segTree, mid + 1, size);
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
