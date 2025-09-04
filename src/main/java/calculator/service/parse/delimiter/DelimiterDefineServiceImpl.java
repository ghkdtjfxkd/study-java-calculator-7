package calculator.service.parse.delimiter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DelimiterDefineServiceImpl implements DelimiterDefineService {

    private final CustomDelimiterService customDelimiterService;

    public DelimiterDefineServiceImpl(CustomDelimiterService customDelimiterService) {
        this.customDelimiterService = customDelimiterService;
    }

    @Override
    public Set<String> defineFor(String formula) {
        Set<String> delimiterSet = new HashSet<>(DefaultDelimiter.getDelimiters());
        if (hasCustomDelimiter(formula)) {
            String customDelimiter = customDelimiterService.extractFrom(formula);
            delimiterSet.add(customDelimiter);
        }
        return delimiterSet;
    }

    private boolean hasCustomDelimiter(String formula) {
        return customDelimiterService.validate(formula);
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
