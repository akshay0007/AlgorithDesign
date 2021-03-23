package org.example.bt;

import org.example.bt.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LevelViceTreeImple {

    public static void main(String[] args) {
        LevelViceTreeImple levelTree = new LevelViceTreeImple();
        TreeNode<Integer> root = levelTree.createTree();
        levelTree.printTree(root);
    }

    private void printTree(TreeNode<Integer> root) {
        printTree(root, null);
    }

    private String printTree(TreeNode<Integer> root, Integer node) {
        if (root == null)
            return "";
        StringBuilder str = new StringBuilder();
        if (node == null) {
            str.append(root.data + "=childs=:=");
        } else {
            str.append(node + "=childs=:=");
        }
        for (TreeNode<Integer> child : root.children) {
            str.append(printTree(child, node));
        }
        System.out.println(str);
        return str.toString();
    }


    private TreeNode<Integer> createTree() {
        Queue<TreeNode<Integer>> treeQueue = new LinkedList<>();
        System.out.println("please enter root node");
        Scanner scanner = new Scanner(System.in);
        int root = scanner.nextInt();
        TreeNode<Integer> rootNode = new TreeNode<>(root);
        treeQueue.add(rootNode);
        while (!treeQueue.isEmpty()) {
            TreeNode<Integer> node = treeQueue.poll();
            System.out.println("please enter no of childs for node=" + node.data);
            int childs = scanner.nextInt();
            for (int i = 0; i < childs; i++) {
                System.out.println("please enter child for node=" + node.data + "=child=" + i);
                TreeNode<Integer> child = new TreeNode<Integer>(scanner.nextInt());
                treeQueue.add(child);
                node.children.add(child);
            }
        }
        return rootNode;
    }

}

//0
//queue->0
//queue.poll-->0-->no of childs==>3
//queue.add->1,2,3

//queeu.qoll-->1
//no of childs-->3
//queue-->add-->1,2,3
