package org.example.problems.arrayproblem;

public class KadanAlgo {
    public static void main(String[] args) {
        int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
        KadanAlgo kadanAlgo = new KadanAlgo();
        kadanAlgo.solveProblem(arr);
    }

    private void solveProblem(int[] arr) {
        int maxSoFar = 0;
        int maxEnding = 0;
        for (int item : arr) {
            maxEnding = maxEnding + item;
            if (maxEnding <= 0 && item < 0) {
                maxEnding = 0;
            } else {
                if (maxEnding > maxSoFar) {
                    maxSoFar = maxEnding;
                }
            }
        }
        System.out.println("[maxsofar]=" + maxEnding);
    }
}