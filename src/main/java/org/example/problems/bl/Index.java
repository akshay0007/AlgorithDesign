package org.example.problems.bl;

import java.io.IOException;
import java.util.*;

public class Index {

    static Map<Integer,Integer> countMap=new HashMap<>();

    public static List<Integer> frequencyOfMaxValue(List<Integer> numbers, List<Integer> q) {
        // Write your code here
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < q.size(); i++) {
            values.add(addList(q.get(i), numbers));
        }
        return values;
    }

    public static Integer addList(int index, List<Integer> numbers) {
        int count = 0;
        int maxValue = Integer.MIN_VALUE;
        int mainIndex = index - 1;
        if (mainIndex == -1) {
            return count;
        }
        Integer value=countMap.get(mainIndex);
        if(value!=null){
            return value;
        }
        for (int j = mainIndex; j < numbers.size(); j++) {
            if (maxValue < numbers.get(j)) {
                maxValue = numbers.get(j);
                count = 0;
            }
            if (maxValue == numbers.get(j)) {
                count++;
            }
        }
        countMap.put(mainIndex,count);
        return count;
    }

    public static void main(String[] args) throws IOException {
        frequencyOfMaxValue(Arrays.asList(5, 4, 5, 3, 2), Arrays.asList(1, 2, 3, 4, 5));
    }


}
