package calculator.domain.rawinput;

import static calculator.domain.rawinput.Formula.CustomDelimiterBracket.RIGHT_BRACKET;
import static calculator.domain.rawinput.Formula.CustomDelimiterBracket.LEFT_BRACKET;

public class Formula {

    private static final int CUSTOM_DELIMITER_ELEMENT_LENGTH = 1; // 커스텀 구분자가 '문자'라는 조건이 제거되면 제거하거나 수정할 것.

    private final String rawInput;

    private Formula(String rawInput) {
        this.rawInput = rawInput;
        validateInputIsNotBlank(rawInput);
    }

    public static Formula from(String input) {
        return new Formula(input);
    }

    private void validateInputIsNotBlank(String formula) {
        if (formula == null || formula.isEmpty()) {
            throw new IllegalArgumentException("Order string is empty");
        }
    }

    public Delimiters extractDelimiters() {
        if(hasCustomDelimiter(rawInput)) {
            String customDelimiter = extractDelimiter(rawInput);
            return Delimiters.withCustom(customDelimiter);
        }
        return Delimiters.defaults();
    }

    private boolean hasCustomDelimiter(String input) {
        int startIndex = 0;
        int endIndex = customDelimiterSectionSize();

        if(enoughLengthToBeCustomDelimiterCandidate(input)){
            return false;
        }

        String section = input.trim().substring(startIndex, endIndex);
        return CustomDelimiterBracket.isCovered(section);
    }

    private boolean enoughLengthToBeCustomDelimiterCandidate(String input) {
        return input.length() < customDelimiterSectionSize();
    }

    private int customDelimiterSectionSize() {
        return LEFT_BRACKET.length() + CUSTOM_DELIMITER_ELEMENT_LENGTH + RIGHT_BRACKET.length();
    }

    private String extractDelimiter(String section) {
        return removeBracketsTo(section);
    }

    private String removeBracketsTo(String section) {
        int startIndex = LEFT_BRACKET.length();
        int endIndex = customDelimiterSectionSize() - RIGHT_BRACKET.length();

        return section.substring(startIndex, endIndex);
    }

    public String extractActualFormula() {
        if (hasCustomDelimiter(rawInput)) {
            int startIndex = customDelimiterSectionSize();
            int endIndex = rawInput.length();

            return rawInput.substring(startIndex, endIndex);
        }
        return rawInput;
    }

    protected enum CustomDelimiterBracket {
        LEFT_BRACKET("//"),
        RIGHT_BRACKET("\\n"); // 개행문자를 의미하지 않음. `\` + `n` 합쳐진 문자를 의미함.

        private final String symbol;

        CustomDelimiterBracket(String symbol) {
            this.symbol = symbol;
        }

        int length() {
            return symbol.length();
        }

        static boolean isCovered(String section) {
            return section.startsWith(LEFT_BRACKET.symbol) && section.endsWith(RIGHT_BRACKET.symbol);
        }
    }
}
