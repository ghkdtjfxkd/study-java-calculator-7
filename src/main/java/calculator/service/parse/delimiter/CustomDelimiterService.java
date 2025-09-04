package calculator.service.parse.delimiter;

public interface CustomDelimiterService {
    String extractFrom(String formula);
    boolean validate(String formula);
}
