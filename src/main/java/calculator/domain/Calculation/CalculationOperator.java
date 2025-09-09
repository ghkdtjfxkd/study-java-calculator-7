package calculator.domain.Calculation;

public class CalculationOperator extends CalculationElement{

    private final Operation operation;

    private CalculationOperator(String value) {
        super(value);
        this.operation = Operation.PLUS;
    }

    @Override
    public ElementType getType() {
        return ElementType.OPERATOR;
    }

    public Operation getOperation() {
        return operation;
    }

    public static CalculationOperator of(String value) {
        return new CalculationOperator(value);
    }

    public void validate(CalculationElement element) {
        if(nextElementIsOperator(element)){
            throw new IllegalStateException();
        }
    }

    public boolean nextElementIsOperator(CalculationElement element) {
        return element instanceof CalculationOperator;
    }
}
