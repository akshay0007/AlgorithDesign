package org.example.problems.leetcode;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class hard {
    public static void main(String[] args) {
        int[][] arr = {{0, 0}, {0, 1}};
        hard hard = new hard();
        hard.producerAndConsuer();
    }

    public int minFlips(int[][] mat) {
        dfsFunction(mat);
        return -1;
    }

    public void test() {
        ExecutorService ex1 = Executors.newCachedThreadPool();
        ExecutorService ex2 = Executors.newFixedThreadPool(2);
        ExecutorService ex3 = Executors.newSingleThreadExecutor();
        ExecutorService ex4 = Executors.newScheduledThreadPool(3);
        Lock lock = new ReentrantLock();
        Condition cond = lock.newCondition();
        ReentrantReadWriteLock readWrite = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock read = readWrite.readLock();
        ReentrantReadWriteLock.WriteLock write = readWrite.writeLock();
//        cond.await();
//        cond.signal();
        producerAndConsuer();
    }

    private void producerAndConsuer() {
        Lock lock = new ReentrantLock();
        Queue<Integer> items = new LinkedList();
        Condition prod = lock.newCondition();
        Condition cons = lock.newCondition();
        callProducer(lock, items, cons, prod, 10);
        callConsumer(lock, items, cons, prod, 0);
    }

    private void callConsumer(Lock lock, Queue<Integer> items, Condition cons, Condition prod, int minSize) {
        Runnable consumer = () -> {
            lock.lock();
            try {
                if (items.size() == minSize) {
                    cons.await();
                    System.out.println("[await in consumer]");
                }
                System.out.println("[poll]=" + items.poll());
                prod.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        Thread consTh = new Thread(consumer);
        consTh.start();
    }

    private void callProducer(Lock lock, Queue<Integer> items, Condition cons, Condition prod, int maxSize) {
        Runnable producer = () -> {
            lock.lock();
            try {
                if (items.size() == maxSize) {
                    System.out.println("[prod in await]");
                    prod.await();
                }
                for (int i = 0; i < maxSize; i++) {
                    items.add(i++);
                }
                System.out.println("[prod in await]");
                cons.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        Thread prodTh = new Thread(producer);
        prodTh.start();
    }


    private void dfsFunction(int[][] mat) {
        int minValue = Integer.MAX_VALUE;
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[row].length; col++) {
                int steps = opsPerform(mat, row, col);
                System.out.println("[steps]=" + steps);
                minValue = Math.min(steps, minValue);
            }
        }
        System.out.println("[minvalue]=" + minValue);
    }

    private int opsPerform(int[][] mat, int row, int col) {
        int[][] matcp = cloneMatrix(mat);
        return dfsTraversal(matcp, row, col);
    }

    private int[][] cloneMatrix(int[][] mat) {
        int[][] arr = new int[mat.length][mat[0].length];
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[row].length; col++) {
                arr[row][col] = mat[row][col];
            }
        }
        return arr;
    }

    private int dfsTraversal(int[][] mat, int row, int col) {
        Queue<Pair> queuePair = new LinkedList<>();
        queuePair.add(getPair(row, col));
        Map<String, Boolean> isVisited = new HashMap<>();
        int count = 0;
        while (!queuePair.isEmpty()) {
            int size = queuePair.size();
            count++;
            System.out.println("[size]=" + size);
            for (int i = 0; i < size; i++) {
                Pair loc = queuePair.poll();
                String key = getKey(loc);
                if (isVisited.get(key) != null && isVisited.get(key)) {
                    continue;
                }
                isVisited.putIfAbsent(key, true);
                int[][] subSeq = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                if (!isAllSubSeqZero(subSeq, mat, loc)) {
                    for (int[] cord : subSeq) {
                        if (isAllMatrixZero(mat)) {
                            return count;
                        }
                        addIntoQueueAndMakeZero(queuePair, mat, cord, loc);
                        System.out.println("[after adding and zero]=" + queuePair.size());
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private String getKey(Pair loc) {
        return loc.row + "&" + loc.col;
    }

    private boolean isAllSubSeqZero(int[][] subSeq, int[][] mat, Pair loc) {
        for (int[] cord : subSeq) {
            int row = getAnInt(loc.row, 0, cord);
            int col = getAnInt(loc.col, 1, cord);
            if (!isOutSideBoundry(mat, row, col)) {
                if (mat[row][col] == 1) {
                    return false;
                }
            }
        }
        System.out.println("[all are zero]=" + true);
        return true;
    }

    private void addIntoQueueAndMakeZero(Queue<Pair> queuePair, int[][] mat, int[] cord, Pair loc) {
        int row = getAnInt(loc.row, 0, cord);
        int col = getAnInt(loc.col, 1, cord);
        if (!isOutSideBoundry(mat, row, col)) {
            mat[row][col] = mat[row][col] == 0 ? 1 : 0;
            queuePair.add(getPair(row, col));
        }
    }

    private boolean isOutSideBoundry(int[][] mat, int row, int col) {
        return (mat.length <= row || row < 0) || (mat[row].length <= col || col < 0);
    }

    public boolean isAllMatrixZero(int[][] mat) {
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[row].length; col++) {
                if (mat[row][col] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getAnInt(int ele, int index, int[] cord) {
        return ele + cord[index];
    }

    class Pair {
        int row, col;
    }

    Pair getPair(int row, int col) {
        Pair pair = new Pair();
        pair.row = row;
        pair.col = col;
        return pair;
    }
}
