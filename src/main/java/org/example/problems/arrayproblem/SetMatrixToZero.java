package org.example.problems.arrayproblem;

import java.util.ArrayList;
import java.util.List;

public class SetMatrixToZero {
    public static void main(String[] args) {
        int[][] arr = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
//        setZeroes(arr);
    }

    public void setZeroes(int[][] matrix) {
        List<Pair> zeroIndexList = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            findValue(matrix[row], zeroIndexList, row);
        }
        dfsTrarsal(zeroIndexList, matrix);
    }

    public  void dfsTrarsal(List<Pair> zeroIndexList, int[][] arr) {
        for (Pair pair : zeroIndexList) {
            fillArrWithZero(pair, arr);
        }
        System.out.println(arr);
    }

    public  void fillArrWithZero(Pair pair, int[][] arr) {
        int x = pair.x;
        int y = pair.y;
        //row with zero
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][y] != 0) {
                arr[i][y] = 0;
            }
        }
        //col with zero
        for (int i = 0; i < arr[0].length; i++) {
            if (arr[x][i] != 0) {
                arr[x][i] = 0;
            }
        }
    }

    private  void findValue(int[] oneList, List<Pair> zeroIndexList, int row) {
        for (int col = 0; col < oneList.length; col++) {
            if (oneList[col] == 0) {
                zeroIndexList.add(getPair(row, col));
            }
        }
    }

    public  Pair getPair(int row, int col) {
        Pair pair = new Pair();
        pair.x = row;
        pair.y = col;
        return pair;
    }

    public class Pair {
        int x, y;
    }
}
