package calculator.service.parse;

import calculator.domain.CalcElement;
import calculator.dto.CalculationRequest;
import calculator.service.parse.delimiter.DelimiterDefineService;
import java.util.Queue;
import java.util.Set;

/**
 * 1. 문자열 길이 체크
 * 2. 커스텀 구분자 후보 부분와 일반 수식 부분 분리, 기본 구분자 정의
 * 3. 커스텀 구분자 검증 후 파싱,
 * 4. 커스텀 구분자 추가
 * 5. 전체 수식 파싱
 * 6. 큐 생성 후 반환
 */
public class RequestParseServiceImpl implements RequestParseService {

    private final DelimiterDefineService delimiterDefineService;

    public RequestParseServiceImpl(DelimiterDefineService delimiterDefineService) {
        this.delimiterDefineService = delimiterDefineService;
    }

    @Override
    public Queue<CalcElement> parse(CalculationRequest request) {
        String formula = request.orderString();

        validateFormulaIsNotBlank(formula);

        return null;
    }

    private void validateFormulaIsNotBlank(String formula) {
        if (formula == null || formula.isEmpty()) {
            throw new IllegalArgumentException("Order string is empty");
        }
    }

    private Set<String> getDelimiters(String formula) {
        return delimiterDefineService.defineFor(formula);
    }
}
