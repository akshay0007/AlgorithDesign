package org.example.problems.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockBuy {
    public static void main(String[] args) {
        int prices[] = {10, 22, 5, 75, 65, 80};
        StockBuy stockBuy = new StockBuy();
//        stockBuy.dpMatrix(prices, 2);
//        int days[] = {1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 21, 24, 25, 27, 28, 29, 30, 31, 34, 37, 38, 39, 41, 43, 44, 45, 47, 48, 49, 54, 57, 60, 62, 63, 66, 69, 70, 72, 74, 76, 78, 80, 81, 82, 83, 84, 85, 88, 89, 91, 93, 94, 97, 99};
        int days[] = {1, 4, 6, 7, 8, 20};
        int costs[] = {2, 7, 15};
        int index = 0;
        int cost = 0;
        int purchaseAmt = stockBuy.travelSolve(days, costs, index, cost);
        System.out.println("[totalamt]=" + purchaseAmt);
    }

    public int mincostTickets(int[] days, int[] costs) {
        return 2;
    }

    Map<Integer, Integer> cache = new HashMap<>();

    public int travelSolve(int[] days, int[] costs, int dayIndex, int cost) {
        if (dayIndex >= days.length || dayIndex == -1) {
            System.out.println("termination with cost=" + cost);
            return cost;
        }

//        if (cache.containsKey(days[dayIndex])) {
//            return cache.get(days[dayIndex]);
//        }

        int oneDayCost = cost + costs[0];
        System.out.println("[1] day cost=" + cost);
        int sevenDayCost = cost + costs[1];
        System.out.println("[7] day cost=" + cost);
        int thirtyDayCost = cost + costs[2];
        System.out.println("[30] day cost=" + cost);
        int return1DayIndex = dayIndex + 1;
        int return7DayIndex = getNextIndexDay(days, dayIndex, 7);
        int return30DayIndex = getNextIndexDay(days, dayIndex, 30);
        int costOf1Day = travelSolve(days, costs, return1DayIndex, oneDayCost);
        int costOf7Day = travelSolve(days, costs, return7DayIndex, sevenDayCost);
        int costOf30Days = travelSolve(days, costs, return30DayIndex, thirtyDayCost);
        int amount = Math.min(Math.min(costOf1Day, costOf7Day), costOf30Days);
//        cache.put(days[dayIndex], amount);
        return amount;
    }

    private int getNextIndexDay(int[] days, int currentDayIndex, int daysCounter) {
        int nextDay = days[currentDayIndex] + (daysCounter - 1);
        for (int i = currentDayIndex; i < days.length; i++) {
            if (nextDay < days[i]) {
                return i;
            }
        }
        return -1;
    }

    List<Integer> maxProfit = new ArrayList<>();

    public int maxProfit(int k, int[] prices) {
        int count = funcRec(prices, 0, 0, true, k);
        System.out.println("prifit==" + count);
        return count;
    }

    void dpMatrix(int[] arr, int k) {
        int dayLength = arr.length;
        int dp[][] = new int[k + 1][dayLength + 1];
        for (int i = 0; i <= k; i++)
            dp[i][0] = 0;

        // profit is 0 if we don't
        // do any transation
        // (i.e. k =0)
        for (int j = 0; j <= dayLength; j++)
            dp[0][j] = 0;

        for (int i = 1; i < k; i++) {
            for (int j = 1; j < dayLength; j++) {
                int skipAmt = dp[i][j - 1];
                int sell = 0;
                for (int m = 0; m < j; m++) {
                    sell = Math.max(sell, (arr[j] - arr[m] + dp[i - 1][m]));
                }
                dp[i][j] = Math.max(skipAmt, sell);
            }
        }

        System.out.println(dp);
    }


    public int funcRec(int[] prices, int profit, int index, boolean isBuy, int txCount) {
        if (index >= prices.length || txCount <= 0) {
            System.out.println("terminatio condition");
            if (!isBuy) {
                return 0;
            }
            return profit;
        }
        System.out.println("input profilt=" + profit);
        if (isBuy) {
            int newProfit = (profit - prices[index]);
            index = index + 1;
            System.out.println("isBuy=" + newProfit + "=index=" + index + "=tx=" + txCount);
            int ttProfit = Math.max(funcRec(prices, newProfit, index, !isBuy, txCount)
                    , funcRec(prices, newProfit, index, true, txCount));
            return Math.max(ttProfit, profit);
        } else {
            int newProfit = profit + prices[index];
            index = index + 1;
            System.out.println("isSell=" + newProfit + "=index=" + index + "=tx=" + txCount);
            txCount = txCount - 1;
            maxProfit.add(newProfit);
            int ttProfit = Math.max(funcRec(prices, newProfit, index, !isBuy, txCount)
                    , funcRec(prices, newProfit, index, isBuy, txCount));
            return Math.max(ttProfit, profit);
        }
    }

    //    int dp[][][]=new int[][days][]
    int days = 2;
    int txn = 2;

    public void noOfDays() {

    }

    public void noOfTxn() {

    }
}
