package org.example.problems.dpproblme;


public class LCS {
    public static void main(String[] args) {
        LCS lcs = new LCS();
        String s1 = "axtdgeisi";
        String s2 = "adgsigi";
        lcs.solve(s1, s2);
    }

    private void solve(String first, String second) {
        char[] firstArr = first.toCharArray();
        char[] secondArr = second.toCharArray();
        String sub = Dfs(firstArr, secondArr, 0, 0, "");
        System.out.println("[ans]=" + sub);
    }

    private String Dfs(char[] firstArr, char[] secondArr, int firstPos, int secondPos, String sub) {
        System.out.println("[input]="+sub);
        //add//skip
        if (firstPos >= firstArr.length || secondPos >= secondArr.length) {
            return sub;
        }
        StringBuilder appender = new StringBuilder();
        for (int i = secondPos; i < secondArr.length; i++) {
            if (firstArr[firstPos] == secondArr[i]) {
                appender.append(firstArr[firstPos] + "");
                secondPos = i;
                break;
            }
        }

        String first = Dfs(firstArr, secondArr, firstPos + 1, secondPos, sub);
        System.out.println("[sub]=" + sub);
        StringBuilder nextstr = new StringBuilder();
        nextstr.append(sub).append(appender);
        String second = Dfs(firstArr, secondArr, firstPos + 1, secondPos, nextstr.toString());
        System.out.println("[sub]=" + nextstr);
        if (first.length() > second.length()) {
            return first;
        } else {
            return second;
        }
    }
}
