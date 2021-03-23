package org.example.bt.dpproblme;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubstrng {
    public static void main(String[] args) {
        LongestCommonSubstrng longestCommonSubstrng = new LongestCommonSubstrng();
        String x = "abcdgh";
        String y = "acdghrx";
        longestCommonSubstrng.subString(x, y);
    }

    private void subString(String x, String y) {
        subStringDfsSolver(x, y);
        System.out.println("[return-value]=" + finalString);
    }

    String finalString = null;
    Map<String, String> cache=new HashMap<>();

    private String subStringDfsSolver(String x, String y) {
        if (x.length() == 0 || x == null || y.length() == 0 || y == null)
            return "";
        String key=getKey(x,y);
        if(cache.get(key)!=null)
            return cache.get(key);
        String first = x.substring(0, 1);
        String second = y.substring(0, 1);
        StringBuilder returnValue = new StringBuilder();
        if (first.equals(second)) {
            returnValue.append(first);
            returnValue.append(subStringDfsSolver(x.substring(1), y.substring(1)));
            if (finalString == null || finalString.length() < returnValue.toString().length()) {
                finalString = returnValue.toString();
            }
            System.out.println("[equals]=" + returnValue.toString());
        }
        subStringDfsSolver(x.substring(1), y);
        subStringDfsSolver(x, y.substring(1));
//        System.out.println("[continue]=" + returnValue.toString());
        cache.put(key,returnValue.toString());
        return returnValue.toString();
    }

    private String getKey(String x, String y) {
        return x+"&"+y;
    }

}
