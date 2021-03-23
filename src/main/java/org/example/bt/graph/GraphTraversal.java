package org.example.bt.graph;


public class GraphTraversal {
    public static void main(String[] args) {
        char[][] matrix = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCB";
        GraphTraversal graphTraversal = new GraphTraversal();
        graphTraversal.exist(matrix, word);
        //[["A","A","A","A","A","A"],["A","A","A","A","A","A"],["A","A","A","A","A","A"],["A","A","A","A","A","A"],["A","A","A","A","A","A"],["A","A","A","A","A","A"]]
        //"AAAAAAAAAAAAAAB"
    }

    public boolean exist(char[][] board, String word) {
        char[] wordArray = word.toCharArray();
        int count = 0;
        char item = wordArray[count];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (item == board[row][col] && dfsTraversal(row, col, board, wordArray, count)) {
                    System.out.println("[found]");
                    return true;
                }else{
                    System.out.println("[false]");
                }
                count = 0;
            }
        }
        System.out.println("[false]");
        return false;
    }

    private boolean dfsTraversal(int row, int col, char[][] board, char[] wordArray, int count) {
        if (count >= wordArray.length) {
            System.out.println("[termination]");
            return true;
        }
        boolean isFound = false;
        if (!validateBoundryCondition(board, row, col, wordArray, count) && board[row][col] == wordArray[count]) {
            char preValue=board[row][col];
            board[row][col] = '*';
            count=count+1;
            isFound = dfsTraversal(row + 1, col, board, wordArray, count) ||
                    dfsTraversal(row - 1, col, board, wordArray, count) ||
                    dfsTraversal(row, col + 1, board, wordArray, count) ||
                    dfsTraversal(row, col - 1, board, wordArray, count);
            resetValue(row,col,board,preValue);
        }
        return isFound;
    }

    private void resetValue(int row, int col, char[][] board, char preValue) {
        System.out.println("[reset happend]=[board]"+board[row][col]+"=[prevalue]="+preValue);
        board[row][col]=preValue;
    }




    private boolean validateBoundryCondition(char[][] board, int row, int col, char[] wordArray, int count) {
        return board.length <= row || row < 0 || board[0].length <= col || col < 0 || wordArray.length <= count
                || count < 0;
    }

}
