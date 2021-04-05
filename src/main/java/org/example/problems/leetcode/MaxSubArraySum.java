package org.example.problems.leetcode;

public class MaxSubArraySum {
    public static void main(String[] args) {
//        int[] arr = {10, -3, -4, 7, 6, 5, -4, -1};
        int[] arr = {-3, -18, -22, -21, -17, 16, -14, 28, -22};
//        circularSubarraySum(arr, arr.length);
        findMax(arr);
    }

    static int circularSubarraySum(int a[], int n) {

        // Your code here
//        return Integer.max(kadane(a, n), reverseKadane(a, n));
        return findMax(a);
    }

    static int findMax(int arr[]) {
        int maxWithPlus = 0;
        int maxTillNow = 0;
        int firstIndex = -1;
        boolean isConditionmet = true;
        int index = 0;
        for (; isConditionmet; ) {
            int currentMax = arr[index] + maxTillNow;
            if (firstIndex == index) {
                System.out.println("[termination]=[maxTillNow]="+maxTillNow+"=[maxPos]="+maxWithPlus);
                break;
            }
            if (currentMax < 0) {
                firstIndex = -1;
                maxTillNow = 0;
                maxWithPlus = maxTillNow;
                System.out.println("[if]=[maxTillNow]="+maxTillNow+"=[maxPos]="+maxWithPlus);
            } else {
                if (firstIndex == -1)
                    firstIndex = index;
                System.out.println("[else]=[maxTillNow]="+maxTillNow+"=[maxPos]="+maxWithPlus);
                maxTillNow = currentMax;
                maxWithPlus = Math.max(maxTillNow, maxWithPlus);
            }

            if (index >= (arr.length - 1)) {
                System.out.println("[index going to 0]="+index);
                index = 0;
            } else {
                index++;
            }
        }
        int outPut= Math.max(maxTillNow, maxWithPlus);
        System.out.println("[output]="+outPut);
        return outPut;
    }
}
