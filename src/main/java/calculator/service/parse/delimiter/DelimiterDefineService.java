package calculator.service.parse.delimiter;

import java.util.Set;

@FunctionalInterface
public interface DelimiterDefineService {
    Set<String> defineFor(String formula);
}
