package org.example.bt.dpproblme;

import java.util.ArrayList;
import java.util.List;

public class MinSteps {
    public static void main(String[] args) {
        MinSteps minSteps = new MinSteps();
        int items = 10;
        Integer[] cache = new Integer[10];
//        int steps = minSteps.minStepsToreach(10, cache);
        minSteps.dpSolution(items);
//        System.out.println("[min-steps]=" + steps);
    }

    private void dpSolution(int number) {
        Integer[] dp = new Integer[number + 1];
        for (int i = 0; i < number; i++) {
            if (i == 0 || i == 1) {
                dp[i] = 0;
            } else {
                int step1 = Integer.MAX_VALUE, step2 = Integer.MAX_VALUE, step3 = Integer.MAX_VALUE;
                if (i % 2 == 0) {
                    step2 = i / 2;
                }
                if (i % 3 == 0) {
                    step3 = i / 3;
                }
                step1 = i - 1;
                Integer minvalue = dp[step1];
                if (dp.length > step2) {
                    minvalue = Math.min(minvalue, dp[step2]);
                }
                if (dp.length > step3) {
                    minvalue = Math.min(minvalue, dp[step3]);
                }
                dp[i] = 1 + minvalue;
            }
        }
        System.out.println("[answer]");
        System.out.println("[number=" + number + "]=" + dp[number - 1]);
    }

    private int minStepsToreach(int number, Integer[] cache) {
        int count = 0;
        if (number == 1) {
            return count;
        }

        int step2 = Integer.MAX_VALUE, step3 = Integer.MAX_VALUE, step1 = Integer.MAX_VALUE;
        //step2
        if (number % 2 == 0)
            step2 = minStepsToreach(getStepMod(number, 2), cache);
        //step3
        if (number % 3 == 0)
            step3 = minStepsToreach(getStepMod(number, 3), cache);
        //step1
        step1 = minStepsToreach(getStepMod(number, 1), cache);
        //steps
        int steps = min(step1, step2, step3);
        return 1 + steps;
    }


    private int min(int step1, int step2, int step3) {
        return Math.min(Math.min(step1, step2), step3);
    }


    private int getStepMod(int number, int div) {
        int step;
        if (div == 1) {
            step = number - 1;
            return step;
        }
        if ((number % div) == 0) {
            step = number / div;
        } else {
            step = number;
        }
        return step;
    }
}
