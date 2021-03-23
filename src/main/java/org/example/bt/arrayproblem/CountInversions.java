package org.example.bt.arrayproblem;

import java.util.Arrays;
import java.util.List;

public class CountInversions {
    public static void main(String[] args) {
        CountInversions countInversions = new CountInversions();
        int arr[] = {1, 20, 6, 4, 5};
//        countInversions.countInversionSolve(arr);
//        countInversions.matrixSolver();
        double ele = 2.00000;
        int pw = 10;
//        countInversions.myPow(ele, pw);
//        countInversions.majorityElement(null);
//        countInversions.mergeSort(arr);
        lsb();
    }

    private static void lsb() {
        System.out.println(10&-10);
    }

    private void mergeSort(int[] arr) {
        mergeRec(arr, 0, arr.length);
    }

    private int[] mergeRec(int[] arr, int left, int right) {
        if (arr.length == 1) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] leftArr = Arrays.copyOfRange(arr, left, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, right);
        int[] mergeLeft = mergeRec(leftArr, 0, mid);
        int[] mergeRight = mergeRec(rightArr, mid, arr.length);
        int[] mergeArr = mergeArr(mergeLeft, mergeRight);
        return mergeArr;
    }

    private int[] mergeArr(int[] mergeLeft, int[] mergeRight) {
        int[] mergeArr = new int[mergeLeft.length + mergeRight.length + 1];
        int left = 0, right = 0;
        while (left < mergeLeft.length && right < mergeRight.length) {
            if(left>=mergeLeft.length){
                right++;
                continue;
            }
            if(right>=mergeRight.length){
                left++;
                continue;
            }
            if (mergeLeft[left] > mergeRight[right]) {
                int temp = mergeRight[right];
                mergeRight[right] = mergeLeft[left];
                mergeLeft[left] = temp;
                right++;
            }else{
                left++;
            }
        }
        return new int[0];
    }

    public int reversePairs(int[] nums) {
        int count = 0;

        return count;
    }

    public int majorityElement(int[] nums) {
        int count = 0;
        int number = -1;
        for (int ind = 0; ind < nums.length; ind++) {
            int num = nums[ind];
            int currentCount = 0;
            int currentNumber = num;
            for (int ind2 = 0; ind2 < nums.length; ind2++) {
                if (num == nums[ind2]) {
                    currentCount++;
                }
            }
            if (currentCount > count) {
                System.out.println("[gettimg max]");
                count = currentCount;
                number = currentNumber;
                System.out.println("[maxNumber]=" + number);
            }
        }
        System.out.println("[numberis]=" + number);
        return number;
    }

    public double myPow(double x, int n) {
        double returnValue = x;
        int pow = n;
        while (pow > 0) {
            if ((n % 2) == 0) {
                System.out.println("[even]");
                pow = pow / 2;
                returnValue = (x * x);
                System.out.println("[even]=" + "[value]=" + returnValue + "=[pow]=" + pow);
            } else {
                System.out.println("[odd]");
                pow = pow - 1;
                returnValue = returnValue * x;
                System.out.println("[even]=" + "[value]=" + returnValue + "=[pow]=" + pow);
            }
        }
        System.out.println("[return]=" + returnValue);
        return returnValue;
    }

    private void matrixSolver() {
        int mat[][] = {
                {10, 20, 30, 40},
                {15, 25, 35, 45},
                {27, 29, 37, 48},
                {32, 33, 39, 50}
        };
        solveMatrixForSeachOfElment(mat, 37);
    }

    private int[] solveMatrixForSeachOfElment(int[][] mat, int searchEle) {
        int[] eles = {-1, -1};
        for (int row = 0; row < mat.length; row++) {
            int col = findEleInRow(mat[row], searchEle);
            if (col != -1) {
                System.out.println("[found]");
                eles[0] = row;
                eles[1] = col;
                break;
            }
        }
        System.out.println("[eles]={x}=" + eles[0] + "={y}=" + eles[1]);
        return eles;
    }

    private int findEleInRow(int[] row, int searchEle) {
        int colIndex = -1;
        if (row[0] > searchEle) {
            System.out.println("[row[0] < searchEle==termination]");
            return colIndex;
        }
        for (int col = 0; col < row.length; col++) {
            if (row[col] == searchEle) {
                System.out.println("[found]");
                colIndex = col;
            }
        }
        return colIndex;
    }


    private void countInversionSolve(int[] arr) {
        int size = arr.length;
        int mid = size / 2;
        int left = mid;
        divideInHalf(arr, left, size);

    }

    private void divideInHalf(int[] arr, int left, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, 0, left);
        int[] rightArr = Arrays.copyOfRange(arr, left, right);
        int totalCount = 0;
        leftArr = sortArr(leftArr);
        rightArr = sortArr(rightArr);
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < leftArr.length && rightIndex < rightArr.length) {
            if (leftArr[leftIndex] > rightArr[rightIndex]) {
                System.out.println("[left>right]");
                totalCount = totalCount + 1;
                rightIndex++;
                continue;
            } else {
                System.out.println("[right>left]");
                rightIndex = 0;
                leftIndex++;
            }
        }
        System.out.println("[totalcount]=" + totalCount);
    }

    private int[] sortArr(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }
}
