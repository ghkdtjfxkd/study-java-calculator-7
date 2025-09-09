package calculator.dto;

public record CalculationResponse(String answer) {
    public static CalculationResponse from(Long aLong) {
        return new CalculationResponse(String.valueOf(aLong));
    }
}
