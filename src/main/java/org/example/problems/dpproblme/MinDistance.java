package org.example.problems.dpproblme;

public class MinDistance {
    public static void main(String[] args) {
        MinDistance minDistance = new MinDistance();
        String word1 = "abc";
        String word2 = "xbc";
        int ans=minDistance.editDistance(word1, word2);
        System.out.println("[anw]="+ans);
    }

    private int editDistance(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0)
            return 0;
        char[] wordchar1 = word1.toCharArray();
        char[] wordchar2 = word2.toCharArray();
        int word1Counter = 0;
        int word2Counter = 0;
        int returnValue = 0;
        if (wordchar1[word1Counter] == wordchar2[word2Counter]) {
            System.out.println("[value-same]");
            returnValue =  editDistance(word1.substring(1), word2.substring(1));
        } else {
            //insert//delete
            if (wordchar1.length != wordchar2.length) {
                System.out.println("[inside insert]");
                if (word1.length() > word2.length()) {
                    char nextchar = wordchar1[word1Counter];
                    String nwword2 = getWordWithAppender(word2, nextchar);
                    returnValue = 1 + editDistance(word1.substring(1), nwword2.substring(1));
                    System.out.println("[inside insert]="+returnValue);
                } else {
                    char nextchar = wordchar2[word2Counter];
                    String nwword1 = getWordWithAppender(word1, nextchar);
                    returnValue = 1 + editDistance(word1.substring(1), nwword1.substring(1));
                    System.out.println("[inside insert]="+returnValue);
                }
            }


            //sub
            if (word1.length() == word2.length()) {
                System.out.println("[inside sub]");
                char word1Char = wordchar1[word1Counter];
                char word2Char = wordchar2[word2Counter];
                word1.replaceFirst(word1Char+"",word2Char+"");
                returnValue = 1 + editDistance(word1.substring(1), word2.substring(1));
                System.out.println("[inside sub]="+returnValue);
            }
        }
        return returnValue;
    }

    private String getWordWithAppender(String word2, char nextchar) {
        StringBuilder word2app = new StringBuilder(word2);
        word2app.append(nextchar);
        return word2app.toString();
    }
}
