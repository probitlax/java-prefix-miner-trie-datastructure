package com.truecaller.prefixminer.reader;

import com.truecaller.prefixminer.treedatastructure.trie.Trie;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class TextFileReader implements DataReader {

    public static long prefixTotalCount = 0;

    @Override
    public Trie txtFileReader(String fileName) throws IOException {
        Trie trie = new Trie();
        File file = new ClassPathResource("/data/" + fileName).getFile();
        try (FileReader reader = new FileReader(file)) {
            BufferedReader buffReader = new BufferedReader(reader);
            String singlePrefix;
            while ((singlePrefix = buffReader.readLine()) != null) {
                trie.insert(singlePrefix);
                prefixTotalCount++;
            }
        }
        return trie;
    }

}