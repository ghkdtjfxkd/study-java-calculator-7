package calculator.domain.Calculation;

import calculator.domain.rawinput.Delimiters;
import java.util.LinkedList;
import java.util.Queue;

public class CalculationQueue {

    private final Queue<CalculationElement> elements;

    private CalculationQueue(Queue<CalculationElement> elements) {
        this.elements = new LinkedList<>(elements);
    }

    public static CalculationQueue from(String actualFormula, Delimiters delimiters) {
        Queue<CalculationElement> elements = parseToQueue(actualFormula, delimiters);
        return new CalculationQueue(elements);
    }

    private static Queue<CalculationElement> parseToQueue(String formula, Delimiters delimiters) {
        Queue<CalculationElement> elements = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (char currentChar : formula.toCharArray()) {
            if(!delimiters.has(currentChar)) {
                stringBuilder.append(currentChar);
                continue;
            }
            offerStackedOperandBuffer(stringBuilder, elements);
            elements.offer(CalculationElement.from(String.valueOf(currentChar)));
        }
        offerStackedOperandBuffer(stringBuilder, elements);

        return elements;
    }

    private static void offerStackedOperandBuffer(StringBuilder stringBuilder, Queue<CalculationElement> elements) {
        offerOperand(stringBuilder, elements);
        stringBuilder.setLength(0);
    }

    private static void offerOperand(StringBuilder stringBuilder, Queue<CalculationElement> elements) {
        String token = stringBuilder.toString().trim();
        if(!token.isEmpty()) {
            elements.offer(CalculationElement.from(token));
        }
    }

    public CalculationResult calculate() {
        validateNotEmpty();

        CalculationResult result = getFirstNumberAsResult();
        CalculationElement previous = createDummyOperand();

        while (hasMoreElements()) {
            CalculationElement current = getNextElement();
            result = processElement(current, result); // CalculationResult 반환
            previous = current;
        }

        return result;
    }

    private void validateNotEmpty() {
        if (elements.isEmpty()) {
            throw new IllegalArgumentException("계산할 요소가 없습니다");
        }
    }

    private CalculationResult getFirstNumberAsResult() {
        CalculationElement first = elements.poll();
        validateIsOperand(first);
        Long value = ((CalculationOperand) first).getOperand();
        return CalculationResult.of(value);
    }

    private void validateIsOperand(CalculationElement element) {
        if (element == null || !element.isOperand()) {
            throw new IllegalArgumentException("수식은 숫자로 시작해야 합니다");
        }
    }

    private CalculationElement createDummyOperand() {
        return CalculationOperand.from("0");
    }

    private boolean hasMoreElements() {
        return !elements.isEmpty();
    }

    private CalculationElement getNextElement() {
        return elements.poll();
    }

    private CalculationResult processElement(CalculationElement current, CalculationResult accumulator) {
        if (current.isOperator()) {
            return processOperator((CalculationOperator) current, accumulator);
        }

        throw new IllegalStateException("예상치 못한 피연산자: " + current.rawValue);
    }

    private CalculationResult processOperator(CalculationOperator operator, CalculationResult accumulator) {
        validateHasNextOperand();

        CalculationElement nextElement = getNextElement();
        validateIsOperand(nextElement);

        Long operand = ((CalculationOperand) nextElement).getOperand();

        return accumulator.apply(operator.getOperation(), operand);
    }

    private void validateHasNextOperand() {
        if (!hasMoreElements()) {
            throw new IllegalArgumentException("연산자 다음에 숫자가 와야 합니다");
        }
    }
}
