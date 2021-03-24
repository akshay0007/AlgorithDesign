package org.example.problems.external;

import java.util.*;
import java.util.stream.Collectors;

class FreqStack {
    public FreqStack() {
        priorityMap = new LinkedHashMap<>();
        numberSet = new LinkedList<>();
    }

    LinkedList<Integer> numberSet;
    LinkedHashMap<Integer, Integer> priorityMap;

    public void push(int val) {
        priorityMap.putIfAbsent(val, 0);
        Integer value = priorityMap.get(val) + 1;
        priorityMap.put(val, value);
        numberSet.add(val);
        sorting(priorityMap);
        System.out.println("[pop]=" + "[key]=" + val + "[val]=" + value);
    }

    private void sorting(LinkedHashMap<Integer, Integer> priorityMap) {
        System.out.println("[sorting]");
        LinkedHashMap<Integer, Integer> map = priorityMap.entrySet().stream()
                .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
                .collect(Collectors.toMap(ent -> ent.getKey(), ent -> ent.getValue(),
                        (e1, e2) -> e1, LinkedHashMap::new));
        this.priorityMap = map;
    }

    public int pop() {
        Optional<Map.Entry<Integer, Integer>> firstOpt = priorityMap.entrySet()
                .stream()
                .findFirst();
        int popItem = -1;
        if (firstOpt.isPresent()) {
            Integer setVal = numberSet.getLast();
            Integer mapKey = firstOpt.get().getKey();
            Integer valueMap = firstOpt.get().getValue();
            popItem = mapKey.intValue();
            boolean isRemoveFromSet = false;
            if (setVal != mapKey) {
                Integer setMapVal = priorityMap.get(setVal);
                Integer mapSetVal = priorityMap.get(mapKey);
                if (setMapVal == mapSetVal) {
                    isRemoveFromSet = true;
                }
            }

            if (valueMap == 0) {
                priorityMap.remove(mapKey);
                numberSet.remove(mapKey);
            } else {
                priorityMap.put(mapKey, valueMap - 1);
            }
            sorting(priorityMap);
        }
        System.out.println("[pop]=" + popItem);
        return popItem;
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
    }
}