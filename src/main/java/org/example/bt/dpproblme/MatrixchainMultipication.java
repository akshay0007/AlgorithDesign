package org.example.bt.dpproblme;

public class MatrixchainMultipication {
    public static void main(String[] args) {
        MatrixchainMultipication matrix = new MatrixchainMultipication();
        int[] mm = {40, 20, 30, 10, 30};
        matrix.multiplication(mm);
    }

    private int multiplication(int[] matrix) {
        return matrixMultipication(matrix, 0);
    }


    private int matrixMultipication(int[] matrix, int corner) {
        if (matrix.length <= corner) {
            return 0;
        }

        for (int row = corner; row < matrix.length; row++) {
            int multi = matrixMultipication(matrix, row);
        }

        return 0;
    }
}
