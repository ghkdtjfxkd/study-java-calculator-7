package calculator.domain.Calculation;

public class CalculationResult {

    private final Long value;

    private CalculationResult(Long value) {
        this.value = value;
    }

    public static CalculationResult of(Long value) {
        return new CalculationResult(value);
    }

    public CalculationResult apply(Operation operation, Long operand) {
        return CalculationResult.of(operation.apply(this.value, operand));
    }

    public Long getValue() {
        return value;
    }
}
