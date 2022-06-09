package com.truecaller.prefixminer.treedatastructure.trie;

import java.util.List;

public interface Tree {
    Tree insert(String word);

    String findTheLongestPrefix(String app);

    boolean contains(String word);

    void delete(String word);

    boolean containsPrefix(String prefix);

    List<String> wordsWithPrefix(String prefix);
}