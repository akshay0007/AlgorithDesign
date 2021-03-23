package org.example.bt.dpproblme;

public class PaintHouse {
    public static void main(String[] args) {
        PaintHouse house = new PaintHouse();
        int[][] houses = {
                {30, 50, 10},
                {300, 200, 20},
                {400, 40, 300}
        };
        house.painthouse(houses, 0, -1);
    }

    private int painthouse(int[][] houses, int row, int skipCol) {
        int amt = Integer.MAX_VALUE;
        if(row>=houses.length||skipCol>=houses[0].length)
            return 0;
        for (int col = 0; col < houses[row].length; col++) {
                if (skipCol != -1 && skipCol == col) {
                continue;
            }
            int paintInHouse = houses[row][col] + painthouse(houses, row + 1, col);
            System.out.println("[paintInHouse]="+paintInHouse);
            amt = Math.min(paintInHouse, amt);
            System.out.println("[amt]="+amt);
        }
        System.out.println("[retur-amt]="+amt);
        return amt;
    }
}
