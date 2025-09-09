package calculator.dto;

public record CalculationRequest(String orderString) {
    public static CalculationRequest of(String orderString) {
        return new CalculationRequest(orderString);
    }
}
