package com.truecaller.prefixminer;

import com.truecaller.prefixminer.reader.DataReader;
import com.truecaller.prefixminer.reader.TextFileReader;
import com.truecaller.prefixminer.treedatastructure.trie.Trie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.Scanner;


@Order(value = 1)
@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {

    private static final String LINE = "--------------------------------------------------------------------------------------------";
    public static final String WELCOME_MESSAGE = "-------------------------------- Welcome to Prefix Miner App -------------------------------";
    public static final String GET_FILE_NAME_MESSAGE = ">> Put your prefixes file in src/main/resources/data directory and enter its file name Or just press enter to use the default sample file:";
    public static final String GET_WORD_MESSAGE = ">> Enter your word to find the longest existing matched prefix, otherwise enter :q to quit:";
    public static final String LOAD_FILE_MESSAGE = "Result: %s prefixes from %s is loaded into the Trie data structure successfully.";
    public static final String FIND_PREFIX_MESSAGE = " Result: %s %n Timing: %s nanos ";

    @Value("${prefix.file.name}")
    private String defaultPrefixFileName;

    private DataReader textFileReader;

    Trie trie;

    public ApplicationStartupRunner(DataReader textFileReader) {
        this.textFileReader = textFileReader;
    }

    @Override
    public void run(String... args) throws Exception {
        trie = new Trie();
        loadDataIntoTrie();
        showTheLongestMatchedPrefix();
    }

    private Trie loadDataIntoTrie() {
        System.out.println(LINE);
        System.out.println(WELCOME_MESSAGE);
        System.out.println(LINE);
        System.out.println(GET_FILE_NAME_MESSAGE);
        Scanner scanner1 = new Scanner(System.in);
        String fileName = scanner1.nextLine();
        fileName = fileName.isEmpty() ? defaultPrefixFileName : fileName;
        try {
            trie = textFileReader.txtFileReader(fileName);
        } catch (IOException e) {
            log.error(e.getMessage());
            System.exit(0);
        }
        System.out.println(String.format(LOAD_FILE_MESSAGE, TextFileReader.prefixTotalCount, fileName));
        return trie;
    }

    private void showTheLongestMatchedPrefix() {
        StopWatch stopWatch = new StopWatch();
        while (true) {
            System.out.println(LINE);
            System.out.println(GET_WORD_MESSAGE);
            Scanner scanner2 = new Scanner(System.in);
            String word = scanner2.nextLine();
            if (word.equals(":q"))
                break;
            stopWatch.start();
            String longestPrefixOfWord = trie.findTheLongestPrefix(word);
            stopWatch.stop();
            System.out.println(String.format(FIND_PREFIX_MESSAGE, longestPrefixOfWord, stopWatch.getLastTaskTimeNanos()));
        }
    }
}
