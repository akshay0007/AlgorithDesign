package org.example.bt.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    public T data;
    public List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children=new ArrayList<>();
    }
}
