package com.truecaller.prefixminer.treedatastructure.trie;

public class DataFixture {
    public static Trie getSampleAbcTrie(Trie trie) {
        trie.insert("a").insert("ab").insert("abc");
        return trie;
    }
}
