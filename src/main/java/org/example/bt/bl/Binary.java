package org.example.bt.bl;

import java.util.*;
import java.util.stream.Collectors;

public class Binary {
    public static void main(String[] args) {
        System.out.println(cardinalitySort(Arrays.asList(1,
                2,
                3,
                4)));
    }

    public static List<Integer> cardinalitySort(List<Integer> nums) {
        // Write your code here

        Map<Integer, Integer> numbersArray = new LinkedHashMap<>();
        for (Integer num : nums) {
            numbersArray.put(num, getOneCount(num));
        }
        Map<Integer, List<Integer>> collect = numbersArray.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new))
                .entrySet().stream()
                .collect(Collectors.groupingBy(en -> en.getValue(), Collectors.mapping(
                        e -> e.getKey(), Collectors.toList()
                )));
        return  collect.entrySet().stream().map(Map.Entry::getValue)
                .flatMap(p -> p.stream())
                .collect(Collectors.toList());
    }

    private static Integer getOneCount(Integer num) {
        String toBinaryString = Integer.toBinaryString(num);
        char[] arr = toBinaryString.toCharArray();
        int count = 0;
        for (char c : arr) {
            if ("1".equals(c + "")) {
                count++;
            }
        }
        return count;
    }
}
