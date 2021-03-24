package org.example.problems.arrayproblem;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public static void main(String[] args) {
        int row = 5;
        PascalTriangle pascalTriangle = new PascalTriangle();
        pascalTriangle.generate(row);
    }

    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> pascalList = new ArrayList<>();
        for (int i = 0; i < rowIndex; i++) {
            System.out.println("[pascal triangle]");
            createPascalRow(i, pascalList);
        }
        System.out.println("[pascal list]");
        return pascalList.get(rowIndex);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            System.out.println("[pascal triangle]");
            createPascalRow(i, pascalList);
        }
        System.out.println("[pascal list]");
        return pascalList;
    }

    private void createPascalRow(int row, List<List<Integer>> pascalList) {
        List<Integer> list = new ArrayList<>();
        int col = row;
        for (int i = 0; i <= col; i++) {
            System.out.println("[pascal row]");
            if (i == 0 || i == col) {
                list.add(1);
            } else {
                System.out.println("[adding into internal rows]");
                List<Integer> preList = pascalList.get(row - 1);
                Integer colItem = preList.get(i - 1) + preList.get(i);
                list.add(colItem);
            }
        }
        pascalList.add(list);
    }
}
