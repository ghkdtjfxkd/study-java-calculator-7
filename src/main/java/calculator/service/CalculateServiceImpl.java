package calculator.service;

import calculator.domain.CalcElement;
import calculator.dto.CalculationRequest;
import calculator.dto.CalculationResponse;
import calculator.service.parse.RequestParseService;
import java.util.Queue;

public class CalculateServiceImpl implements CalculateService {

    private final RequestParseService requestParseService;
    private final CalcStrategy calcStrategy;

    public CalculateServiceImpl(RequestParseService requestParseService, CalcStrategy calcStrategy) {
        this.requestParseService = requestParseService;
        this.calcStrategy = calcStrategy;
    }

    @Override
    public CalculationResponse placeOrder(CalculationRequest request) {
        Queue<CalcElement> calcElements = requestParseService.parse(request);
        return CalculationResponse.from(calculateResult(calcElements));
    }

    private Long calculateResult(Queue<CalcElement> calcElements) {
        // TODO : 계산 전략 개발
        return calcStrategy.calcFrom(calcElements);
    }
}
