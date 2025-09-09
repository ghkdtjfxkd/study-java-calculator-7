package calculator.domain.Calculation;

import java.math.BigDecimal;

public class CalculationOperand extends CalculationElement{

    private final BigDecimal operand;

    private CalculationOperand(String rawValue) {
        super(rawValue);
        this.operand = parseAndValidate(rawValue);
    }

    @Override
    public ElementType getType() {
        return ElementType.OPERAND;
    }

    public static CalculationOperand valueOf(String value) {
        return new CalculationOperand(value);
    }

    public Long getOperand() {
        return operand.longValue();
    }

    private BigDecimal parseAndValidate(String rawValue) {
        try {
            BigDecimal parsed = new BigDecimal(rawValue);
            validateNonNegative(parsed);
            validateWithinLongRange(parsed);
            return parsed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 숫자: " + rawValue, e);
        }
    }

    private void validateNonNegative(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + rawValue);
        }
    }

    private void validateWithinLongRange(BigDecimal value) {
        try {
            long none = value.longValueExact();
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("숫자가 Long 범위를 초과합니다: " + rawValue, e);
        }
    }
}
