package com.truecaller.prefixminer.reader;

import com.truecaller.prefixminer.treedatastructure.trie.Trie;

import java.io.IOException;

public interface DataReader {
    Trie txtFileReader(String fileName) throws IOException;
}
