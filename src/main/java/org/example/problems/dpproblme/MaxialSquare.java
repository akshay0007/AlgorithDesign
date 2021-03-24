package org.example.problems.dpproblme;

public class MaxialSquare {
    public static void main(String[] args) {
        MaxialSquare square = new MaxialSquare();
        int[][] m = {
                {0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };
//        int[][] m = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        square.findMaxialSquare(m);
    }

    private void findMaxialSquare(int[][] matrix) {
        int row = 0, col = 0;
        int countMatrix = findMatrixDfs(matrix, row, col);
        System.out.println("[value]=" + maxSquare);

    }

    int maxSquare=0;
    private int findMatrixDfs(int[][] matrix, int row, int col) {
        //boundry condition
        if (isBoundryCondition(matrix, row, col)) {
            System.out.println("[boundry-condition]");
            return -1;
        }
        if (row == (matrix.length - 1) || col == (matrix.length - 1)) {
            System.out.println("[return-condtion]");
            return matrix[row][col] == 0 ? 0 : 1;
        }
        int rowInc = findMatrixDfs(matrix, row + 1, col);
        int rowcolInc = findMatrixDfs(matrix, row + 1, col + 1);
        int colInc = findMatrixDfs(matrix, row, col + 1);
        int retrunvalue= matrix[row][col] + Math.min(Math.min(rowInc, colInc), rowcolInc);
        maxSquare=Math.max(maxSquare,retrunvalue);
        return retrunvalue;
    }

    private boolean isBoundryCondition(int[][] matrix, int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }
}
