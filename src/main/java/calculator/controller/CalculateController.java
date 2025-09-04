package calculator.controller;

import calculator.dto.CalculationRequest;
import calculator.dto.CalculationResponse;
import calculator.service.CalculateService;

public class CalculateController {

    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    public CalculationResponse calculate(CalculationRequest request) {
        return calculateService.placeOrder(request);
    }
}
