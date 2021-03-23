package org.example.bt.dpproblme;

public class EggsBreak {
    public static void main(String[] args) {
        EggsBreak eggsBreak = new EggsBreak();
//        int ams = eggsBreak.eggDrop(10, 10);
        int ams = eggsBreak.dpSolution(10, 10);
        System.out.println("[ams]" + ams);
    }

    private int dpSolution(int eggs, int floors) {
        int dp[][] = new int[eggs + 1][floors + 1];
        for (int i = 0; i < eggs; i++) {
            for (int j = 0; j < floors; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 1) {
                    dp[i][j] = j;
                    continue;
                }
                if (j == 1) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = getBest(i, j, dp)+1;
            }
        }
        System.out.println("[answer]="+dp[eggs][floors]);
        return dp[eggs][floors]+1;
    }

    private int getBest(int rowegg, int colfloor, int[][] dp) {
        int minVlaue = Integer.MAX_VALUE;
        for (int i = 1; i < colfloor; i++) {
            minVlaue = Math.min(minVlaue, Math.max(dp[i - 1][rowegg - 1], dp[colfloor - i][rowegg]));
        }
        return minVlaue;
    }

    private int eggDrop(int eggs, int floor) {
        if (eggs == 0 || floor == 0) {
            return 0;
        }
        if (eggs == 1) {
            return floor;
        }
        int preMin = Integer.MAX_VALUE;
        for (int i = 1; i <= floor; i++) {
            int eggBreak = eggDrop(eggs - 1, i - 1);
            int eggSurvive = eggDrop(eggs, floor - i);
            preMin = Math.min(preMin, Math.max(eggBreak, eggSurvive));
        }
        return preMin + 1;
    }
}
