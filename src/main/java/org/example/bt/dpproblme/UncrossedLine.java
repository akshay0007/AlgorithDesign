package org.example.bt.dpproblme;

import java.util.HashMap;
import java.util.Map;

public class UncrossedLine {
    public static void main(String[] args) {
        int[] A = {1, 4, 2};
        int[] B = {1, 2, 4};
        UncrossedLine uncrossedLine = new UncrossedLine();
        uncrossedLine.maxUncrossedLines(A, B);
    }

    public int maxUncrossedLines(int[] a, int[] b) {
        int count = dfsUncrossedLine(a, b, 0, 0);
        System.out.println("[count]=" + count);
        return count;
    }

    Map<String, Integer> cache = new HashMap<>();

    private int dfsUncrossedLine(int[] a, int[] b, int aIndex, int bIndex) {
        int count = 0;
        if (aIndex >= a.length || bIndex >= b.length) {
            return 0;
        }
        String key = getKey(aIndex, bIndex);
        if (cache.get(key) != null) {
            return cache.get(key);
        }
        if (a[aIndex] == b[bIndex]) {
            count = 1 + dfsUncrossedLine(a, b, aIndex + 1, bIndex + 1);
            System.out.println("[values are equal and count value]=" + count);
        } else if (a[aIndex] != b[bIndex]) {
            count = Math.max(dfsUncrossedLine(a, b, aIndex + 1, bIndex),
                    dfsUncrossedLine(a, b, aIndex, bIndex + 1));
            System.out.println("[values are not equals and count value]=" + count);
        }
        cache.put(getKey(aIndex, bIndex), count);
        return count;
    }

    private String getKey(int aIndex, int bIndex) {
        return aIndex + "#" + bIndex;
    }
}
