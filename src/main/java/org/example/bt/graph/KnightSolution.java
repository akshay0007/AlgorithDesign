package org.example.bt.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KnightSolution {
    public static void main(String[] args) {
        KnightSolution knightSolution = new KnightSolution();
        knightSolution.knight(8, 8, 1, 1, 8, 8);
    }

    public int knight(int A, int B, int C, int D, int E, int F) {
        int row = A-1, col = B-1;
        int srcx = C-1, srcy = D-1;
        int destx = E-1, desty = F-1;
        Queue<Pair> pairQueue = new LinkedList<>();
        boolean[][] isVisited = new boolean[row+1][col+1];
        boolean isTerminated = false;
        addKnightPostions(pairQueue, srcx, srcy, row, col, isVisited, isTerminated);
        System.out.println("[add-knightPos-init]");
        int count = 0;
        while (!pairQueue.isEmpty() && !isTerminated) {
            int size = pairQueue.size();
            count++;
            System.out.println("[count]=[queue-size]=" + size);
            for (int i = 0; i < size; i++) {
                Pair pollPair = pairQueue.poll();
                if (destx == pollPair.x && desty == pollPair.y) {
                    System.out.println("[terminating]");
                    System.out.println("[count-value]=" + count);
                    return count;
                }
                addKnightPostions(pairQueue, pollPair.x, pollPair.y, row, col, isVisited, isTerminated);
            }
        }
        System.out.println("[count-value]=" + -1);
        return -1;

    }

    private void addKnightPostions(Queue<Pair> pairQueue, int srcx, int srcy, int row, int col, boolean[][] isVisited, boolean isTerminated) {
        List<MOVES> moveList = Arrays.asList(MOVES.values());
        for (MOVES moves : moveList) {
            switch (moves) {
                case TOP_R:
                    addIntoQueue(new Pair(srcx + 1, srcy + 2), pairQueue, row, col, isVisited, isTerminated);
                    break;
                case TOP_L:
                    addIntoQueue(new Pair(srcx - 1, srcy + 2), pairQueue, row, col, isVisited, isTerminated);
                    break;
                case BOTTOM_L:
                    addIntoQueue(new Pair(srcx - 1, srcy - 2), pairQueue, row, col, isVisited, isTerminated);
                    break;
                case BOTTOM_R:
                    addIntoQueue(new Pair(srcx + 1, srcy - 2), pairQueue, row, col, isVisited, isTerminated);
                    break;
                case LEFT_B:
                    addIntoQueue(new Pair(srcx - 2, srcy - 1), pairQueue, row, col, isVisited, isTerminated);
                    break;
                case LEFT_T:
                    addIntoQueue(new Pair(srcx - 2, srcy + 1), pairQueue, row, col, isVisited, isTerminated);
                    break;
                case RIGHT_B:
                    addIntoQueue(new Pair(srcx + 2, srcy - 1), pairQueue, row, col, isVisited, isTerminated);
                    break;
                case RIGHT_T:
                    addIntoQueue(new Pair(srcx + 2, srcy + 1), pairQueue, row, col, isVisited, isTerminated);
                    break;
            }
        }
    }

    private void addIntoQueue(Pair pair, Queue<Pair> pairQueue, int row, int col, boolean[][] isVisited, boolean isTerminated) {
        System.out.println("[pair]=x" + pair.x + "[pair]=y" + pair.y);
        if (!isBoundryCondition(pair, row, col)
                && !isVisited[pair.x][pair.y]) {
            pairQueue.add(pair);
            isVisited[pair.x][pair.y] = true;
        }
    }

    private boolean isBoundryCondition(Pair pair, int row, int col) {
        return pair.x > row || pair.x < 0 || pair.y > col || pair.y < 0;
    }

    enum MOVES {
        TOP_L,
        TOP_R,
        LEFT_T,
        LEFT_B,
        BOTTOM_L,
        BOTTOM_R,
        RIGHT_T,
        RIGHT_B
    }

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
