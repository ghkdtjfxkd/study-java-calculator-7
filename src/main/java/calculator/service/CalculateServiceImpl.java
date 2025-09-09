package calculator.service;

import calculator.domain.Calculation.CalculationQueue;
import calculator.domain.Calculation.CalculationResult;
import calculator.domain.rawinput.Formula;
import calculator.dto.CalculationRequest;
import calculator.dto.CalculationResponse;

public class CalculateServiceImpl implements CalculateService {

    @Override
    public CalculationResponse calculate(CalculationRequest request) {
        Formula formula = Formula.from(request.orderString());

        CalculationQueue calculationQueue = CalculationQueue.from(formula.extractActualFormula(), formula.extractDelimiters());
        CalculationResult calculateResult = calculationQueue.calculate();

        return CalculationResponse.from(calculateResult.getValue());
    }
}
