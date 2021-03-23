package org.example.bt.dpproblme;

public class RodCutting {
    public static void main(String[] args) {
//        int[][] cutting = {{1, 3}, {2, 5}, {3, 8}, {4, 9}, {5, 10}, {6, 17}, {7, 17}, {8, 20}};
//        RodCutting rodCutting = new RodCutting();
//        rodCutting.cutRoadRec(cutting);
    }

    public int minCost(int n, int[] cuts) {
        Integer[] memo=new Integer[n];
        return cutRoadDfs(cuts, n,memo);
    }

//    private void cutRoadRec(int[][] cutting) {
//        int totalLength = 8;
//        int cutRod = cutRoadDfs(cutting, totalLength);
//        System.out.println("[amt]=" + cutRod);
//    }

    private int cutRoadDfs(int[] cutting, int totalLength, Integer[] memo) {
        if (totalLength < 0) {
            System.out.println("[termination]");
            return 0;
        }
        if (memo[totalLength] != null) {
            return memo[totalLength];
        }
        Integer amt = 0;
        for (int row = 0; row < cutting.length; row++) {
            int calLength = (totalLength - row);
            if (calLength >= 0) {
                int leftAmt = cutting[row] + cutRoadDfs(cutting, calLength, memo);
                amt = Math.max(amt, (leftAmt));
            }
            System.out.println("[sum-amount]=" + amt);
        }
        System.out.println("[return]=" + amt);
        memo[totalLength] = amt;
        return amt;
    }

}


