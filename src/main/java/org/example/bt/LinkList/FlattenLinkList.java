package org.example.bt.LinkList;

import java.util.*;

public class FlattenLinkList {
    public static void main(String[] args) {
        FlattenLinkList flattenLinkList = new FlattenLinkList();
//        flattenLinkList.
    }

    class Node {
        int data;
        Node next;
        Node bottom;

        Node(int data) {
            this.data = data;
            this.next = this.bottom = null;
        }
    }


    Node flatten(Node root) {
        if (root == null || root.next == null)
            return root;
        Node nextNode = flatten(root.next);
        Node finalNode = mergeIntoSingle(root, nextNode);
        return finalNode;
    }

    private Node mergeIntoSingle(Node root, Node nextNode) {
        while (root != null || nextNode != null) {
            if (root.data > nextNode.data) {
                nextNode.data=root.data;
            }
        }
        return null;
    }
}
