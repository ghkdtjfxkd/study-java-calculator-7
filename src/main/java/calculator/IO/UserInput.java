package calculator.IO;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class UserInput {
    private static final String INPUT_INFO_MESSAGE = "덧셈할 문자열을 입력해 주세요.";

    private static void announceInfoMessage() {
        System.out.println(INPUT_INFO_MESSAGE);
    }

    public static String read() {
        announceInfoMessage();
        return readLine();
    }
}
