package com.truecaller.prefixminer.treedatastructure.trie;

public interface Tree {
    Tree insert(String word);

    String findTheLongestPrefix(String app);
}