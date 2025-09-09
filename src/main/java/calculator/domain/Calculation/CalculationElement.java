package calculator.domain.Calculation;

import java.math.BigDecimal;

public abstract class CalculationElement {

    protected final String rawValue;

    protected CalculationElement(String rawValue) {
        this.rawValue = rawValue;
    }

    public static CalculationElement from(String value){
        if(isNumeric(value)){
            return CalculationOperand.valueOf(value);
        }
        return CalculationOperator.of(value);

    }

    public abstract ElementType getType();

    public boolean isOperand() {
        return getType() == ElementType.OPERAND;
    }

    public boolean isOperator() {
        return getType() == ElementType.OPERATOR;
    }

    private static boolean isNumeric(String value) {
        try {
            new BigDecimal(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public enum ElementType {
        OPERAND, OPERATOR
    }
}
