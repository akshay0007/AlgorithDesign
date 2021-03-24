package org.example.problems.dpproblme;

public class ZeroOneKnapshake {
    public static void main(String[] args) {
        int[][] weightValue = {{6, 10}, {1, 5}, {2, 4}, {4, 8}, {5, 6}};//wt-value
        int maxWeight = 10;
        ZeroOneKnapshake knapshake = new ZeroOneKnapshake();
        knapshake.solveRec(weightValue, maxWeight);
    }

    private void solveRec(int[][] weightValue, int maxWeight) {
        int src = 0;
        int returnValue = dfsRecursion(weightValue, src, maxWeight, 0, 0);
        System.out.println("[return-value]=" + returnValue);
    }

    private int dfsRecursion(int[][] weightMatrix, int src, int bagcapcity, int totalWeight, int profit) {
        if (weightMatrix.length <= src || totalWeight > bagcapcity) {
            System.out.println("[termination]=" + profit);
            return profit;
        }
        //wt-value
        if (bagcapcity > weightMatrix[src][0]) {
            //skip current weight
            int skipBag = dfsRecursion(weightMatrix, src + 1, bagcapcity, totalWeight, profit);

            //take current weight
            int newWeight = totalWeight + weightMatrix[src][0];
            int newProfit = profit + weightMatrix[src][1];
            int takeBag=Integer.MIN_VALUE;
            if (newWeight < bagcapcity) {
                takeBag = dfsRecursion(weightMatrix, src + 1, bagcapcity, newWeight, newProfit);
            }
            //return value
            int returnValue = Math.max(skipBag, takeBag);
            System.out.println("[weight]=" + returnValue);
            return returnValue;
        }
        return profit;
    }
}
