package org.example.problems.graph;

import java.util.ArrayList;
import java.util.List;

public class PrimAlgo {
    public static void main(String[] args) {
        PrimAlgo primAlgo = new PrimAlgo();
        Integer[][] arr = {{0, 1, 10}, {0, 2, 4}, {1, 2, 2}, {1, 3, 6}, {1, 0, 10}, {1, 4, 3}, {2, 3, 1}, {3, 1, 6}, {4, 1, 3}};
        primAlgo.primAlgo(arr);
    }

    private void primAlgo(Integer[][] arr) {
        List<Edge> edgeList = getEdgeList(arr);
        int srcVertx = 0;
        List<EdgeDetails> edgeDetails = createPrimeTable(edgeList, 4);
        EdgeDetails edgeDet = edgeDetails.get(srcVertx);
        edgeDet.parent = srcVertx;
        edgeDet.weight = 0;
        primSolver(edgeList, edgeDetails, srcVertx);
    }

    private void primSolver(List<Edge> edgeList, List<EdgeDetails> edgeDetails, int srcVertx) {
        EdgeDetails edgeDet = edgeDetails.get(srcVertx);
        List<Edge> adjList = getAdjEdges(edgeList, srcVertx);
        for (Edge ed : adjList) {
            EdgeDetails eddetail = edgeDetails.get(ed.dest);
            if (eddetail.weight > ed.weight) {
                eddetail.weight = ed.weight;
                eddetail.parent = srcVertx;
            }
        }
        edgeDet.isVisited = true;
        edgeDetails.sort((c1, c2) -> Integer.compare(c1.weight, c2.weight));
        int preSrcVertex = srcVertx;
        for (EdgeDetails edde : edgeDetails) {
            if (!edde.isVisited) {
                srcVertx = edde.vertex;
                break;
            }
        }
        if (srcVertx != preSrcVertex) {
            primSolver(edgeList, edgeDetails, srcVertx);
        }
    }

    private List<Edge> getAdjEdges(List<Edge> edgeList, int srcVertx) {
        List<Edge> adjList = new ArrayList<>();
        for (Edge edge : edgeList) {
            if (edge.src == srcVertx) {
                adjList.add(edge);
            }
        }
        adjList.sort((c1, c2) -> Integer.compare(c1.weight, c2.weight));
        return adjList;
    }

    private List<EdgeDetails> createPrimeTable(List<Edge> edgeList, int vertex) {
        List<EdgeDetails> edgeDetails = new ArrayList<>();
        for (int i = 0; i < vertex; i++) {
            edgeDetails.add(getEdgeDetail(i, -1, Integer.MAX_VALUE, false));
        }
        return edgeDetails;
    }

    private EdgeDetails getEdgeDetail(int vertex, int parent, int weight, boolean isVisited) {
        EdgeDetails edgeDetails = new EdgeDetails();
        edgeDetails.vertex = vertex;
        edgeDetails.parent = parent;
        edgeDetails.weight = weight;
        edgeDetails.isVisited = isVisited;
        return edgeDetails;
    }

    private List<Edge> getEdgeList(Integer[][] arr) {
        List<Edge> edgeList = new ArrayList<>();
        for (Integer[] edges : arr) {
            edgeList.add(getEdge(edges[0], edges[1], edges[2]));
        }
        return edgeList;
    }

    private Edge getEdge(Integer src, Integer dest, Integer weight) {
        Edge edge = new Edge();
        edge.src = src;
        edge.dest = dest;
        edge.weight = weight;
        return edge;
    }

    class Edge {
        int src;
        int dest;
        int weight;
    }

    class EdgeDetails {
        Integer vertex;
        Integer parent;
        Integer weight;
        boolean isVisited;
    }
}
