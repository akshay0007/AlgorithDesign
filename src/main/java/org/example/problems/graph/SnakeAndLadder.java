package org.example.problems.graph;

import java.util.*;

public class SnakeAndLadder {
    public static void main(String[] args) {
        int[][] pairs = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };
        SnakeAndLadder snakeAndLadder = new SnakeAndLadder();
        snakeAndLadder.snakesAndLadders(pairs);
    }

    public int snakesAndLadders(int[][] board) {
        int toalSize = (board.length * board[0].length);
        boolean[][] isvisited = new boolean[board.length][board[0].length];
        Queue<Pair> queueMove = new LinkedList<>();
        int move = bfsTravrsal(queueMove, board, toalSize, isvisited);
        return move;
    }

    private int bfsTravrsal(Queue<Pair> queueMove, int[][] board, int toalSize, boolean[][] isvisited) {
        queueMove.add(getPair(0, 0, 1, board));
        int count = 0;
        while (!queueMove.isEmpty()) {
            int size = queueMove.size();
            count++;
            System.out.println("[queue size]=" + size + " [count]=" + count);
            for (int i = 0; i < size; i++) {
                Pair pos = queueMove.poll();
                if (pos.number == toalSize) {
                    System.out.println("[count=tremination]=" + count);
                    return count;
                }
                System.out.println("[polling]=" + pos.number);
                if (!isvisited[pos.x][pos.y]) {
                    isvisited[pos.x][pos.y] = true;
                    System.out.println("[polling pair]");
                    getNextMove(pos, queueMove, board, toalSize, isvisited);
                }
            }
        }
        System.out.println("[count]=ans" + count);
        return count;
    }

    private Pair getPair(int x, int y, int number, int[][] board) {
        Pair pair = new Pair();
        pair.x = x;
        pair.y = y;
        pair.number = number;
        return pair;
    }

    private void getNextMove(Pair prePos, Queue<Pair> queueMove, int[][] board, int toalSize, boolean[][] isvisited) {
        int[] nextPos = {1, 2, 3, 4, 5, 6};
        for (int next : nextPos) {
            Pair nextPair = getnextPos(next, prePos, board, toalSize);
            if (nextPair != null && !isvisited[nextPair.x][nextPair.y]) {
                queueMove.add(nextPair);
//                isvisited[nextPair.x][nextPair.y] = true;
            }
        }
    }

    private Pair getnextPos(int next, Pair prePos, int[][] board, int toalSize) {
        int divisor = board[0].length;
        Pair pair = new Pair();
        pair.number = getNumber(prePos, toalSize, next);
        if (pair.number == -1) {
            return null;
        }
        pairFound(divisor, pair);
        if(board[pair.x][pair.y]!=-1){
            pair.number=board[pair.x][pair.y];
            System.out.println("[board-pos-snake-ladder]="+board[pair.x][pair.y]);
            pairFound(divisor,pair);
        }
        System.out.println("[next pair]=" + pair.number);
        return pair;
    }

    private void pairFound(int divisor, Pair pair) {
        pair.y = ((pair.number - 1) / divisor);
        if ((pair.y ^ 1) != 1) {//odd
            pair.x = ((divisor - ((pair.number - 1) % divisor)) - 1);
        } else {
            pair.x = (((pair.number - 1) % divisor));
        }
    }


    private int getNumber(Pair prePair, int totalSize, int next) {
        int number = prePair.number + next;
        if (number > totalSize) {
            return -1;
        }
        return number;
    }


    class Pair {
        int x;
        int y;
        int number;
    }

}
