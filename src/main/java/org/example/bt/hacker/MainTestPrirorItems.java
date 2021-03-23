package org.example.bt.hacker;

import java.util.Arrays;
import java.util.List;

public class MainTestPrirorItems {
    public static void main(String[] args) {
        MainTestPrirorItems mainTestPrirorItems = new MainTestPrirorItems();
        calculateAmount(Arrays.asList(
                10,
                18,
                1,
                1,
                16,
                12,
                10,
                18,
                19,
                6,
                6
        ));
        //98
    }

    public static long calculateAmount(List<Integer> prices) {
        // Write your code here
        long total = 0l;
        for (int i = 0; i < prices.size(); i++) {
            if (i == 0) {
                total = total + prices.get(i);
                continue;
            }
            if (prices.get(i) < prices.get(i - 1)) {
                int value = maxValue((1 - min(prices, i - 1)), 0);
                total = total + value;
            } else {
                total = total + (prices.get(i) - prices.get(i - 1));
            }
        }
        return total;

    }

    private static int maxValue(int first, int second) {
        return Math.max(first, second);
    }

    private static int min(List<Integer> prices, int prePos) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prePos; i++) {
            int amout = prices.get(i);
            if (min > amout) {
                min = amout;
            }
        }
        return min;
    }

}
