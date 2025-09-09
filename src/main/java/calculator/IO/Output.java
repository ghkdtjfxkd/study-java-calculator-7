package calculator.IO;

public class Output {
    private static final String OutputFormat = "결과 : %s";

    public static void print(String output) {
        System.out.printf(OutputFormat+ System.lineSeparator(), output);
    }
}
