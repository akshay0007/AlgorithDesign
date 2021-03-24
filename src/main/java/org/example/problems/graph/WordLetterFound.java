package org.example.problems.graph;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WordLetterFound {

    public static void main(String[] args) {
        String board[][] = {{"o", "a", "a", "n"}, {"e", "t", "a", "e"}, {"i", "h", "k", "r"}, {"i", "f", "l", "v"}};
        String words[] = {"oath", "pea", "eat", "rain"};
//        String words[] = {"oath"};
        WordLetterFound wordLetterFound = new WordLetterFound();
//        wordLetterFound.findWords(board, words);
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> wordList = new ArrayList<>();
        for (String word : words) {
            System.out.println("[word]=" + word + "=[searching]");
            findWordInBoard(board, word, wordList);
        }
        System.out.println("[size]=" + wordList.size());
        Set<String> listSet=wordList.stream().collect(Collectors.toSet());
       List<String> arr= listSet.stream().peek(cons -> System.out.println(cons))
                .collect(Collectors.toList());
        return arr;
    }

    private void findWordInBoard(char[][] board, String word, List<String> wordList) {
        char[] letters = word.toCharArray();
        int count = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (isvalid(board, letters, count, row, col)) {
                    System.out.println("[found]=[word]=" + word);
                    wordList.add(word);
                } else {
                    System.out.println("[not-found]=[word]=" + word);
                }
                count = 0;
            }
        }
    }

    private boolean isvalid(char[][] board, char[] letters, int count, int row, int col) {
        boolean isValid= board[row][col]==getWord(letters, count) && dfsSearch(board, row, col, letters, count);
        return isValid;
    }

    private char getWord(char[] letters, int count) {
        return letters[count];
    }

    private boolean validateBoundryCondition(char[][] board, int row, int col, char[] letters, int count) {
        return row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || count < 0 || count >= letters.length;
    }

    private boolean dfsSearch(char[][] board, int row, int col, char[] letters, int count) {
        if (letters.length <= count) {
            return true;
        }
        boolean isFound = false;
        if (!validateBoundryCondition(board, row, col, letters, count) && board[row][col]==getWord(letters, count)){
            count = count + 1;
            char pre = board[row][col];
            board[row][col] = '*';
            isFound = dfsSearch(board, row + 1, col, letters, count) || dfsSearch(board, row - 1, col, letters, count)
                    || dfsSearch(board, row, col + 1, letters, count) || dfsSearch(board, row, col - 1, letters, count);
            board[row][col] = pre;
        }
        return isFound;
    }
}
