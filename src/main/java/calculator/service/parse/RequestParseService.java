package calculator.service.parse;

import calculator.domain.CalcElement;
import calculator.dto.CalculationRequest;
import java.util.Queue;

@FunctionalInterface
public interface RequestParseService {
    Queue<CalcElement> parse(CalculationRequest request);
}
