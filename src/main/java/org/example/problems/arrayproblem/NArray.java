package org.example.problems.arrayproblem;

import java.util.*;

public class NArrayTrees {

    public static void main(String[] args) {
        NArrayTrees nArrayTrees = new NArrayTrees();
        nArrayTrees.designTree();
    }

    private void designTree() {
        //1-2,3
        //2-21,22
        //3-31,32
        Queue<Node<Integer>> queueNode = new LinkedList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("please pass head");
        Integer number = getInput(sc);
        if (number == -1) {
            return;
        }
        Node<Integer> headNode = new Node<>(number);
        System.out.println("number of children");
        queueNode.add(headNode);
        while (!queueNode.isEmpty()) {
            Node<Integer> mainNode = queueNode.poll();
            System.out.println("number of children for =" + mainNode.data);
            Integer childrenCount = getInput(sc);
            for (int count = 0; count < childrenCount; count++) {
                System.out.println("please enter value of child " + count);
                Integer childValue = getInput(sc);
                Node<Integer> nodeChild = new Node<>(childValue);
                queueNode.offer(nodeChild);
                mainNode.getNodeList().add(nodeChild);
            }
        }
        System.out.println(headNode);

    }


    private Integer getInput(Scanner sc) {
        return Integer.parseInt(sc.nextLine());
    }

    class Node<T> {
        T data;
        List<Node> nodeList;

        public Node(T data) {
            this.data = data;
            this.nodeList = new ArrayList<>();
        }

        public List<Node> getNodeList() {
            return nodeList;
        }
    }
}
