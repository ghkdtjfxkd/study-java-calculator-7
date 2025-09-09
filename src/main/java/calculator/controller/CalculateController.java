package calculator.controller;

import calculator.IO.Output;
import calculator.IO.UserInput;
import calculator.dto.CalculationRequest;
import calculator.dto.CalculationResponse;
import calculator.service.CalculateService;

public class CalculateController {

    private final CalculateService calculateService;

    private CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    public static CalculateController of(CalculateService calculateService) {
        return new CalculateController(calculateService);
    }

    public void execute() {
        CalculationResponse calculateResponse = calculate();
        Output.print(calculateResponse.answer());
    }

    private CalculationResponse calculate() {
        return calculateService.calculate(request());
    }

    private CalculationRequest request() {
        String read = UserInput.read();
        return CalculationRequest.of(read);
    }
}
