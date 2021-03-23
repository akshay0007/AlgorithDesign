package org.example.bt.dpproblme;


public class DecodeString {
    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
//        decodeString.decode("1123");
        decodeString.dpSolution("1123");
    }

    private void dpSolution(String word) {
        int dp[] = new int[word.length() + 1];
        dp[0] = 1;
        dp[1] = word.charAt(0) != 0 ? 1 : 0;
        for (int i = 2; i <= dp.length; i++) {
            int first = Integer.valueOf(word.substring(i - 1, i));
            int second = Integer.valueOf(word.substring(i - 2, i));
            if (first >= 1 && second <= 9) {
                dp[i] = dp[i] + dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] = dp[i] + dp[i - 2];
            }
        }
    }

    private void decode(String word) {
        int initIndex = 0;
        String decodeString = "";
        dfsApply(word, initIndex, decodeString);
        System.out.println("[word]" + decodeString);
    }


    private void dfsApply(String arr, int initIndex, String decodeString) {
        if (initIndex >= arr.length()) {
            System.out.println("[not-found]");
            return;
        }
        String firstChar = "", secondChar = "";
        if ((initIndex + 1) < arr.length()) {
            System.out.println("[-onechar]");
            firstChar = arr.substring(initIndex, initIndex + 1);
        }
        if ((initIndex + 2) < arr.length()) {
            System.out.println("[-twochar]");
            secondChar = arr.substring(initIndex, initIndex + 2);
        }
        if (!firstChar.isEmpty() && Integer.parseInt(firstChar) == 0) {
            System.out.println("[0-ans]=" + decodeString);
            return;
        }
        if (!secondChar.isEmpty() && Integer.parseInt(secondChar) > 26) {
            System.out.println("[gt>26]=" + decodeString);
            dfsApply(arr, initIndex + 1, decodeString.concat(firstChar));
        } else {
            System.out.println("[lt<26]=" + decodeString);
            if (!firstChar.isEmpty())
                dfsApply(arr, initIndex + 1, decodeString.concat(firstChar));
            if (!secondChar.isEmpty())
                dfsApply(arr, initIndex + 2, decodeString.concat(secondChar));
        }
    }

}
