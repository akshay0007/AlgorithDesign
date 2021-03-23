package org.example.bt.dpproblme;

public class CoinChangeProblem {
    public static void main(String[] args) {
        CoinChangeProblem coin = new CoinChangeProblem();
        int[] amts = {1, 2, 3, 4, 5};
        int amt = 5;
        int totalcount = coin.changeProbem(amts, amt, 0);
        System.out.println("[total-count]=" + totalcount);
    }

    private int changeProbem(int[] amts, int amt, int index) {
        int count = 0;
        if (amt == 0) {
            System.out.println("[value=0]");
            return 1;
        }
        if (index >= amts.length || index < 0) {
            System.out.println("[boundry-condition]");
            return 0;
        }
        for (int i = index; i < amts.length; i++) {
            if (amts[i] < amt) {
                int value = changeProbem(amts, (amt - amts[i]), i);
                if (value == 0) {
                    System.out.println("[value=0]");
                    count = 1 + value;
                }
                value = changeProbem(amts, amt, i);
                if (value == 0) {
                    System.out.println("[value=0]");
                    count = 1 + value + count;
                }
            }
        }
        return count;
    }
}
