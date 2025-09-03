package calculator.IO;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserInputTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUp() {
        outputStreamCaptor.reset();
        // 표준 출력의 대상을 우리가 만든 스트림으로 변경
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        // 다른 테스트에 영향을 주지 않도록 표준 입출력을 원래대로 복원
        System.setOut(originalOut);
        System.setIn(originalIn);
    }


    @Test
    @DisplayName("정상적으로_사용자_값_입력")
    void correctInput() {
        // given
        String simulatedInput = "1,2";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // when
        String actual = UserInput.read();

        // then
        assertEquals(simulatedInput, actual);
    }
}
