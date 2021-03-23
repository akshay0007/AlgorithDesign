package org.example.bt.arrayproblem;

public class EculidAlgo {
    public static void main(String[] args) {
        EculidAlgo eculidAlgo = new EculidAlgo();
        eculidAlgo.algocall();
    }

    private void algocall() {
        gcdFinder(15, 30);
    }

    private int gcdFinder(int first, int second) {
        if (first == 0 || second == 0) {
            int value = first == 0 ? second : first;
            System.out.println("[termination=gcd]=" + value);
            return value;
        }
        if (first < second) {
            second = second % first;
            return gcdFinder(second, first);
        } else {
            first = first % second;
            return gcdFinder(first, second);
        }
    }
}
