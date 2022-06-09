package com.truecaller.prefixminer.treedatastructure.trie;

import java.util.Map;

public class Trie implements Tree {
    private static final String PREFIX_NOT_MATCHED = "PREFIX_NOT_MATCHED";

    private final Node root;

    public Trie() {
        root = new Node(' ');
    }

    @Override
    public Tree insert(String word) {
        Node currentNode = root;
        Map<Character, Node> children = root.getChildren();
        for (char c : word.toCharArray()) {
            if (children.containsKey(c)) {
                currentNode = children.get(c);
            } else {
                currentNode = new Node(c);
                children.put(c, currentNode);
            }
            children = currentNode.getChildren();
        }
        currentNode.setEndOfWord(true);
        return this;
    }

    @Override
    public String findTheLongestPrefix(String str) {
        StringBuilder traversedChar = new StringBuilder("");
        String longestPrefix = PREFIX_NOT_MATCHED;
        Node currentNode = null;
        Map<Character, Node> children = root.getChildren();

        for (char c : str.toCharArray()) {
            if (!children.containsKey(c)) {
                break;
            }
            currentNode = children.get(c);
            children = currentNode.getChildren();

            traversedChar.append(c);
            if (currentNode.isEndOfWord()) {
                longestPrefix = String.valueOf(traversedChar);
            }
        }
        return longestPrefix;
    }
}

