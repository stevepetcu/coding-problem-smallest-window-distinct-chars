package com.stefanpetcu.smallestwindowdistinctchars.utils;

import jakarta.validation.constraints.NotNull;

import java.util.stream.Collectors;

public class WordFuzzyMatcher {
    public Integer lengthOfSmallestWindowContainingEveryDistinctCharacterFor(@NotNull final String word) throws AlderaanException {
        if (null == word) {
            return 0;
        }

        if (word.length() <= 1) {
            return word.length();
        }

        var allChars = word.chars().mapToObj(c -> (char) c).toList();
        var uniqueWordChars = word.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());

        if (allChars.size() == uniqueWordChars.size()) {
            return allChars.size();
        }

        // TODO: starting from a random index & direction for "rolling" the window might make this faster.
        //  We would have to ensure the window stays within bounds and remember the starting index + reverse
        //  the sliding direction to ensure a full pass of the input chars.
        //  For now, we always start with the leftmost character and roll the window to the right.
        var rollingWindowStartingIndex = 0;
        var rollingWindowSize = uniqueWordChars.size();

        while (rollingWindowStartingIndex + rollingWindowSize <= allChars.size()) {
            var rollingWindow = allChars.subList(rollingWindowStartingIndex, rollingWindowStartingIndex + rollingWindowSize);

            if (rollingWindow.containsAll(uniqueWordChars)) {
                return rollingWindow.size();
            } else if (rollingWindowStartingIndex + rollingWindowSize == allChars.size()) {
                rollingWindowStartingIndex = 0;
                rollingWindowSize++;
                continue;
            }

            rollingWindowStartingIndex++;
        }

        throw new AlderaanException("I felt a great disturbance in the Force, as if millions of bits " +
                "suddenly cried out in terror and were suddenly flipped. I fear something terrible has happened.");
    }
}

class AlderaanException extends Throwable {
    public AlderaanException(String message) {
        super(message);
    }
}
