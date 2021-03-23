package org.example.bt.graph;


import java.util.*;
import java.util.stream.Collectors;

public class CircleProblem {
    public static void main(String[] args) {
        CircleProblem circleProblem = new CircleProblem();
        int[][] arr = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
//        circleProblem.provinaceFind1(arr);
//        circleProblem.canVisitAllRooms(getListKeys());
        int[][] mat = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };
//        circleProblem.islandFill(mat);
//        int[][] matarr = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] matarr = {{0, 0, 0}, {0, 0, 0}};
        int sr = 0, sc = 0, newColor = 2;
//        circleProblem.floodFill(matarr, sr, sc, newColor);
//        String[][] matrix = {{"X", "X", "X", "X"}, {"X", "O", "O", "X"}, {"X", "X", "O", "X"}, {"X", "O", "X", "X"}};
        char[][] matrix = {{'O'}};
        circleProblem.solve(matrix);
    }


    public void solve(char[][] board) {
        int[] rowArray = {0, board.length - 1};
        for (int row : rowArray) {
            validateHorizontal(board, row);
        }
        int[] colArray = {0, board[0].length - 1};
        for (int col : colArray) {
            validateVertical(board, col);
        }
        getOutput(board);
    }

    private void printMatrix(String[][] board) {
        for (String[] rows : board) {
            for (String col : rows) {
                System.out.print(col);
            }
            System.out.println();
        }
    }

    private void getOutput(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'O') {
                    board[row][col] = 'X';
                } else if (board[row][col] == '#') {
                    board[row][col] = 'O';
                }
            }
        }
    }

    private void validateVertical(char[][] board, int col) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'O') {
                dfsValidator(board, i, col);
            }
        }
    }

    private void validateHorizontal(char[][] board, int row) {
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] == 'O') {
                dfsValidator(board, row, i);
            }
        }
    }

    private void dfsValidator(char[][] board, int row, int col) {
        if (!isCornerCase(board, row, col) && board[row][col] == 'O') {
            board[row][col] = '#';
            dfsValidator(board, row + 1, col);
            dfsValidator(board, row - 1, col);
            dfsValidator(board, row, col + 1);
            dfsValidator(board, row, col - 1);
        }
    }

    private boolean isCornerCase(char[][] board, int row, int col) {
        return (board.length <= row || board[0].length <= col)
                || (row < 0 || col < 0);
    }


    private void findRegion(String[] row, int src) {
        for (int i = 0; i < row.length; i++) {
//            if(row)
        }
    }


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        fillColor(image, sr, sc, newColor, oldColor);
        System.out.println(image);
        return image;
    }

    private void fillColor(int[][] image, int sr, int sc, int newColor, int oldColor) {
        if (!minMaxBoundCheck(sr, sc, image) && !(image[sr][sc] == newColor)
                && image[sr][sc] == oldColor) {
            image[sr][sc] = newColor;
            fillColor(image, sr + 1, sc, newColor, oldColor);
            fillColor(image, sr - 1, sc, newColor, oldColor);
            fillColor(image, sr, sc - 1, newColor, oldColor);
            fillColor(image, sr, sc + 1, newColor, oldColor);
        }
    }

    private boolean minMaxBoundCheck(int sr, int sc, int[][] image) {
        return (sr < 0 || sr >= image.length) || (sc < 0 || sc >= image[0].length);
    }

    private void callImageColor(int[][] image, int sr, int sc, int newColor, int row) {
        for (int i = 0; i < image[row].length; i++) {

        }
    }

    private void islandFill(int[][] mat) {
        int count = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    islandBFSFill(mat, i, j);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private void islandBFSFill(int[][] mat, int r, int c) {
        if (!isDownLimit(r, c) && isUpperLimit(r, c, mat.length) && (mat[r][c] == 1)) {
            mat[r][c] = 0;
            islandBFSFill(mat, r - 1, c);
            islandBFSFill(mat, r, c - 1);
            islandBFSFill(mat, r + 1, c);
            islandBFSFill(mat, r, c + 1);
        }
    }

    private boolean isUpperLimit(int r, int c, int length) {
        return r > length || c > length;
    }

    private boolean isDownLimit(int r, int c) {
        return r < 0 || c < 0;
    }

    private static List<List<Integer>> getListKeys() {
        return Arrays.asList(
                Arrays.asList(1, 3),
                Arrays.asList(3, 0, 1),
                Arrays.asList(2),
                Arrays.asList(0)
        );
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        bfsRoom(rooms, visited, 0);
        for (boolean b : visited) {
            if (!b) {
                System.out.println("[false]");
                return false;
            }
        }
        System.out.println("[true]");
        return true;
    }

    private void bfsRoom(List<List<Integer>> rooms, boolean[] visited, int par) {
        Queue<Integer> roomsQueue = new LinkedList<>();
        visited[par] = true;
        roomsQueue.add(par);
        while (!rooms.isEmpty()) {
            Integer room = roomsQueue.poll();
            if (room == null)
                return;
            for (int i = 0; i < rooms.get(room).size(); i++) {
                int roomNumber = rooms.get(room).get(i);
                System.out.println("[roomnumber]" + roomNumber);
                if (!visited[roomNumber]) {
                    visited[roomNumber] = true;
                    roomsQueue.add(roomNumber);
                }
            }
        }
    }

    private void provinaceFind(int[][] mat) {
        int vertex = mat.length;
        int ans = 0;
        boolean[] visited = new boolean[vertex];
        for (int i = 0; i < vertex; i++) {
            if (!visited[i]) {
                ans++;
                System.out.println("[bfs]" + i);
                dfsHelper(visited, mat, i);
            }
        }
        System.out.println("[ans]" + ans);
    }

    private void dfsHelper(boolean[] visited, int[][] mat, int perent) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(perent);
        visited[perent] = true;
        while (!queue.isEmpty()) {
            int ele = queue.poll();
            for (int i = 0; i < mat.length; i++) {
                if (mat[ele][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    private void provinaceFind1(int[][] mat) {
        int vertex = mat.length;
        List<LinkedHashSet<Integer>> provinace = new ArrayList<>();
        createGraph(provinace, vertex);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    provinace.get(i).add(j);
                }
            }
        }
        System.out.println("[size]" + provinace.stream().filter(list -> list.size() > 0)
                .collect(Collectors.toList()).size());


//        for (int i = 0; i < vertex; i++) {
//            int[] adj = arr[i];
//            System.out.println("[dfs]=" + i);
//            dfsCall(adj, arr, i, provinace);
//        }
        System.out.println(provinace.size());
    }


    private void dfsCall(int[] adj, int[][] mat, int parent, List<LinkedHashSet<Integer>> provinace) {
        if (adj.length == 0) {
            System.out.println("[termination]");
            return;
        }
        for (int i = 0; i < adj.length; i++) {
            if (mat[parent][i] == 1) {
                System.out.println("[group]=" + "[parent]=" + parent + "[childs]" + i);
                int node = adj[i];
                provinace.get(parent).add(node);
                dfsCall(mat[node], mat, node, provinace);
            }
        }
    }

    private void createGraph(List<LinkedHashSet<Integer>> provinace, int vertex) {
        for (int i = 0; i < vertex; i++) {
            provinace.add(new LinkedHashSet<Integer>());
        }
    }
}
