package org.example.problems;

import org.example.problems.utils.TreeNode;

import java.util.Scanner;

public class NTree {

    public static void main(String[] args) {
        NTree nTree = new NTree();
        TreeNode<Integer> rootNode = nTree.takeInput();
        nTree.printTree(rootNode.data, rootNode);
    }

    private void printTree(Integer root, TreeNode<Integer> rootNode) {
        if (rootNode == null) {
            return;
        }
        System.out.println(root + "==" + rootNode.data);
        for (TreeNode<Integer> child : rootNode.children) {
            printTree(rootNode.data, child);
        }
    }

    private TreeNode<Integer> takeInput() {
        System.out.println("Please enter next node");
        Scanner scanner = new Scanner(System.in);
        int root = scanner.nextInt();
        TreeNode<Integer> rootNode = new TreeNode<Integer>(root);
        System.out.println("please enter no. of childs");
        int childs = scanner.nextInt();
        for (int i = 0; i < childs; i++) {
            TreeNode<Integer> child = takeInput();
            rootNode.children.add(child);
        }
        return rootNode;
    }


}
