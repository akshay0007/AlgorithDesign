package org.example.problems.javastream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FoundMedian {
    public static void main(String[] args) {
        foundMedian();
    }

    private static void foundMedian() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());

        Integer answ = list.size() % 2 == 0 ?
                list.stream().sorted().skip((list.size() / 2) - 1).limit(2).reduce(0, Integer::sum)/2
                : list.stream().sorted().skip((list.size() - 1) / 2).limit(1).reduce(0, Integer::sum);
        System.out.println(answ);

    }
}
