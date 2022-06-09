package com.truecaller.prefixminer.treedatastructure.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*
 * Copyright (c) 2021 Geekific (https://www.youtube.com/c/Geekific)
 * Copyright (c) 2022 Probitlax (https://github.com/probitlax)
*/

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

    @Override
    public boolean contains(String word) {
        Node lastPresentNode = search(word);
        return lastPresentNode != null && lastPresentNode.isEndOfWord();
    }

    @Override
    public boolean containsPrefix(String prefix) {
        return search(prefix) != null;
    }

    private Node search(String str) {
        Node currentNode = null;
        Map<Character, Node> children = root.getChildren();
        for (char c : str.toCharArray()) {
            if (!children.containsKey(c)) {
                return null;
            }
            currentNode = children.get(c);
            children = currentNode.getChildren();
        }
        return currentNode;
    }

    @Override
    public List<String> wordsWithPrefix(String prefix) {
        List<String> words = new ArrayList<>();
        Node prefixNode = search(prefix);
        if (prefixNode != null) {
            addWords(prefixNode, prefix, words);
        }
        return words;
    }

    private void addWords(Node node, String word, List<String> wordList) {
        if (node.isEndOfWord()) {
            wordList.add(word);
        }
        node.getChildren().values().forEach(child -> {
            String character = String.valueOf(child.getCharacter());
            addWords(child, word.concat(character), wordList);
        });
    }

    @Override
    public void delete(String word) {
        List<Node> list = new ArrayList<>();
        Map<Character, Node> children = root.getChildren();
        for (char c : word.toCharArray()) {
            if (!children.containsKey(c)) {
                break;
            }
            Node currentNode = children.get(c);
            children = currentNode.getChildren();
            list.add(currentNode);
        }
        if (list.isEmpty() || list.size() != word.length()) {
            return;
        }
        list.get(list.size() - 1).setEndOfWord(false);
        for (int i = list.size() - 1; i > 0; i--) {
            Node current = list.get(i);
            if (current.getChildren().isEmpty()) {
                list.get(i - 1).getChildren().remove(current.getCharacter());
            } else if (current.isEndOfWord()) {
                break;
            }
        }
    }
}

