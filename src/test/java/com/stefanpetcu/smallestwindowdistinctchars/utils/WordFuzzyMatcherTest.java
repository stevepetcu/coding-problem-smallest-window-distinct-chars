package com.stefanpetcu.smallestwindowdistinctchars.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class WordFuzzyMatcherTest {
    private final WordFuzzyMatcher subject = new WordFuzzyMatcher();

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void lengthOfSmallestWindowContainingEveryDistinctCharacterFor_returns_0_when_word_is_empty(String input) throws AlderaanException {
        assertEquals(0, subject.lengthOfSmallestWindowContainingEveryDistinctCharacterFor(input));
    }

    static private Stream<Arguments> stringsAndWindowLengthsProvider() {
        return Stream.of(
                arguments("jiujitsu", 5),
                arguments("AA", 1),
                arguments("BBBBB", 1),
                arguments("Aa", 2),
                arguments("A", 1),
                arguments("Yo", 2),
                arguments("foo", 2),
                arguments("abc", 3),
                arguments("foobaroo", 6),
                arguments("qwertyqwe", 6),
                arguments("qwertyQwe", 7),
                arguments("asdasdfdsadsa", 4),
                arguments("qweqweqweqswe", 4)
        );
    }

    @ParameterizedTest
    @MethodSource("stringsAndWindowLengthsProvider")
    void lengthOfSmallestWindowContainingEveryDistinctCharacterFor_returns_correct_length(String word, Integer allUniqueCharsWindowLength) throws AlderaanException {
        assertEquals(allUniqueCharsWindowLength, subject.lengthOfSmallestWindowContainingEveryDistinctCharacterFor(word));
    }
}
