package org.example.bt.dpproblme;

public class DungeonGame {
    public static void main(String[] args) {
        DungeonGame game = new DungeonGame();
        int[][] gameMatrix = {
                {-2, -3, 3},
                {-2, -3, 3},
                {-2, -3, 3},
        };
        int total = game.effectivePath(gameMatrix, 0, 0);
        System.out.println("[total==ans]=" + total);
    }

    private int effectivePath(int[][] matrix, int row, int col) {
        if(row >= matrix.length && col >= matrix[0].length)
            return 0;
        if (row >= matrix.length || col >= matrix[0].length
                || col < 0 || row < 0) {
            System.out.println("[termination]="+Integer.MIN_VALUE);
            return Integer.MIN_VALUE;
        }
        int rowH = effectivePath(matrix, row + 1, col);
        int colH = effectivePath(matrix, row, col + 1);
        int totalHealth = matrix[row][col] + Math.max(rowH, colH);
        System.out.println("[total health]=" + totalHealth);
        return totalHealth;
    }
}
