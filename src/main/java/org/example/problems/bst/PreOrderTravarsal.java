package org.example.problems.bst;

public class PreOrderTravarsal {
    //20 16 5 18 17 19 60 85 70
    public static void main(String[] args) {
        int[] arr = {20, 16, 5, 18, 17, 19, 60, 85, 70};
        PreOrderTravarsal preOrderTravarsal = new PreOrderTravarsal();
        preOrderTravarsal.bstFromPreorder(arr);
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return createTree(preorder, 0, preorder.length - 1);
    }


    TreeNode createTree(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex) {
            System.out.println("overlapping and out of bound");
            return null;
        }
        TreeNode root = new TreeNode();
        int ele = arr[leftIndex];
        if (leftIndex == rightIndex) {
            System.out.println("only one element left");
            root.val = ele;
            return root;
        }
        int lastIndex = maxEleIndex(leftIndex, rightIndex, ele, arr);
        if (lastIndex == -1) {
            System.out.println("only left portion");
            root.val = ele;
            root.left = createTree(arr, leftIndex + 1, rightIndex);
        } else {
            System.out.println("both left and right exists");
            root.val = ele;
            root.left = createTree(arr, leftIndex + 1, lastIndex - 1);
            root.right = createTree(arr, lastIndex, rightIndex);
        }
        return root;
    }


    public int maxEleIndex(int leftIndex, int rightIndex, int data, int arr[]) {
        for (int i = leftIndex; i <= rightIndex; i++) {
            if (arr[i] > data) {
                return i;
            }
        }
        return -1;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
