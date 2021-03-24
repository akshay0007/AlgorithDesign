package org.example.problems.testtree;



import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ConstructBtree {
    public static void main(String[] args) {
        ConstructBtree constructBtr = new ConstructBtree();
        List<Integer> listIp = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        TreeNode rootNode = constructBtr.createTree(listIp);
//        constructBtr.hardCode(rootNode);
        constructBtr.diameterOfBinaryTree(rootNode);
    }

    private void diameterOfBinaryTree(TreeNode rootNode) {
        TreeNode left = rootNode.left;
        TreeNode right = rootNode.right;
        diameterOfBinaryTree(left);
        diameterOfBinaryTree(right);
    }

    private void hardCode(TreeNode rootNode) {
        List<List<Integer>> values = verticalTraversal(rootNode);
        values.stream().peek(list -> list.stream().forEach(System.out::print))
                .collect(Collectors.toList()).forEach(System.out::println);
    }


    private List<List<Integer>> verticalTraversal(TreeNode rootNode) {
        Map<Integer, PriorityQueue<NodePosition>> nodePosMap = new HashMap<>();
        nodePosMap.putIfAbsent(0, getPrirorityQueue());
        Queue<NodePosition> tempQueue = new LinkedList<>();
        Queue<TreeNode> srcQueue = new LinkedList<>();
        srcQueue.add(rootNode);//putting src treenode
        NodePosition rootNodePos = createPositionNode(rootNode, 0, 0);
        tempQueue.add(rootNodePos);//putting root nodepos
        nodePosMap.get(0).add(rootNodePos);
        while (!srcQueue.isEmpty()) {//1234
            TreeNode root = srcQueue.poll();
            NodePosition nodepos = tempQueue.poll();
            if (root == null || root.val == null)
                continue;
            putIntoLeft(nodepos, nodePosMap, tempQueue, root, srcQueue);
            putIntoRight(nodepos, nodePosMap, tempQueue, root, srcQueue);
        }
        System.out.println(nodePosMap);
        List<List<Integer>> matrixValue = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        nodePosMap.entrySet().stream().forEach(entry -> {
            matrixValue.add(new ArrayList<>());
            matrixValue.get(count.getAndIncrement()).addAll(entry.getValue().
                    stream().map(NodePosition::getValue).collect(Collectors.toList()));
        });
        return matrixValue;
    }

    class NodePosition {
        Integer value;
        int x, y;

        public Integer getValue() {
            return value;
        }
    }

    private NodePosition createPositionNode(TreeNode rootNode, int x, int y) {
        NodePosition nodePosition = new NodePosition();
        nodePosition.x = x;
        nodePosition.y = y;
        nodePosition.value = rootNode.val;
        return nodePosition;
    }

    private PriorityQueue<NodePosition> getPrirorityQueue() {
        return new PriorityQueue<NodePosition>((tp1, tp2) -> {
            if (tp1.y == tp2.y) {
                return tp1.value.compareTo(tp2.value);
            }
            return tp1.x;
        });
    }

    private void putIntoLeft(NodePosition node, Map<Integer, PriorityQueue<NodePosition>> nodePosMap,
                             Queue<NodePosition> tempQueue, TreeNode treeNode, Queue<TreeNode> srcQueue) {
        if (treeNode.left == null)
            return;
        Integer pointX = node.x - 1;
        Integer pointY = node.y + 1;
        NodePosition nd = createPositionNode(treeNode.left, pointX, pointY);
        if (nd.value == null) {
            return;
        }
        nodePosMap.putIfAbsent(pointX, getPrirorityQueue());
        nodePosMap.get(pointX).add(nd);
        tempQueue.add(nd);
        srcQueue.add(treeNode.left);
    }

    private void putIntoRight(NodePosition node, Map<Integer, PriorityQueue<NodePosition>> nodePosMap,
                              Queue<NodePosition> tempQueue, TreeNode treeNode, Queue<TreeNode> srcQueue) {
        if (treeNode.right == null)
            return;
        Integer pointX = node.x + 1;
        Integer pointY = node.y + 1;
        NodePosition nd = createPositionNode(treeNode.right, pointX, pointY);
        if (nd.value == null) {
            return;
        }
        nodePosMap.putIfAbsent(pointX, getPrirorityQueue());
        nodePosMap.get(pointX).add(nd);
        tempQueue.add(nd);
        srcQueue.add(treeNode.right);
    }


    private TreeNode createTree(List<Integer> list) {
        Queue<Integer> queueNode = new LinkedList<>();
        list.forEach(cons -> queueNode.add(cons));
        TreeNode root = createNode(queueNode.poll());
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode temp = root;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            addToChildToLeft(temp, queueNode, queue);
            addToChildToRight(temp, queueNode, queue);
        }
        System.out.println(root);
        return root;
    }

    private TreeNode addToChildToRight(TreeNode temp, Queue<Integer> queueNode, Queue<TreeNode> queue) {
        if (!queueNode.isEmpty()) {
            temp.right = createNode(queueNode.poll());
            queue.add(temp.right);
        }
        return temp;
    }


    private TreeNode addToChildToLeft(TreeNode temp, Queue<Integer> queueNode, Queue<TreeNode> queue) {
        if (!queueNode.isEmpty()) {
            temp.left = createNode(queueNode.poll());
            queue.add(temp.left);
        }
        return temp;
    }


    private TreeNode createNode(Integer node) {
        return new TreeNode(node);
    }


    private static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;


        TreeNode(Integer val) {
            this.val = val;
        }

    }


}
