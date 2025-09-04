package calculator.service.parse.delimiter;

public class CustomDelimiterServiceImpl implements CustomDelimiterService {

    private static final int START_INDEX = 0;
    private static final int CUSTOM_DELIMITER_LENGTH = 1; // 커스텀 구분자가 '문자'라는 조건이 제거되면 제거하거나 수정할 것.

    @Override
    public String extractFrom(String formula) {
        String customDelimiterCandidate = getCustomDelimiterSection(formula);
        return removeBracketsTo(customDelimiterCandidate);
    }

    private String removeBracketsTo(String section) {
        int startIndex = Bracket.LEFT_BRACKET.symbol.length();
        int endIndex = section.length() - Bracket.RIGHT_BRACKET.symbol.length();

        return section.substring(startIndex, endIndex);
    }

    @Override
    public boolean validate(String formula) {
        String customDelimiterCandidate = getCustomDelimiterSection(formula);
        return isCoveredToBrackets(customDelimiterCandidate);
    }

    private String getCustomDelimiterSection(String formula) {
        return formula.substring(START_INDEX,
                Bracket.LEFT_BRACKET.length() + CUSTOM_DELIMITER_LENGTH + Bracket.RIGHT_BRACKET.length());
    }

    private boolean isCoveredToBrackets(String section) {
        return section.startsWith(Bracket.LEFT_BRACKET.symbol) &&
                section.endsWith(Bracket.RIGHT_BRACKET.symbol);
    }

    private enum Bracket {
        LEFT_BRACKET("//"),
        RIGHT_BRACKET("\\n"); // 개행문자를 의미하지 않음, `\` + `n` 합쳐진 문자를 의미함.

        final String symbol;

        Bracket(String symbol) {
            this.symbol = symbol;
        }

        int length() {
            return symbol.length();
        }
    }
}
