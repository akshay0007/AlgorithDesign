package org.example.problems.trie;

class Trie {

    static class TrieNode {
        TrieNode[] element;
        boolean isEnd;

        TrieNode() {
            this.element = new TrieNode[totalSize];
        }
    }

    static int totalSize = 26;
    TrieNode root = null;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
        root.element = new TrieNode[totalSize];
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        char arr[] = word.toCharArray();
        TrieNode temp = root;
        for (int i = 0; i < arr.length; i++) {
            char currentNode = arr[i];
            int key = getKey(currentNode);
            if (temp.element[key] == null) {
                temp.element[key] = new TrieNode();
            }
            TrieNode node = temp.element[key];
            temp = node;
        }
        temp.isEnd = true;
        System.out.println(temp);
    }


    private int getKey(char ele) {
        return ele - 'a';
    }


    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        char arr[] = word.toCharArray();
        TrieNode temp = root;
        for (int i = 0; i < arr.length; i++) {
            char currentNode = arr[i];
            int key = getKey(currentNode);
            TrieNode node = temp.element[key];
            if (node == null)
                return false;
            temp = node;
        }
        if (!temp.isEnd)
            return false;
        System.out.println(temp);
        return true;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        char arr[] = prefix.toCharArray();
        TrieNode temp = root;
        for (int i = 0; i < arr.length; i++) {
            char currentNode = arr[i];
            int key = getKey(currentNode);
            TrieNode node = temp.element[key];
            if (node == null)
                return false;
            temp = node;
        }
        System.out.println(temp);
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");
        trie.search("app");
        trie.startsWith("app");
        trie.insert("app");
        trie.search("app");
    }
}