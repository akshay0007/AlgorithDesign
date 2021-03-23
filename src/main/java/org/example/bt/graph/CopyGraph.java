package org.example.bt.graph;

import java.util.*;

public class CopyGraph {
    public static void main(String[] args) {
        CopyGraph copyGraph = new CopyGraph();
        copyGraph.cloneGraph(null);
    }


    public Node cloneGraph(Node node) {
        if(node==null)
            return null;
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(node);
        Map<Node, Node> maps = new HashMap<>();
        if (!maps.containsKey(node)) {
            maps.put(node, new Node(node.val));
        }
        while (!nodeQueue.isEmpty()) {
            Node nodeEle = nodeQueue.poll();
            for (Node neighbor : nodeEle.neighbors) {
                if (!maps.containsKey(neighbor)) {
                         maps.put(neighbor, new Node(neighbor.val));
                    nodeQueue.add(neighbor);
                }
                maps.get(nodeEle).neighbors.add(maps.get(neighbor));
            }
        }
        return maps.get(node);
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
