package org.example.problems.dpproblme;

import java.util.HashMap;
import java.util.Map;

public class UniquePath {
    public static void main(String[] args) {
        UniquePath uniquePath = new UniquePath();
        int srcR = 0, srcC = 0;
        int dstR = 2, dstC = 2;
//        int op = uniquePath.dfsPath(srcR, srcC, dstR, dstC);
        int op = uniquePath.dpSolution(srcR, srcC, dstR, dstC);
        System.out.println("[OP]=" + op);
    }

    private int dpSolution(int srcR, int srcC, int dstR, int dstC) {
        int[][] dp = new int[dstR + 1][dstC + 1];
        //last col
        for (int row = dp[dstC].length - 1; row >= 0; row--) {
            dp[row][dstC] = 1;
        }
        //last row
        for (int col = dp[dstR].length - 1; col >= 0; col--) {
            dp[dstR][col] = 1;
        }

        for (int row = dp.length - 2; row >= 0; row--) {
            for (int col = dp[row].length - 2; col >= 0; col--) {
                dp[row][col]=dp[row+1][col]+dp[row][col+1];
            }
        }
        return dp[0][0];
    }

    Map<String, Integer> cache = new HashMap<>();

    private int dfsPath(int srcR, int srcC, int dstR, int dstC) {
        if (cache.get(getKey(srcR, srcC)) != null) {
            return cache.get(getKey(srcR, srcC));
        }
        if (isOutOfBoundry(srcR, srcC, dstC, dstR)) {
            System.out.println("[boundry-condition-0]");
            return 0;
        }
        if (srcR == dstR && srcC == dstC) {
            System.out.println("[end-condition-1]");
            return 1;
        }
        int ans = dfsPath(srcR + 1, srcC, dstR, dstC)
                + dfsPath(srcR, srcC + 1, dstR, dstC);
        cache.put(getKey(srcR, srcC), ans);
        return ans;
    }

    public String getKey(Integer row, Integer col) {
        return row + "#" + col;
    }

    private boolean isOutOfBoundry(int srcR, int srcC, int dstC, int dstR) {
        return srcC < 0 || srcR < 0 || srcC > dstC || srcR > dstR;
    }
}
