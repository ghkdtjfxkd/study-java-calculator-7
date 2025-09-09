package calculator;

import calculator.controller.CalculateController;
import calculator.service.CalculateService;
import calculator.service.CalculateServiceImpl;

public class Application {
    public static void main(String[] args) {
        CalculateService calculateService = new CalculateServiceImpl();
        CalculateController.of(calculateService).execute();
    }
}
