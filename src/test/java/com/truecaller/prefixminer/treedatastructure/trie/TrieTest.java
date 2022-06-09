package com.truecaller.prefixminer.treedatastructure.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

class TrieTest {
    private Trie underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new Trie();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a,a",
            "ab,ab",
            "abc,abc",
            "abcd,abc",
            "abcde,abc",
            "abc12,abc",
            "abXc,ab"}, delimiter = ',')
    void findTheLongestPrefix_itShouldFindTheLongest(String inputWord, String expectedResult) {
        //Given
        Trie abcTrie = DataFixture.getSampleAbcTrie(underTest);
        //When
        String longestPrefix = abcTrie.findTheLongestPrefix(inputWord);
        //Then
        assertThat(longestPrefix).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "x,PREFIX_NOT_MATCHED",
            "xa,PREFIX_NOT_MATCHED",
            "xab,PREFIX_NOT_MATCHED",
            "xabc,PREFIX_NOT_MATCHED",
            "A,PREFIX_NOT_MATCHED",
            "ABC,PREFIX_NOT_MATCHED",
            "1abc,PREFIX_NOT_MATCHED",
            "!abc,PREFIX_NOT_MATCHED"}, delimiter = ',')
    void findTheLongestPrefix_itShouldNotFindAnything(String inputWord, String expectedResult) {
        //Given
        Trie abcTrie = DataFixture.getSampleAbcTrie(underTest);
        //When
        String longestPrefix = abcTrie.findTheLongestPrefix(inputWord);
        //Then
        assertThat(longestPrefix).isEqualTo(expectedResult);
    }


}