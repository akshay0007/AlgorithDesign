package org.example.bt.arrayproblem;

import java.util.Arrays;
import java.util.Scanner;

public class BinaryIndexTree {
    public static void main(String[] args) {
        BinaryIndexTree binaryIndexTree = new BinaryIndexTree();
        binaryIndexTree.constructBinaryIndex(8);
    }

    private void constructBinaryIndex(int number) {
        int freq[] = {2, 1, 1, 3, 2, 3,
                4, 5, 6, 7, 8, 9};

//        Scanner sc = new Scanner(System.in);

        createFenwikTree(freq);
    }

    private void createFenwikTree(int[] arr) {
        int[] fenwikTree = new int[arr.length + 1];
        Arrays.fill(fenwikTree, 0);
        for (int index = 0; index < arr.length; index++) {
            fenwikTree = updateTreeNode(index, fenwikTree, arr[index], arr.length);
        }
        System.out.println("[fenwik-tree]=" + fenwikTree);
        System.out.println("Sum of elements in arr[0..5]" +
                " is " + getSum(5, fenwikTree));
    }

    private int getSum(int index, int[] fenwikTree) {
        int sum = 0;
        index = index + 1;
        while (index > 0) {
            sum = sum + fenwikTree[index];
            index -= (index & (-index));
        }
        return sum;
    }

    private int[] updateTreeNode(int index, int[] fenwikArr, int val, int totalSize) {
        index = index + 1;
        while (index < totalSize) {
            fenwikArr[index] = val + fenwikArr[index];
            index = index + (index & (-index));
            System.out.println("[fenwik loop=index]=" + index);
        }
        return fenwikArr;
    }

    private int getLcf(int number) {
        return number & (-number);
    }
}
