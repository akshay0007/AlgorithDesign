package org.example.bt.graph;

import java.util.*;
import java.util.regex.Pattern;

public class WordLadder {
    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        String beginWord = "a", endWord = "c";
//        List<String> wordList = Arrays.asList(
//                "hot", "dot", "dog", "lot", "log", "cog"
//        );
        List<String> wordList = Arrays.asList(
                "a", "b", "c"
        );
        wordLadder.ladderLength(beginWord, endWord, wordList);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int minSteps = 0;
        Queue<String> queue = new LinkedList<>();
        Map<String, Boolean> isVistedMap = putValuesInMap(wordList);
        Set<String> adjListEle = getAdjEle(beginWord, wordList);
        System.out.println("[adj-1-time]");
        addToQueue(queue, adjListEle, isVistedMap);
        boolean isTerminated = false;
        while (!queue.isEmpty() && !isTerminated) {
            int size = queue.size();
            minSteps++;
            System.out.println("[minsteps]=" + minSteps);
            for (int i = 0; i < size; i++) {
                if(isTerminated)break;
                String pollEle = queue.poll();
                System.out.print("[poll ele]=" + pollEle + "=");
                isVistedMap.put(pollEle, true);
                Set<String> adjPollEle = getAdjEle(pollEle, wordList);
//                System.out.println("[adjList]");
                addToQueue(queue, adjPollEle, isVistedMap);
                for (String pollAdjEle : adjPollEle) {
                    if (!isVistedMap.get(pollAdjEle)) {
                        if (pollAdjEle.equals(endWord)) {
                            System.out.println("[found]");
                            minSteps++;
                            queue.add(pollAdjEle);
                            isTerminated = true;
                        }
                    }
                }
                System.out.println();
            }
        }
        if(!isTerminated){
            System.out.println("[answer]=" + 0);
            return 0;
        }
        System.out.println("[answer]=" + minSteps);
        return minSteps;
    }

    private StringBuilder getRegexWord(String word, int index) {
        StringBuilder builder = new StringBuilder();
        builder.append(word.substring(0, index));
        builder.append(word.substring(index, index))
                .append(".")
                .append(word.substring(index + 1));
        return builder;
    }

    private void addToQueue(Queue<String> queue, Set<String> adjListEle, Map<String, Boolean> isVistedMap) {
        for (String s : adjListEle) {
            if (!isVistedMap.get(s)) {
                queue.add(s);
            }
        }
    }

    private Set<String> getAdjEle(String beginWord, List<String> wordList) {
        Set<String> arrayList = new HashSet<>();
        Set<String> regexList = getAdjEleData(beginWord);
        for (String ele : wordList) {
            for (String element : regexList) {
                if (ele.matches(element)) {
                    arrayList.add(ele);
                }
            }
        }
        return arrayList;
    }

    private Set<String> getAdjEleData(String words) {
        char[] wordChar = words.toCharArray();
        Set<String> elements = new HashSet<>();
        for (int ind = 0; ind < wordChar.length; ind++) {
            String regWord = getRegexWord(words, ind).toString();
            elements.add(regWord);
        }
        return elements;
    }

    private Map<String, Boolean> putValuesInMap(List<String> wordList) {
        Map<String, Boolean> mapVisited = new HashMap<>();
        for (String ele : wordList) {
            mapVisited.put(ele, Boolean.FALSE);
        }
        return mapVisited;
    }






}
