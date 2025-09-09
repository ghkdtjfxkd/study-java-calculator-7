package calculator.domain.rawinput;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Delimiters {

    private final Set<String> delimiters;

    private Delimiters() {
        this.delimiters = Set.copyOf(DefaultDelimiter.getDelimiters());
    }

    private Delimiters(Set<String> delimiters) {
        this.delimiters = Set.copyOf(delimiters);
    }

    public static Delimiters defaults() {
        return new Delimiters();
    }

    public static Delimiters withCustom(String customDelimiter) {
        Set<String> result = new HashSet<>(DefaultDelimiter.getDelimiters());
        result.add(customDelimiter);
        return new Delimiters(result);
    }

//    public String toRegex() {
//        return delimiters.stream()
//                .map(Pattern::quote) // 특수문자 이스케이프 처리
//                .collect(Collectors.joining("|"));
//    }

    public boolean has(char currentChar) {
        return delimiters.contains(String.valueOf(currentChar));
    }

    private enum DefaultDelimiter {
        COMMA(","),
        COLON(":");

        final String delimiter;

        DefaultDelimiter(String delimiter) {
            this.delimiter = delimiter;
        }

        String getDelimiter() {
            return this.delimiter;
        }

        static List<String> getDelimiters() {
            return Arrays.stream(DefaultDelimiter.values())
                    .map(DefaultDelimiter::getDelimiter)
                    .toList();
        }
    }
}
